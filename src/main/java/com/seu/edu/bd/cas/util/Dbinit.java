package com.seu.edu.bd.cas.util;

import com.seu.edu.bd.cas.model.*;
import com.seu.edu.bd.cas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Component
public class Dbinit {

   @Autowired
   SemesterRepository semesterRepository;
   @Autowired
   CourseRepository courseRepository;
   @Autowired
   FacultyRepository facultyRepository;
   @Autowired
   SectionRepository sectionRepository;
   @Autowired
   RegistrationRepository registrationRepository;
   @Autowired
   StudentRepository studentRepository;

   @PostConstruct
   public void postConstruct(){
      Calendar calendar = new GregorianCalendar();
      var start = LocalDate.of(2020, Month.JANUARY, 1);
      var end = LocalDate.of(2020, Month.JANUARY, 1);

      Semester semester = new Semester(1,"Spring",start,end,start.getYear()+"");
//      semesterRepository.save(semester);
      Student student1 = new Student("Poran Chowdury");
      Student student2 = new Student("Sha Jalal");
      Student student3 = new Student("Nazmul Hasan");
      var studentList = List.of(student1, student2, student3);
//      studentRepository.saveAll(studentList);

      Course course1 = new Course("CSE2014","Data Structures",3);
      Course course2 = new Course("CSE2013","Data Structures Lab",1);
//      courseRepository.save(course1);
//      courseRepository.save(course2);
      Faculty faculty = new Faculty("KMH","Monirul Hasan","abc","teacher",null);
//      facultyRepository.save(faculty);
      String sectionId = course1.getCourseCode()+"."+1+"."+semester.getId();
      String sectionId1 = course2.getCourseCode()+"."+1+"."+semester.getId();
      Section section = new Section(sectionId,10,semester,null,course1,faculty,"Room(502)",2);
      Section section2 = new Section(sectionId1,10,semester,null,course2,faculty,"Room(503)",1);
//      sectionRepository.save(section);
//      sectionRepository.save(section2);
      List<Registration> list = new ArrayList<>();

       list.add(new Registration(student1, section));
       list.add(new Registration(student1, section2));
       list.add(new Registration(student2, section));
       list.add( new Registration(student2, section2));
//      registrationRepository.saveAll(list);
      student1.setRegisterSections(Collections.singleton(new Registration(student1,section2)));
      student1.setId(1);
//      studentRepository.save(student1);
   }
}
