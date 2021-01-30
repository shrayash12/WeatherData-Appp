package shradha.com.weatherdata.splashscreen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import shradha.com.weatherdata.model.WeatherResponse;

public class WeatherViewModel extends AndroidViewModel {

    @Inject
    WeatherRepository weatherRepository;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

    Observable<WeatherResponse> getWeatherData(String query) {
        return weatherRepository.getWeatherData(query);
    }
}
