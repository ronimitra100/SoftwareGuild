package com.sg.classmodeling;

public class School2 {
    private int enrollment;
    private int numberOfTeachers;
    private String[] courseOffered;
    private String sportsNickName;
    private String[] clubsOffered;
    private Student[] studentRoster;
    
    public void enrollStudent(Student student){
    }
    
    public void unenrollStudent(Student student){
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public int getNumberOfTeachers() {
        return numberOfTeachers;
    }

    public void setNumberOfTeachers(int numberOfTeachers) {
        this.numberOfTeachers = numberOfTeachers;
    }

    public String[] getCourseOffered() {
        return courseOffered;
    }

    public void setCourseOffered(String[] courseOffered) {
        this.courseOffered = courseOffered;
    }

    public String getSportsNickName() {
        return sportsNickName;
    }

    public void setSportsNickName(String sportsNickName) {
        this.sportsNickName = sportsNickName;
    }

    public String[] getClubsOffered() {
        return clubsOffered;
    }

    public void setClubsOffered(String[] clubsOffered) {
        this.clubsOffered = clubsOffered;
    }

    public Student[] getStudentRoster() {
        return studentRoster;
    }

    public void setStudentRoster(Student[] studentRoster) {
        this.studentRoster = studentRoster;
    }
    
    
}
