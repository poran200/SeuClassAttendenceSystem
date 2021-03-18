package com.seu.edu.bd.cas.service;

import com.seu.edu.bd.cas.dto.AttendancesDto;
import com.seu.edu.bd.cas.model.ClassLog;
import com.seu.edu.bd.cas.model.Registration;
import com.seu.edu.bd.cas.model.Section;
import com.seu.edu.bd.cas.model.Student;
import com.seu.edu.bd.cas.repository.AttendanceRepository;
import com.seu.edu.bd.cas.repository.ClassLogRepository;
import com.seu.edu.bd.cas.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class AttendanceSheetService {
    private final SectionRepository sectionRepository;
    private  final ClassLogRepository classLogRepository;
    private  final AttendanceRepository attendanceRepository;


    public List<AttendancesDto> getAttendanceBySection(String sectionId){
        List<AttendancesDto> attendancesDtos = new ArrayList<>();
        Optional<Section> section = sectionRepository.findById(sectionId);
        final List<ClassLog> classLogs = classLogRepository.findAllBySection_SectionId(sectionId);
        if (section.isPresent()) {
            Set<Student> registerStudents = section.get()
                    .getRegisterStudents()
                    .stream()
                    .map(Registration::getStudent).collect(Collectors.toSet());
            for (Student s : registerStudents) {
                long countAttendance = getCountAttendance(classLogs,s);
                int totalClass = classLogs.size();
                long attendancePercentage =  countAttendance * 100 / totalClass;
                log.info("Student Name : " +s.getName()
                + " \ntotal attended : " +countAttendance
                + " \ntotal class : "+ totalClass);
                attendancesDtos.add(new AttendancesDto(s,attendancePercentage));
            }
        }

        return attendancesDtos;
    }

    private long getCountAttendance(List<ClassLog> classLogs, Student s) {
        long totalAttend = 0;
        for (ClassLog log :classLogs) {
           totalAttend+= attendanceRepository.countAllByClassLogAndStudent_IdAndAttendTrue(log,s.getId());

        }
        return totalAttend;
    }
}
