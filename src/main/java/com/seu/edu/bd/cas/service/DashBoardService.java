package com.seu.edu.bd.cas.service;

import com.seu.edu.bd.cas.dto.ClassLogSummaryDto;
import com.seu.edu.bd.cas.dto.DashBoardInfo;
import com.seu.edu.bd.cas.model.ClassLog;
import com.seu.edu.bd.cas.model.Faculty;
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
        DashBoardInfo dashBoardInfo = getDashboardsInfo(faciallyInitial);
        Optional<Faculty> faculty = facultyRepository.findByInitial(faciallyInitial);
        if (faculty.isPresent()){
            Set<Section> sectionIdSList = faculty.get().getSections();

            for ( Section section: sectionIdSList) {
                ClassLogSummaryDto summaryDto = new ClassLogSummaryDto();
                summaryDto.setSectionId(section.getSectionId());
                summaryDto.setScheduled(section.getClassPerWeek());
                summaryDto.setLogged(countLooged(section));
                summaryDto.setCourseTitle(section.getCourse().getTitle());
                List<ClassLog> logs = classLogRepository.findAllBySection_SectionId(section.getSectionId());
                int totalDuration = logs.stream().mapToInt(ClassLog::getDuration).sum();
                summaryDto.setDurationTaken(totalDuration);
                summaryDto.setDurationToBeTaken(logs.size()*section.getDuration());
                int attendencePercentage = logs.stream().mapToInt(value -> 100 * (value.getTotalAttend() / value.getSection().getRegisterStudents().size())).sum();
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
        List<ClassLog> classLogs = classLogRepository.findAllBySection_Faculty_Initial(facultyInitial);
        return classLogs;
    }

    public List<ClassLogSummaryDto> getClassLogSummaryForDateOrWeek(String faciallyInitial, Date start ,Date end){
        List<ClassLogSummaryDto> summaryDtoList = new ArrayList<>();
        Optional<Faculty> faculty = facultyRepository.findByInitial(faciallyInitial);
        if (faculty.isPresent()){
            Set<Section> sectionIdSList = faculty.get().getSections();

            for ( Section section: sectionIdSList) {
                List<ClassLog> logs = classLogRepository.findAllBySection_SectionIdAndConductAtBetween(section.getSectionId(),start,end);
               log.error("class log : "+ logs);
               if (!logs.isEmpty()){
                   ClassLogSummaryDto summaryDto = new ClassLogSummaryDto();
                   summaryDto.setSectionId(section.getSectionId());
                   summaryDto.setScheduled(section.getClassPerWeek());
                   summaryDto.setLogged(logs.size());
                   summaryDto.setCourseTitle(section.getCourse().getTitle());
                   int totalDuration = logs.stream().mapToInt(ClassLog::getDuration).sum();
                   summaryDto.setDurationTaken(totalDuration);
                   summaryDto.setDurationToBeTaken(logs.size()*section.getDuration());
                   int attendencePercentage = logs.stream().mapToInt(value -> 100 * (value.getTotalAttend() / value.getSection().getRegisterStudents().size())).sum();
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
        DashBoardInfo dashBoardInfo = new DashBoardInfo();
        Optional<Faculty> faculty = facultyRepository.findByInitial(initial);
        if (faculty.isPresent()){
            Set<Section> sections = faculty.get().getSections();
            dashBoardInfo.setTotalSections(sections.size());
            AtomicInteger size = new AtomicInteger();
            sections.forEach(section -> size.set( size.get()+section.getRegisterStudents().size()));
            dashBoardInfo.setRegisterStudents(size.get());
            int classLogged = classLogRepository.countClassLogBySection_Faculty_Initial(initial);
            dashBoardInfo.setClassLogged(classLogged);
            Optional<Semester> semester = semesterRepository.findById(1);
            if (semester.isPresent()){
                DateUtilWeek dateUtilWeek = new DateUtilWeek(semester.get());
                System.out.println("dateUtilWeek = " + dateUtilWeek.geTotalWeek());
                int totalWeek = dateUtilWeek.geTotalWeek();
                int totalSchedule = sections.stream().mapToInt(sec -> sec.getClassPerWeek() * totalWeek).sum();
                dashBoardInfo.setScheduled(totalSchedule);
            }
        }
        System.out.println(dashBoardInfo.toString());
        return dashBoardInfo;
    }

}
