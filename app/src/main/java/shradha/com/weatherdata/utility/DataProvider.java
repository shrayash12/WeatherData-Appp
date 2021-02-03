package shradha.com.weatherdata.utility;

// Singleton design pattern

import shradha.com.weatherdata.model.WeatherForecast;
import shradha.com.weatherdata.model.WeatherResponse;
import shradha.com.weatherdata.model.nextdays.WeatherNextDays;

public class DataProvider {
    static DataProvider dataProvider = null;
    WeatherResponse weatherResponse;

    WeatherNextDays weatherNextDays;

    private DataProvider() {

    }

    public static DataProvider getInstance() {
        if (dataProvider == null) {
            dataProvider = new DataProvider();
        }
        return dataProvider;
    }

    public WeatherNextDays getWeatherForecast() {
        return weatherNextDays;
    }

    public void setWeatherForecast(WeatherNextDays weatherNextDays) {
        this.weatherNextDays = weatherNextDays;
    }

    public void setData(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }

    public WeatherResponse getData() {
        return weatherResponse;
    }

}
