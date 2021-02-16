package shradha.com.weatherdata.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import shradha.com.weatherdata.R;
import shradha.com.weatherdata.model.WeatherForecast;
import shradha.com.weatherdata.model.WeatherResponse;
import shradha.com.weatherdata.model.nextdays.WeatherNextDays;
import shradha.com.weatherdata.splashscreen.SearchActivity;
import shradha.com.weatherdata.utility.DataProvider;
import shradha.com.weatherdata.utility.Utility;

public class MainActivity extends AppCompatActivity {

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
    ImageView searchIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();
        WeatherResponse weatherResponse = DataProvider.getInstance().getData();

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
        //endregion

        //region next 5 days UI

        WeatherNextDays weatherNextDays = DataProvider.getInstance().getWeatherForecast();
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

        //endregion

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
        searchIcon = findViewById(R.id.searchIcon);

    }

}