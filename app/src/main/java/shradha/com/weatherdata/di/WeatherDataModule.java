package shradha.com.weatherdata.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import shradha.com.weatherdata.model.WeatherService;
import shradha.com.weatherdata.splashscreen.WeatherRepository;
import shradha.com.weatherdata.splashscreen.WeatherRepositoryImpl;

@Module
public class WeatherDataModule {

    Application application;

    WeatherDataModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public static WeatherService provideWeatherService(Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }

    @Provides
    @Singleton
    public static WeatherRepository provideWeatherRepository(WeatherService weatherService) {
        WeatherRepository weatherRepository = new WeatherRepositoryImpl(weatherService);
        return weatherRepository;
    }
}
