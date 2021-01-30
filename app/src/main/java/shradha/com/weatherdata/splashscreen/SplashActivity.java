package shradha.com.weatherdata.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import shradha.com.weatherdata.R;
import shradha.com.weatherdata.mainscreen.MainActivity;
import shradha.com.weatherdata.model.WeatherResponse;

public class SplashActivity extends AppCompatActivity {

    @Inject
    WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        weatherViewModel.getWeatherData("Singapore")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull WeatherResponse weatherResponse) {
                gotoNextScreen();
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void gotoNextScreen() {
        Bundle bundle =   ActivityOptions.makeCustomAnimation(this,
                R.anim.slide_in_right_medium,
                R.anim.slide_out_left_medium).toBundle();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent, bundle);
    }

}