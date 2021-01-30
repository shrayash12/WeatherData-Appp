package shradha.com.weatherdata.splashscreen;

import io.reactivex.rxjava3.core.Observable;
import shradha.com.weatherdata.model.WeatherResponse;

public interface WeatherRepository {
    Observable<WeatherResponse> getWeatherData(String query);
}
