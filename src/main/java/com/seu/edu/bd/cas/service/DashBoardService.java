package com.seu.edu.bd.cas.service;

import com.seu.edu.bd.cas.dto.ClassLogSummaryDto;
import com.seu.edu.bd.cas.dto.DashBoardInfo;
import com.seu.edu.bd.cas.model.ClassLog;
import com.seu.edu.bd.cas.model.Section;
import com.seu.edu.bd.cas.model.Semester;
import com.seu.edu.bd.cas.repository.*;
import com.seu.edu.bd.cas.util.DateUtilWeek;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Log4j2
public class DashBoardService {

    private final FacultyRepository facultyRepository;
    private final SectionRepository sectionRepository;
    private final StudentRepository studentRepository;
    private final ClassLogRepository classLogRepository;
    private final SemesterRepository semesterRepository;

    public DashBoardInfo getClassLogSummary(String faciallyInitial){
        List<ClassLogSummaryDto> summaryDtoList = new ArrayList<>();
        var dashBoardInfo = getDashboardsInfo(faciallyInitial);
        var faculty = facultyRepository.findByInitial(faciallyInitial);
        if (faculty.isPresent()){
            var sectionIdSList = faculty.get().getSections();

            for ( Section section: sectionIdSList) {
                var summaryDto = new ClassLogSummaryDto();
                summaryDto.setSectionId(section.getSectionId());
                summaryDto.setScheduled(section.getClassPerWeek());
                summaryDto.setLogged(countLooged(section));
                summaryDto.setCourseTitle(section.getCourse().getTitle());
                var logs = classLogRepository.findAllBySection_SectionId(section.getSectionId());
                var totalDuration = logs.stream().mapToInt(ClassLog::getDuration).sum();
                summaryDto.setDurationTaken(totalDuration);
                summaryDto.setDurationToBeTaken(logs.size()*section.getDuration());
                var attendencePercentage = logs.stream().mapToInt(value -> 100 * (value.getTotalAttend() / value.getSection().getRegisterStudents().size())).sum();
                if (attendencePercentage != 0){
                    summaryDto.setAttendanceRate(attendencePercentage/logs.size());
                }
                summaryDtoList.add(summaryDto);
            }
        }
        dashBoardInfo.setSummary(summaryDtoList);
        return dashBoardInfo;
    }

    private Integer countLooged(Section section) {
        return classLogRepository.countClassLogBySection_SectionId(section.getSectionId());
    }

    public List<ClassLog> findByFaculty(String facultyInitial){
        var classLogs = classLogRepository.findAllBySection_Faculty_Initial(facultyInitial);
        return classLogs;
    }

    public List<ClassLogSummaryDto> getClassLogSummaryForDateOrWeek(String faciallyInitial, Date start ,Date end){
        List<ClassLogSummaryDto> summaryDtoList = new ArrayList<>();
        var faculty = facultyRepository.findByInitial(faciallyInitial);
        if (faculty.isPresent()){
            var sectionIdSList = faculty.get().getSections();

            for ( Section section: sectionIdSList) {
                var logs = classLogRepository.findAllBySection_SectionIdAndConductAtBetween(section.getSectionId(),start,end);
               log.error("class log : "+ logs);
               if (!logs.isEmpty()){
                   var summaryDto = new ClassLogSummaryDto();
                   summaryDto.setSectionId(section.getSectionId());
                   summaryDto.setScheduled(section.getClassPerWeek());
                   summaryDto.setLogged(logs.size());
                   summaryDto.setCourseTitle(section.getCourse().getTitle());
                   var totalDuration = logs.stream().mapToInt(ClassLog::getDuration).sum();
                   summaryDto.setDurationTaken(totalDuration);
                   summaryDto.setDurationToBeTaken(logs.size()*section.getDuration());
                   var attendencePercentage = logs.stream().mapToInt(value -> 100 * (value.getTotalAttend() / value.getSection().getRegisterStudents().size())).sum();
                   if (attendencePercentage != 0){
                       summaryDto.setAttendanceRate(attendencePercentage/logs.size());
                   }
                   summaryDtoList.add(summaryDto);
               }

            }
        }
        return summaryDtoList;
    }
    public DashBoardInfo getDashboardsInfo(String initial){
        var dashBoardInfo = new DashBoardInfo();
        var faculty = facultyRepository.findByInitial(initial);
        if (faculty.isPresent()){
            var sections = faculty.get().getSections();
            dashBoardInfo.setTotalSections(sections.size());
            AtomicInteger size = new AtomicInteger();
            sections.forEach(section -> size.set( size.get()+section.getRegisterStudents().size()));
            dashBoardInfo.setRegisterStudents(size.get());
            var classLogged = classLogRepository.countClassLogBySection_Faculty_Initial(initial);
            dashBoardInfo.setClassLogged(classLogged);
            var semester = semesterRepository.findById(1);
            if (semester.isPresent()){
                var dateUtilWeek = new DateUtilWeek(semester.get());
                System.out.println("dateUtilWeek = " + dateUtilWeek.geTotalWeek());
                var totalWeek = dateUtilWeek.geTotalWeek();
                var totalSchedule = sections.stream().mapToInt(sec -> sec.getClassPerWeek() * totalWeek).sum();
                dashBoardInfo.setScheduled(totalSchedule);
            }
        }
        System.out.println(dashBoardInfo.toString());
        return dashBoardInfo;
    }

}
