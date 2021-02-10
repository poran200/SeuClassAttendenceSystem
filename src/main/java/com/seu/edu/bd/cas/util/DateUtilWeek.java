package com.seu.edu.bd.cas.util;

import com.seu.edu.bd.cas.dto.Week;
import com.seu.edu.bd.cas.model.Semester;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Log4j2
@Data
public final class DateUtilWeek {
    private Map<Integer, Week> weekMap = new HashMap<>();
    private Semester semester;

    public DateUtilWeek(Semester semester) {
        this.semester = semester;
        this.getWeek(1);
    }

    public  Week getWeek(int nOfWeek){


        LocalDate start = semester.getStartDate();
        LocalDate end = semester.getEndDate();
        Date date = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date1 = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(date1);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
//        Map<Integer, Week> weekMap = new HashMap<>();
        int  weekCount = 1;
        while (c.before(cEnd)){
            Date weekStart = c.getTime();
            // we do not need the same day a week after, that's why use 6, not 7
            c.add(Calendar.DAY_OF_MONTH, 6);
            Date weekEnd = c.getTime();
            weekMap.put(weekCount,new Week(weekStart,weekEnd));
            c.add(Calendar.DAY_OF_MONTH, 1);
            weekCount++;
        }

//        System.out.println("weekMap = " + weekMap.toString());
        Week week = weekMap.get(nOfWeek);
        log.info("week = " + week.toString());
        return  week;
    }
    public int geTotalWeek(){
      return   weekMap.size();
    }

}
