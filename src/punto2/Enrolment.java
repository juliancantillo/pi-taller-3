/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package punto2;

import exceptions.EnrollmentRecordScoreException;

/**
 *
 * @author Usuario
 */
public class Enrolment {
    
    private String period;
    private Course course;
    private double score;

    public Enrolment(String period, Course course) {
        this.period = period;
        this.course = course;
    }
    
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    public void recordScore(String stringScore) throws EnrollmentRecordScoreException{
        Double tmpScore = 0.0;
        
        if(!"".equals(stringScore)){
            tmpScore = Double.parseDouble( stringScore );
        }
        
        if(tmpScore > 5){
            throw new EnrollmentRecordScoreException(R.STR_ENROLMENT_RECORD_GREATER_THAN_FIVE);
        }
        else if(tmpScore < 0){
            throw new EnrollmentRecordScoreException(R.STR_ENROLMENT_RECORD_LOWER_THAN_ZERO);
        }
        else if( Double.isNaN(tmpScore) ){
            throw new EnrollmentRecordScoreException(R.STR_ENROLMENT_RECORD_NAN);
        }else{
            this.setScore(tmpScore);
        }
    }

    @Override
    public String toString() {
        return "(" + course.getCode() + "-" + period  + ") " + course.getName() + " " + score;
    }
    
    
    
}
