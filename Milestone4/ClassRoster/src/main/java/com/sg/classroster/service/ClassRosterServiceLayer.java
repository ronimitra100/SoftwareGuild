package com.sg.classroster.service;
import com.sg.classroster.dto.*;
import java.util.List;
import com.sg.classroster.dao.*;

public interface ClassRosterServiceLayer {
   
   void createStudent(Student student) throws
           ClassRosterDuplicateIdException,
           ClassRosterDataValidationException,
           ClassRosterPersistenceException;
   
   List<Student> getAllStudents() throws ClassRosterPersistenceException;
   
   Student getStudent(String studentId) throws ClassRosterPersistenceException;
   
   Student removeStudent(String studentId) throws ClassRosterPersistenceException;
   
}
