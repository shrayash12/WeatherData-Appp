package shradha.com.weatherdata.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import shradha.com.weatherdata.R;
import shradha.com.weatherdata.di.WeatherDataApplication;
import shradha.com.weatherdata.mainscreen.MainActivity;
import shradha.com.weatherdata.model.WeatherResponse;

public class SplashActivity extends AppCompatActivity {


    @Inject
    WeatherViewModel weatherViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Dependency injection init for this activity
        ((WeatherDataApplication) getApplication()).getWeatherDataComponents().inject(this);
        weatherViewModel.refreshWeatherData("Singapore");



        weatherViewModel.getWeatherData().observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {

                Log.d(SplashActivity.class.getSimpleName(),weatherResponse.getName());
            }
        });
    }

    private void gotoNextScreen() {
        Bundle bundle = ActivityOptions.makeCustomAnimation(this,
                R.anim.slide_in_right_medium,
                R.anim.slide_out_left_medium).toBundle();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent, bundle);
    }

}