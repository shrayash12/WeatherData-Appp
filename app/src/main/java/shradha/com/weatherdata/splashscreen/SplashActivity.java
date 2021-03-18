package shradha.com.weatherdata.splashscreen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import shradha.com.weatherdata.R;
import shradha.com.weatherdata.di.WeatherDataApplication;
import shradha.com.weatherdata.mainscreen.MainActivity;
import shradha.com.weatherdata.model.WeatherForecast;
import shradha.com.weatherdata.model.WeatherResponse;
import shradha.com.weatherdata.model.nextdays.WeatherNextDays;
import shradha.com.weatherdata.utility.DataProvider;

public class SplashActivity extends AppCompatActivity {


    @Inject
    WeatherViewModel weatherViewModel;

    private FusedLocationProviderClient fusedLocationClient;

    private static final String TAG = "ShoOwnerMaps";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        // Dependency injection init for this activity
        ((WeatherDataApplication) getApplication()).getWeatherDataComponents().inject(this);
        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                //override methods
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    weatherViewModel.refreshWeatherForecast("" + location.getLatitude(), "" + location.getLongitude());
                                    weatherViewModel.getWeatherData().observe(SplashActivity.this, new Observer<WeatherResponse>() {
                                        @Override
                                        public void onChanged(WeatherResponse weatherResponse) {
                                            DataProvider.getInstance().setData(weatherResponse);
                                            gotoNextScreen();

                                        }
                                    });

                                    weatherViewModel.getWeatherForecastData().observe(SplashActivity.this, new Observer<WeatherNextDays>() {
                                        @Override
                                        public void onChanged(WeatherNextDays weatherForecast) {
                                            DataProvider.getInstance().setWeatherForecast(weatherForecast);
                                        }
                                    });


                                }
                            }
                        });

            } else {
                ActivityCompat.requestPermissions(this, permission, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permission,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }


    }

    private void gotoNextScreen() {
        Bundle bundle = ActivityOptions.makeCustomAnimation(this,
                R.anim.slide_in_right_medium,
                R.anim.slide_out_left_medium).toBundle();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent, bundle);
        finish();
    }

}