package com.sg.classroster.ui;

import com.sg.classroster.dto.*;
import java.util.List;

public class ClassRosterView {

    UserIO io;

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Student IDs");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices", 1, 5);
    }

    public ClassRosterView(UserIO io) {
        this.io = io;
    }

    public Student getNewStudentInfo() {
        String studentId = io.readString("Please enter Student ID");
        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String cohort = io.readString("Please enter Cohort");
        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);
        return currentStudent;
    }

    public void displayCreateStudentBanner() {
        io.print("==Create Student==");
    }

    public void displayCreateSuccessBanner() {
        io.print("Student successfully created. Please hit enter to continue");
    }

    public void displayStudentList(List<Student> studentList) {
        for (Student currentStudent : studentList) {
            io.print(currentStudent.getStudentId() + ":"
                    + currentStudent.getFirstName() + ":"
                    + currentStudent.getLastName() + ":"
                    + currentStudent.getCohort());
        }
        io.readString("Please hit enter to continue");
    }

    public void displayDiplayAllBanner() {
        io.print("=== Display All Students ===");
    }

    public void displayDisplayStudentBanner() {
        io.print("=== Display Student ===");
    }

    public String getStudentIdChoice() {
        return io.readString("Please enter the Student ID");
    }

    public void displayStudent(Student student) {
        if (student != null) {
            io.print(student.getStudentId());
            io.print(student.getFirstName() + " " + student.getLastName());
            io.print(student.getCohort());
            io.print(" ");
        } else {
            io.print("No such student.");
        }
        io.readString("Please enter hit to continue");
    }

    public void displayRemoveStudentBanner() {
        io.print("=== Remove Student ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Student successfully removed. Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown command");
    }

    public void displayErrorMessage(String errMsg) {
        io.print("=== ERROR ===");
        io.print(errMsg);
    }
}
