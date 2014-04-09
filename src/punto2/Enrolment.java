/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package punto2;

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

    @Override
    public String toString() {
        return "(" + course.getCode() + "-" + period  + ") " + course.getName() + " " + score;
    }
    
    
    
}
