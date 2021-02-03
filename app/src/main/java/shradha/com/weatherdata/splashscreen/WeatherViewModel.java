package shradha.com.weatherdata.splashscreen;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import shradha.com.weatherdata.model.WeatherForecast;
import shradha.com.weatherdata.model.WeatherResponse;
import shradha.com.weatherdata.model.nextdays.WeatherNextDays;

public class WeatherViewModel extends ViewModel {
    WeatherRepository weatherRepository;
    MutableLiveData<WeatherResponse> weatherResponseMutableLiveData;
    MutableLiveData<WeatherNextDays> weatherForecastResponseMutableLiveData;

    @Inject
    public WeatherViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public MutableLiveData<WeatherResponse> getWeatherData() {
        return weatherResponseMutableLiveData;
    }

    public MutableLiveData<WeatherNextDays> getWeatherForecastData() {
        return weatherForecastResponseMutableLiveData;
    }

    public void refreshWeatherForecast(String lat,String lon) {
        weatherForecastResponseMutableLiveData = new MutableLiveData<>();
        weatherResponseMutableLiveData = new MutableLiveData<>();
        weatherRepository.getWeatherForecast(lat,lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Pair<WeatherResponse, WeatherNextDays>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Pair<WeatherResponse, WeatherNextDays> pair) {
                        weatherResponseMutableLiveData.postValue(pair.first);
                        weatherForecastResponseMutableLiveData.postValue(pair.second);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
