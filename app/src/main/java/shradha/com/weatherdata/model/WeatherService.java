package shradha.com.weatherdata.model;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("data/2.5/weather")
    Observable<WeatherResponse> getWeatherData(@Query("q") String query, @Query("appid") String aapId);
}
