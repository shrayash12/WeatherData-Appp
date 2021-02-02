package shradha.com.weatherdata;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.rxjava3.core.Observable;
import shradha.com.weatherdata.model.WeatherResponse;
import shradha.com.weatherdata.model.WeatherService;
import shradha.com.weatherdata.splashscreen.WeatherRepository;
import shradha.com.weatherdata.splashscreen.WeatherRepositoryImpl;
import shradha.com.weatherdata.utility.Constants;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class WeatherRepositoryTest {


    @Mock
    WeatherService weatherService;

    WeatherRepository weatherRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        weatherRepository = new WeatherRepositoryImpl(weatherService);
    }

    @Test
    public void testGetWeatherData() {

        WeatherResponse weatherResponse = new WeatherResponse("Singapore");
        when(weatherService.getWeatherData("Singapore", Constants.API_KEY)).thenReturn(Observable.just(weatherResponse));

        weatherRepository.getWeatherData("Singapore")
                .test()
                .assertNoErrors()
                .assertComplete()
                .assertValue(weatherResponse)
                .dispose();
    }
}
