package shradha.com.weatherdata.di;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import shradha.com.weatherdata.splashscreen.ViewModelFactory;
import shradha.com.weatherdata.splashscreen.ViewModelKey;
import shradha.com.weatherdata.splashscreen.WeatherViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
    //You are able to declare ViewModelProvider.Factory dependency in another module. For example in ApplicationModule.

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel.class)
    abstract ViewModel userViewModel(WeatherViewModel userViewModel);

    //Others ViewModels
}
