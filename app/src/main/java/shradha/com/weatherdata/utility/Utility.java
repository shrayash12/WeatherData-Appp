package shradha.com.weatherdata.utility;

import android.content.res.Resources;

import androidx.annotation.VisibleForTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Locale;

import shradha.com.weatherdata.R;

public class Utility {

    public static String getCountryNameFromCode(String code) {
        Locale locale = new Locale("", code);
        return locale.getDisplayCountry();
    }

    public static String getCelsiusFromKelvin(Double temp) {
        double celsius = temp - 273.15;

        String ans = String.format("%.0f", celsius);

        return (ans + "\u2103");
    }

    public static String convertIntegerDateToStringData(Integer integer) {
        Date date = new Date(integer * 1000L);
        String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", date); // Thursday
        String day = (String) android.text.format.DateFormat.format("dd", date); // 20
        String monthString = (String) android.text.format.DateFormat.format("MMM", date); // Jun
        String monthNumber = (String) android.text.format.DateFormat.format("MM", date); // 06
        String year = (String) android.text.format.DateFormat.format("yyyy", date); // 2013

        return day + " " + monthString + ", " + dayOfTheWeek;
    }

    @VisibleForTesting
    public static String mapIntegerMonthToString(int day) {
        String month = "";
        switch (day) {
            case 1:
                month = "Jan";
                break;
            case 2:
                month = "Feb";

                break;
            case 3:
                month = "March";

                break;
            case 4:
                month = "April";

                break;
            case 5:
                month = "May";

                break;
            case 6:
                month = "June";

                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "Sept";
                break;
            case 10:
                month = "Oct";
                break;
            case 11:
                month = "Nov";
                break;
            case 12:
                month = "Dec";
                break;
        }
        return month;
    }

    @VisibleForTesting
    public static String mapIntegerDayToString(int day) {
        String dayOfWeek = "";
        switch (day) {
            case 1:
                dayOfWeek = "Monday";
                break;
            case 2:
                dayOfWeek = "Tuesday";

                break;
            case 3:
                dayOfWeek = "Wednesday";

                break;
            case 4:
                dayOfWeek = "Thursday";

                break;
            case 5:
                dayOfWeek = "Friday";

                break;
            case 6:
                dayOfWeek = "Saturday";

                break;
            case 7:
                dayOfWeek = "Sunday";
                break;
        }
        return dayOfWeek;
    }


    public static String getWeatherJson(String weatherType, Resources resources) {
        BufferedReader br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.
                default_weather)));
        if (weatherType.contains("clear sky")) {
            br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.sunny)));
        } else if (weatherType.contains("moderate rain")) {
            br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.moderate_raining)));

        } else if (weatherType.contains("Haze")) {
            br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.wind)));

        } else if (weatherType.contains("few clouds")) {
            br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.fewclouds)));

        } else if (weatherType.contains("storm")) {
            br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.stormhard)));

        } else if (weatherType.contains("overcast clouds")) {
            br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.cloudy)));
        } else if (weatherType.contains("smoke")) {
            br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.smoke)));
        } else if (weatherType.contains("snow")) {
            br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.snow)));
        } else if (weatherType.contains("broken clouds")) {
            br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.brokenclouds)));
        } else if (weatherType.contains("haze")) {
            br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.haze)));
        } else if (weatherType.contains("light rain")) {
            br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.light_rain)));
        } else if (weatherType.contains("scattered clouds")) {
            br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.scatteredclouds)));
        } else if (weatherType.contains("heavy intensity rain")) {
            br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.heavyrain)));

        } else {

        }

        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDateForNextDay(Integer integer) {
        Date date = new Date(integer * 1000L);
        String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", date); // Thursday
        String day = (String) android.text.format.DateFormat.format("dd", date); // 20
        String monthString = (String) android.text.format.DateFormat.format("MMM", date); // Jun
        String monthNumber = (String) android.text.format.DateFormat.format("MM", date); // 06
        String year = (String) android.text.format.DateFormat.format("yyyy", date); // 2013

        return day + monthString;
    }

}
