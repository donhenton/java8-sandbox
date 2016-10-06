/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.java8.features;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://javarevisited.blogspot.com/2015/03/20-examples-of-date-and-time-api-from-Java8.html
 * http://tutorials.jenkov.com/java-date-time/zoneddatetime.html
 * https://dzone.com/articles/deeper-look-java-8-date-and
 *
 *
 * @author dhenton
 */
public class DateLibTests {

    private Logger log = LoggerFactory.getLogger(DateLibTests.class);

    @Test
    public void testDate() {

        LocalDate dateOfBirth = LocalDate.of(2010, 01, 14);
        assertEquals(2010, dateOfBirth.getYear());

    }

    @Test
    public void testMonthDay() {
        LocalDate dateOfBirth = LocalDate.of(2010, 01, 14);
        LocalDate today = LocalDate.now();
        MonthDay birthDay = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);
        assertFalse(currentMonthDay.equals(birthDay));

    }

    @Test
    public void testAddDays() {

        LocalDate randomDay = LocalDate.of(2010, 01, 14);
        LocalDate newDay = randomDay.plusDays(10);
        assertEquals(randomDay.getDayOfMonth() + 10, newDay.getDayOfMonth());

    }

    @Test
    public void testAddMonths() {

        LocalDate randomDay = LocalDate.of(2010, 01, 14);
        LocalDate newDay = randomDay.plus(1, ChronoUnit.MONTHS);
        assertEquals(randomDay.getMonth().getValue() + 1, newDay.getMonth().getValue());

    }

    @Test
    public void testCompareDates() {

        LocalDate day1 = LocalDate.of(2010, 01, 14);
        LocalDate day2 = day1.plus(1, ChronoUnit.MONTHS);
        assertTrue(day2.isAfter(day1));

    }

    @Test
    public void testTimeZone() {
        final String timeSample = "2011-12-03T10:15:30";
        ZoneId america = ZoneId.of("America/Los_Angeles");
        log.debug(america.toString());
        assertEquals("Pacific Time", america.getDisplayName(TextStyle.FULL, Locale.US));

        LocalDateTime dt = LocalDateTime.parse(timeSample, DateTimeFormatter.ISO_DATE_TIME);
        assertEquals(30, dt.getSecond());
        assertEquals(10, dt.getHour());

        LocalDateTime dt2 = ZonedDateTime.of(dt, america).toLocalDateTime();
        assertEquals(10, dt2.getHour());

    }

    @Test
    public void testZoneOffsetEffect() {

        LocalDateTime leaving = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
        ZoneId leavingZone = ZoneId.of("America/Los_Angeles");
        ZonedDateTime zdt = leaving.atZone(leavingZone);
        assertEquals("2013-07-20T19:30:00-07:00[America/Los_Angeles]", 
                zdt.format(DateTimeFormatter.ISO_DATE_TIME));
        
        
        ZoneId arrivingZone = ZoneId.of("America/New_York");
        ZonedDateTime zd2 = zdt.toOffsetDateTime().atZoneSameInstant(arrivingZone);
        
        String testString = "2013-07-20T21:30:00-04:00[America/New_York]";
        if (zd2.getZone().getRules().isDaylightSavings(zd2.toInstant()))
        {
            testString = "2013-07-20T22:30:00-04:00[America/New_York]";
            //log.debug("daylight");
        }
         
        
        assertEquals(testString,zd2.format(DateTimeFormatter.ISO_DATE_TIME));
    }
    
    
    @Test
    public void testTemporalAdjusters()
    {
        LocalDateTime d1 = LocalDateTime.of(2016, Month.FEBRUARY, 20, 19, 30);
        LocalDate lastDayOfMonth = 
                d1.with(TemporalAdjusters.lastDayOfMonth()).toLocalDate();
        LocalDate  d2 = LocalDate.of(2016, Month.FEBRUARY, 29);
        assertEquals(d2,lastDayOfMonth);
        
        
        
        
    }

}
