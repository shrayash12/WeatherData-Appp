package shradha.com.weatherdata.splashscreen;

import android.util.Pair;

import io.reactivex.rxjava3.core.Observable;
import shradha.com.weatherdata.model.WeatherForecast;
import shradha.com.weatherdata.model.WeatherResponse;

public interface WeatherRepository {
    Observable<WeatherResponse> getWeatherData(String query);

    Observable<Pair<WeatherResponse, WeatherForecast>> getWeatherForecast(String query);
}
