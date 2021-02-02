package shradha.com.weatherdata;

import org.junit.Assert;
import org.junit.Test;

import shradha.com.weatherdata.utility.Utility;

public class UtilityTest {

    @Test
    public void testMapIntegerDay1() {
        String something = Utility.mapIntegerDayToString(1);
        Assert.assertEquals("Monday", something);
    }

    @Test
    public void testMapIntegerDay2() {
        String something = Utility.mapIntegerDayToString(2);
        Assert.assertEquals("Tuesday", something);
    }

    @Test
    public void testMapIntegerDay3() {
        String something = Utility.mapIntegerDayToString(3);
        Assert.assertEquals("Wednesday", something);
    }

    @Test
    public void testMapIntegerDay4() {
        String something = Utility.mapIntegerDayToString(4);
        Assert.assertEquals("Thursday", something);
    }

    @Test
    public void testMapIntegerDay5() {
        String somthing = Utility.mapIntegerDayToString(5);
        Assert.assertEquals("Friday", somthing);
    }

    @Test
    public void testMapIntegerDay6() {
        String something = Utility.mapIntegerDayToString(6);
        Assert.assertEquals("Saturday", something);
    }

    @Test
    public void testMapIntegerDay7() {
        String something = Utility.mapIntegerDayToString(7);
        Assert.assertEquals("Sunday", something);
    }

    @Test
    public void testMapIntegerMonthToString1() {
        String ans = Utility.mapIntegerMonthToString(1);
        Assert.assertEquals("Jan", ans);
    }

    @Test
    public void testMapIntegerMonthToString2() {
        String ans = Utility.mapIntegerMonthToString(2);
        Assert.assertEquals("Feb", ans);
    }

    @Test
    public void testMapIntegerMonthToString3() {
        String ans = Utility.mapIntegerMonthToString(3);
        Assert.assertEquals("March", ans);
    }

    @Test
    public void testMapIntegerMonthToString4() {
        String ans = Utility.mapIntegerMonthToString(4);
        Assert.assertEquals("April", ans);
    }

    @Test
    public void testMapIntegerMonthToString5() {
        String ans = Utility.mapIntegerMonthToString(5);
        Assert.assertEquals("May", ans);
    }

    @Test
    public void testMapIntegerMonthToString6() {
        String ans = Utility.mapIntegerMonthToString(6);
        Assert.assertEquals("June", ans);
    }

    @Test
    public void testMapIntegerMonthToString7() {
        String ans = Utility.mapIntegerMonthToString(7);
        Assert.assertEquals("July", ans);
    }

    @Test
    public void testMapIntegerMonthToString8() {
        String ans = Utility.mapIntegerMonthToString(8);
        Assert.assertEquals("August", ans);
    }

    @Test
    public void testMapIntegerMonthToString9() {
        String ans = Utility.mapIntegerMonthToString(9);
        Assert.assertEquals("Sept", ans);
    }

    @Test
    public void testMapIntegerMonthToString10() {
        String ans = Utility.mapIntegerMonthToString(10);
        Assert.assertEquals("Oct", ans);
    }

    @Test
    public void testMapIntegerMonthToString11() {
        String ans = Utility.mapIntegerMonthToString(11);
        Assert.assertEquals("Nov", ans);
    }

    @Test
    public void testMapIntegerMonthToString12() {
        String ans = Utility.mapIntegerMonthToString(12);
        Assert.assertEquals("Dec", ans);
    }

    @Test
    public void testGetCelsiusFromKelvin() {
        String tempInCel = Utility.getCelsiusFromKelvin(300.00);
        Assert.assertEquals("26.85", tempInCel);
    }

    @Test
    public void testGetCountryNameFromCode() {
        String countryName = Utility.getCountryNameFromCode("SG");
        Assert.assertEquals("Singapore", countryName);

    }
}
