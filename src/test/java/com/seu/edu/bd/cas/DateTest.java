package com.seu.edu.bd.cas;

import com.seu.edu.bd.cas.model.Semester;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

class DateTest {
    @Test
    void dateTest() {
        Calendar calendar = new GregorianCalendar();
        var start = LocalDate.of(2020, Month.JANUARY, 1);
        var end = LocalDate.of(2020, Month.APRIL, 1);
        var localDate = start.atStartOfDay(ZoneId.systemDefault());
        var between = ChronoUnit.WEEKS.between(start.minusDays(3), end.minusDays(3));
        System.out.println("between week = " + between);
        System.out.println("localDate = " + localDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
        var minusDays = start.minusDays(3);
        System.out.println(minusDays.getDayOfWeek());
        System.out.println(end.getDayOfWeek());
        var weekday = end.getDayOfWeek().getValue();
        if (weekday!=1){
        }
        Semester semester = new Semester(1,"Spring",start,end,start.getYear()+"");
        System.out.println(semester.toString());
        var start1 = LocalDate.of(2020, Month.JANUARY, 1);
        var end1 = LocalDate.of(2020, Month.JANUARY, 31);
        var between1 = ChronoUnit.WEEKS.between(start1, end1);
        System.out.println("between  sum of week 1-1-2021 to 31-1-2021 = " + between1);
    }

    @Test
    void DatesWeek() throws ParseException {
        var start = LocalDate.of(2020, Month.JANUARY, 1);
        var end = LocalDate.of(2020, Month.JANUARY, 31);
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.set(start.getYear(),Calendar.JANUARY,start.getDayOfMonth());

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);

        Date weekStart = c.getTime();
        // we do not need the same day a week after, that's why use 6, not 7
        c.add(Calendar.DAY_OF_MONTH, 6);
        Date weekEnd = c.getTime();
        var wDate = weekStart.toInstant().toString().split("T");
        System.out.println("weekStart = " +weekStart +"  weekEnd = " + weekEnd);
//      System.out.println("weekEnd = " + weekEnd);


    }
}
