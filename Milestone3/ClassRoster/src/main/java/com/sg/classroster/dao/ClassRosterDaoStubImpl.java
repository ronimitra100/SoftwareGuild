/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronim_000
 */
public class ClassRosterDaoStubImpl implements ClassRosterDao{
    Student onlyStudent;
    List<Student> studentList = new ArrayList<Student>();
    
    public ClassRosterDaoStubImpl(){
        onlyStudent = new Student("0001");
        onlyStudent.setFirstName("John");
        onlyStudent.setLastName("Doe");
        onlyStudent.setCohort("Java-Jan-2015");
        studentList.add(onlyStudent);
    }

    public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())){
            return onlyStudent;
        }
        else{
            return null;
        }
    }

    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return studentList;
    }

    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
         if (studentId.equals(onlyStudent.getStudentId())){
            return onlyStudent;
        }
        else{
            return null;
        }
    }

    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())){
            return onlyStudent;
        }
        else{
            return null;
        }
    }
    
}
