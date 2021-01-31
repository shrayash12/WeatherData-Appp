package shradha.com.weatherdata.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import shradha.com.weatherdata.R;
import shradha.com.weatherdata.model.WeatherResponse;
import shradha.com.weatherdata.utility.DataProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherResponse weatherResponse = DataProvider.getInstance().getData();

    }
}