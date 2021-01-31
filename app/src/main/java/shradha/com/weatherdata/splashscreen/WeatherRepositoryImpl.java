package shradha.com.weatherdata.splashscreen;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import shradha.com.weatherdata.model.WeatherResponse;
import shradha.com.weatherdata.model.WeatherService;
import shradha.com.weatherdata.utility.Constants;

public class WeatherRepositoryImpl implements WeatherRepository {

    private final WeatherService weatherService;

    @Inject
    public WeatherRepositoryImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @Override
    public Observable<WeatherResponse> getWeatherData(String query) {
        return weatherService.getWeatherData(query, Constants.API_KEY);
    }
}
