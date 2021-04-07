package shradha.com.weatherdata.di;

import javax.inject.Singleton;

import dagger.Component;
import shradha.com.weatherdata.mainscreen.MainActivity;
import shradha.com.weatherdata.splashscreen.SplashActivity;

@Singleton
@Component(modules = {WeatherDataModule.class,AppModule.class, ViewModelModule.class})
public interface WeatherDataComponents {
    void inject(MainActivity mainActivity);
    void inject(SplashActivity splashActivity);
}
