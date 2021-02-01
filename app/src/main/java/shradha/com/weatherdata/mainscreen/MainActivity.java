package shradha.com.weatherdata.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

import shradha.com.weatherdata.R;
import shradha.com.weatherdata.model.WeatherResponse;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        WeatherResponse weatherResponse = DataProvider.getInstance().getData();

        cityName.setText(weatherResponse.getName()+",");

        countryName.setText(Utility.getCountryNameFromCode(weatherResponse.getSys().getCountry()));

        degreeSelsText.setText("" + Utility.getCelciusFromFar(weatherResponse.getMain().getTemp()));

        date.setText(""+Utility.convertIntegerDateToStringData(weatherResponse.getDt()));

        weatherTypeText.setText(""+weatherResponse.getWeather().get(0).getDescription());

        windforce.setText(""+weatherResponse.getWind().getDeg()+ "/" + weatherResponse.getWind().getSpeed());

        humidity.setText(""+weatherResponse.getMain().getHumidity());

        pressure.setText(""+weatherResponse.getMain().getPressure());

    }

    public void findViews() {
        cityName = findViewById(R.id.cityName);
        countryName = findViewById(R.id.countryName);
        date = findViewById(R.id.date);
        degreeSelsText = findViewById(R.id.degreeSelsText);
        weatherTypeText = findViewById(R.id.weatherTypeText);
        windforce = findViewById(R.id.windforce);
        humidity = findViewById(R.id.humidity);
        pressure = findViewById(R.id.pressure);
    }
}