package com.seu.edu.bd.cas.service;

import com.seu.edu.bd.cas.exeption.ResourceNotFoundExption;
import com.seu.edu.bd.cas.exeption.SectionNOtFoundException;
import com.seu.edu.bd.cas.model.Attendance;
import com.seu.edu.bd.cas.model.ClassLog;
import com.seu.edu.bd.cas.model.Registration;
import com.seu.edu.bd.cas.model.Student;
import com.seu.edu.bd.cas.repository.AttendanceRepository;
import com.seu.edu.bd.cas.repository.ClassLogRepository;
import com.seu.edu.bd.cas.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassLogService {
    private final ClassLogRepository classLogRepository;
    private final SectionRepository sectionRepository;
    private final AttendanceRepository attendanceRepository;


    public ClassLog save( String sectionId, List<Student> students) throws SectionNOtFoundException {
        var saveLog = new ClassLog();
        saveLog.setTotalAttend(students.size());
        var section = sectionRepository.findById(sectionId);
        if (section.isPresent()){
            saveLog.setSection(section.get());
            var studentSet = section.get().getRegisterStudents()
                    .stream()
                    .map(Registration::getStudent)
                     .collect(Collectors.toSet());
            for (Student s : studentSet) {
                var studentOptional = students.stream()
                        .filter(student -> s.getId() == student.getId())
                        .findFirst();
                Attendance attendance;
                if (studentOptional.isPresent()){
                    attendance = attendanceRepository.save(new Attendance(s, true));
                }else {
                    attendance = attendanceRepository.save(new Attendance(s, false));
                }
                saveLog.addAttendance(attendance);
            }
            return classLogRepository.save(saveLog);

        }

      throw  new SectionNOtFoundException("section not found : "+sectionId);
    }
    public ClassLog update(long classLogId , int duration, Date conductedAt, String status) throws SectionNOtFoundException {

        var optionalClassLog = classLogRepository.findById(classLogId);

        if (optionalClassLog.isPresent()){

            var classLog = optionalClassLog.get();
            classLog.setStatus(status);
            classLog.setConductAt(conductedAt);
            classLog.setDuration(duration);
           return classLogRepository.save(classLog);

        }

        throw  new SectionNOtFoundException("class  log  not found Id: "+classLogId);
    }


    public ClassLog findById(long id) throws ResourceNotFoundExption {
        var byId = classLogRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }else {
         throw   new ResourceNotFoundExption("class log not found Id :"+ id);
        }
    }
}
