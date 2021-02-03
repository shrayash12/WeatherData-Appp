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
import shradha.com.weatherdata.model.nextdays.WeatherNextDays;
import shradha.com.weatherdata.utility.Constants;

public class WeatherRepositoryImpl implements WeatherRepository {
    private final WeatherService weatherService;

    @Inject
    public WeatherRepositoryImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @Override
    public Observable<WeatherResponse> getWeatherData(String lat, String lon) {
        return weatherService.getWeatherData(lat,lon,Constants.API_KEY);
    }

    @Override
    public Observable<Pair<WeatherResponse, WeatherNextDays>> getWeatherForecast(String lat, String lon) {
        return Observable
                .combineLatest(
                        weatherService.getWeatherData(lat, lon, Constants.API_KEY),
                        weatherService.getWeatherForNext5Days(lat, lon, Constants.API_KEY, Constants.EXCLUDE_DATA),
                        new BiFunction<WeatherResponse, WeatherNextDays, Pair<WeatherResponse, WeatherNextDays>>() {
                            @Override
                            public Pair<WeatherResponse, WeatherNextDays> apply(WeatherResponse weatherResponse, WeatherNextDays weatherNextDays) throws Throwable {
                                return new Pair(weatherResponse, weatherNextDays);
                            }
                        }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(WeatherRepositoryImpl.class.getSimpleName(), throwable.getLocalizedMessage());
                    }
                });
    }
}
