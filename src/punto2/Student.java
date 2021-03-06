/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package punto2;

import exceptions.StudentEnrollementException;
import exceptions.StudentRecordScoreException;
import java.util.ArrayList;

/**
 * Universidad del Valle
 * @author Julian Andres Cantillo // cod: 1431263 - 3743
 */
public class Student {
    
    private String code;
    private String name;
    private String career;
    private final ArrayList<Enrolment> enrolments;

    Student(String name, String code, String career) {
        this.code = code;
        this.name = name;
        this.career = career;
        this.enrolments = new ArrayList();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public ArrayList<Enrolment> getEnrolments() {
        return enrolments;
    }
    
    public void addCourseToEnrolments( String period, Course course ) throws StudentEnrollementException{
        
        if( !isEnrolledToCourse(period, course) ){
            this.enrolments.add( new Enrolment(period, course) );
        }else{
            throw new StudentEnrollementException(R.STR_STUDENT_IS_ALL_READY_ENROLLED);
        }
    }
    
    public void addCourseToEnrolments(Enrolment enrolment) throws StudentEnrollementException{
        if( !isEnrolledToCourse(enrolment.getPeriod(), enrolment.getCourse()) ){
            this.enrolments.add( enrolment );
        }else{
            throw new StudentEnrollementException(R.STR_STUDENT_IS_ALL_READY_ENROLLED);
        }
    }
    
    public boolean isEnrolledToCourse(String period, Course course){
        for (Enrolment enrolment : enrolments) {
            if(enrolment.getCourse().getCode().equals( course.getCode() ) && enrolment.getPeriod().equals(period) ){
                return true;
            }
        }
        
        return false;
    }
    
    public Enrolment getEnrolmentByCourse(String period, Course course){
        for (Enrolment enrolment : enrolments) {
            if(enrolment.getCourse().getCode().equals( course.getCode() ) && enrolment.getPeriod().equals(period) ){
                return enrolment;
            }
        }
        
        return null;
    }
    
    public String getEnrolledCoursesList(){
        String coursesList = "";
        Course course;
        
        for (Enrolment enrolment : enrolments) {
            course = enrolment.getCourse();
            coursesList += "[ "+ course.getCode() +" ] " + course.getName() + "\n";
        }
        
        return coursesList;
    }
    
    public String getEnrolledCoursesList(String period){
        String coursesList = "";
        Course course;
        
        for (Enrolment enrolment : enrolments) {
            if( enrolment.getPeriod().equals(period) ){
                course = enrolment.getCourse();
                coursesList += "[ "+ course.getCode() +" ] " + course.getName() + "\n";
            }
        }
        
        return coursesList;
    }
    
    public boolean recordScore(double score, String period, Course course) throws StudentRecordScoreException{
        Course c;
        for (Enrolment enrolment : enrolments) {
            c = enrolment.getCourse();
            if( c.getCode().equals( course.getCode() ) && enrolment.getPeriod().equals(period) ){
                enrolment.setScore(score);
                return true;
            }
        }
        
        throw new StudentRecordScoreException(R.STR_RECORD_SCORE);
    }

    @Override
    public String toString() {
        return code + " - " + name;
    }
    
     
    
}