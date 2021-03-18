package shradha.com.weatherdata.mainscreen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import shradha.com.weatherdata.R;
import shradha.com.weatherdata.di.WeatherDataApplication;
import shradha.com.weatherdata.model.WeatherForecast;
import shradha.com.weatherdata.model.WeatherResponse;
import shradha.com.weatherdata.model.nextdays.WeatherNextDays;
import shradha.com.weatherdata.splashscreen.SearchActivity;
import shradha.com.weatherdata.splashscreen.WeatherViewModel;
import shradha.com.weatherdata.utility.DataProvider;
import shradha.com.weatherdata.utility.Utility;

public class MainActivity extends AppCompatActivity {

    @Inject
    WeatherViewModel weatherViewModel;

    TextView cityName;
    TextView countryName;
    TextView date;
    TextView degreeSelsText;
    TextView weatherTypeText;
    TextView windforce;
    TextView humidity;
    TextView pressure;
    LottieAnimationView lottieAnimationView;
    TextView day_1;
    TextView day_2;
    TextView day_3;
    TextView day_4;
    TextView day_5;
    TextView day_1temp;
    TextView day_2temp;
    TextView day_3temp;
    TextView day_4temp;
    TextView day_5temp;
    androidx.appcompat.widget.SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((WeatherDataApplication) getApplication()).getWeatherDataComponents().inject(this);


        findViews();
        WeatherResponse weatherResponse = DataProvider.getInstance().getData();
        setTodayWeatherResponse(weatherResponse);

        //region next 5 days UI
        WeatherNextDays weatherNextDays = DataProvider.getInstance().getWeatherForecast();
        next5daysWeather(weatherNextDays);
        //endregion

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<LatLng> location = getLatLngFromString(query);
                weatherViewModel.refreshWeatherForecast("" + location.get(0).latitude, "" + location.get(0).longitude);
                if (weatherViewModel != null) {
                    weatherViewModel.getWeatherData().observe(MainActivity.this, new Observer<WeatherResponse>() {
                        @Override
                        public void onChanged(WeatherResponse weatherResponse) {
                            setTodayWeatherResponse(weatherResponse);
                        }
                    });

                    weatherViewModel.getWeatherForecastData().observe(MainActivity.this, new Observer<WeatherNextDays>() {
                        @Override
                        public void onChanged(WeatherNextDays weatherNextDays) {
                            next5daysWeather(weatherNextDays);
                        }
                    });

                }
                searchView.setQuery("", false);
                searchView.setIconified(true);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }


    private List<LatLng> getLatLngFromString(String location) {
        List<LatLng> ll = null;
        if (Geocoder.isPresent()) {
            try {
                Geocoder gc = new Geocoder(this);
                List<Address> addresses = gc.getFromLocationName(location, 5); // get the found Address Objects

                ll = new ArrayList<LatLng>(addresses.size()); // A list to save the coordinates if they are available
                for (Address a : addresses) {
                    if (a.hasLatitude() && a.hasLongitude()) {
                        ll.add(new LatLng(a.getLatitude(), a.getLongitude()));
                    }
                }
            } catch (IOException e) {
                // handle the exception
            }
        }
        return ll;
    }

    private void next5daysWeather(WeatherNextDays weatherNextDays) {
        day_1temp.setText("" + Utility.getCelsiusFromKelvin(weatherNextDays.getDaily().get(1).getTemp().getDay()));

        day_2temp.setText("" + Utility.getCelsiusFromKelvin(weatherNextDays.getDaily().get(2).getTemp().getDay()));

        day_3temp.setText("" + Utility.getCelsiusFromKelvin(weatherNextDays.getDaily().get(3).getTemp().getDay()));

        day_4temp.setText("" + Utility.getCelsiusFromKelvin(weatherNextDays.getDaily().get(4).getTemp().getDay()));

        day_5temp.setText("" + Utility.getCelsiusFromKelvin(weatherNextDays.getDaily().get(5).getTemp().getDay()));

        day_1.setText("" + Utility.getDateForNextDay(weatherNextDays.getDaily().get(1).getDt()));

        day_2.setText("" + Utility.getDateForNextDay(weatherNextDays.getDaily().get(2).getDt()));

        day_3.setText("" + Utility.getDateForNextDay(weatherNextDays.getDaily().get(3).getDt()));

        day_4.setText("" + Utility.getDateForNextDay(weatherNextDays.getDaily().get(4).getDt()));

        day_5.setText("" + Utility.getDateForNextDay(weatherNextDays.getDaily().get(5).getDt()));
    }

    private void setTodayWeatherResponse(WeatherResponse weatherResponse) {

        lottieAnimationView.setAnimationFromJson(Utility.getWeatherJson(weatherResponse.getWeather().get(0).getDescription(), getResources()), "");

        //region today UI
        cityName.setText(weatherResponse.getName() + ",");

        countryName.setText(Utility.getCountryNameFromCode(weatherResponse.getSys().getCountry()));

        degreeSelsText.setText("" + Utility.getCelsiusFromKelvin(weatherResponse.getMain().getTemp()));

        date.setText("" + Utility.convertIntegerDateToStringData(weatherResponse.getDt()));

        weatherTypeText.setText("" + weatherResponse.getWeather().get(0).getDescription());

        windforce.setText("" + weatherResponse.getWind().getDeg() + "/" + weatherResponse.getWind().getSpeed() + "hr");

        humidity.setText("" + weatherResponse.getMain().getHumidity() + "%");

        pressure.setText("" + weatherResponse.getMain().getPressure() + " mBar");
    }


    public void findViews() {
        lottieAnimationView = findViewById(R.id.animationView);
        cityName = findViewById(R.id.cityName);
        countryName = findViewById(R.id.countryName);
        date = findViewById(R.id.date);
        degreeSelsText = findViewById(R.id.degreeSelsText);
        weatherTypeText = findViewById(R.id.weatherTypeText);
        windforce = findViewById(R.id.windforce);
        humidity = findViewById(R.id.humidity);
        pressure = findViewById(R.id.pressure);
        day_1 = findViewById(R.id.day_1);
        day_2 = findViewById(R.id.day_2);
        day_3 = findViewById(R.id.day_3);
        day_4 = findViewById(R.id.day_4);
        day_5 = findViewById(R.id.day_5);
        day_1temp = findViewById(R.id.day_1temp);
        day_2temp = findViewById(R.id.day_2temp);
        day_3temp = findViewById(R.id.day_3temp);
        day_4temp = findViewById(R.id.day_4temp);
        day_5temp = findViewById(R.id.day_5temp);
        searchView = findViewById(R.id.searchView);

    }
}