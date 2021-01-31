package shradha.com.weatherdata.di;

import android.app.Application;

public class WeatherDataApplication extends Application {
    WeatherDataComponents weatherDataComponents;
    @Override
    public void onCreate() {
        super.onCreate();
        weatherDataComponents = DaggerWeatherDataComponents.builder()
                .appModule(new AppModule(this))
                .weatherDataModule(new WeatherDataModule(this))
                .build();
    }

    public WeatherDataComponents getWeatherDataComponents() {
        return weatherDataComponents;
    }
}
