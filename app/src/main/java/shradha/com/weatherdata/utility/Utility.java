package shradha.com.weatherdata.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utility {

    public static String getCountryNameFromCode(String code) {
        Locale locale = new Locale("", code);
        return locale.getDisplayCountry();
    }

    public static String getCelciusFromFar(Double temp) {
        double celsius = temp - 273.15F;

        String ans = String.format("%.0f", celsius);

        return ans;
    }

    public static String convertIntegerDateToStringData(Integer integer) {
        /*
         * 132472394793
         *
         * Date date = new Date();
         *
         * Calender c  = Calendar.getInStance();
         *
         * c.setTime(date);
         *
         * */
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        String dayOfWeek = "";
        StringBuilder stringBuilder = new StringBuilder();

        try {
            date = originalFormat.parse(integer.toString());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int week = calendar.get(Calendar.DAY_OF_WEEK);
            dayOfWeek = mapIntegerDayToString(week);

            int month = calendar.get(Calendar.MONTH) +  1;

            stringBuilder.append(calendar.get(Calendar.DAY_OF_MONTH) + " ");


            stringBuilder.append(mapIntegerMonthToString(month) + ", ");

            stringBuilder.append(dayOfWeek);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    private static String mapIntegerMonthToString(int day) {
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

    private static String mapIntegerDayToString(int day) {
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

}
