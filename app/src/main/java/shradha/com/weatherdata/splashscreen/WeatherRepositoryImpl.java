package shradha.com.weatherdata.splashscreen;

import android.util.Log;
import android.util.Pair;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import shradha.com.weatherdata.model.WeatherForecast;
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

    @Override
    public Observable<Pair<WeatherResponse, WeatherForecast>> getWeatherForecast(String query) {
        return Observable
                .combineLatest(
                        weatherService.getWeatherData(query, Constants.API_KEY),
                        weatherService.getWeatherForecast(query, Constants.API_KEY),
                        new BiFunction<WeatherResponse, WeatherForecast, Pair<WeatherResponse, WeatherForecast>>() {
                            @Override
                            public Pair<WeatherResponse, WeatherForecast> apply(WeatherResponse weatherResponse, WeatherForecast weatherForecast) throws Throwable {
                                return new Pair(weatherResponse, weatherForecast);
                            }
                        }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(WeatherRepositoryImpl.class.getSimpleName(), throwable.getLocalizedMessage());
                    }
                });
    }
}
