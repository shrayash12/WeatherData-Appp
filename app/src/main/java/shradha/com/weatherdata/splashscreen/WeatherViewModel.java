package shradha.com.weatherdata.splashscreen;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import shradha.com.weatherdata.model.WeatherResponse;

public class WeatherViewModel extends ViewModel {

    WeatherRepository weatherRepository;

    MutableLiveData<WeatherResponse> weatherResponseMutableLiveData;


    @Inject
    public WeatherViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

   public void refreshWeatherData(String query) {
        weatherResponseMutableLiveData = new MutableLiveData<>();
        weatherRepository.getWeatherData(query)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<WeatherResponse>() {
                     @Override
                     public void onSubscribe(@NonNull Disposable d) {

                     }

                     @Override
                     public void onNext(@NonNull WeatherResponse weatherResponse) {
                       weatherResponseMutableLiveData.postValue(weatherResponse);
                     }

                     @Override
                     public void onError(@NonNull Throwable e) {

                     }

                     @Override
                     public void onComplete() {

                     }
                 });
    }


    public  MutableLiveData<WeatherResponse> getWeatherData() {
        return weatherResponseMutableLiveData;
    }

}
