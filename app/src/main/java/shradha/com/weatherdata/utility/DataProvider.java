package shradha.com.weatherdata.utility;

// Singleton design pattern

import shradha.com.weatherdata.model.WeatherForecast;
import shradha.com.weatherdata.model.WeatherResponse;

public class DataProvider {
    static DataProvider dataProvider = null;
    WeatherResponse weatherResponse;

    WeatherForecast weatherForecast;

    private DataProvider() {

    }

    public static DataProvider getInstance() {
        if (dataProvider == null) {
            dataProvider = new DataProvider();
        }
        return dataProvider;
    }

    public WeatherForecast getWeatherForecast() {
        return weatherForecast;
    }

    public void setWeatherForecast(WeatherForecast weatherForecast) {
        this.weatherForecast = weatherForecast;
    }

    public void setData(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }

   public WeatherResponse getData() {
        return weatherResponse;
    }

}
