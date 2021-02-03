package shradha.com.weatherdata.model;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import shradha.com.weatherdata.model.nextdays.WeatherNextDays;

public interface WeatherService {
    @GET("data/2.5/weather")
    Observable<WeatherResponse> getWeatherData(@Query("q") String q, @Query("appid") String appid);

    @GET("data/2.5/forecast")
    Observable<WeatherForecast> getWeatherForecast(@Query("q") String q, @Query("appid") String appid);

    @GET("data/2.5/onecall")
    Observable<WeatherNextDays> getWeatherForNext5Days(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String appid, @Query("exclude") String exclude);
}
