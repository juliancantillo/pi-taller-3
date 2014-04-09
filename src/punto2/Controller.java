/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package punto2;

import java.util.ArrayList;

/**
 * Universidad del Valle
 * @author Julian Andres Cantillo // cod: 1431263 - 3743
 * 
 * This class controls all the entitys and join the models with the GUI
 */
public class Controller {
    
    private final ArrayList<Student> students;
    private final ArrayList<Course> courses;

    /**
     * Constructor
     * Initializes all the ArrayList
     */
    public Controller() {
        students = new ArrayList();
        courses = new ArrayList();
    }
    
    /**
     * Create and student object and add it into the ArrayList of Students
     * @param name
     * @param code
     * @param career
     */
    public void createStudent(String name, String code, String career){
       
        //Create the object with the variables captured above
        Student student = new Student( name, code, career );
        //The new object is added in the ArrayList
        students.add( student );
        
        System.out.println(student);
    }
    
    public void createStudent(Student student){
        //The new object is added in the ArrayList
        students.add( student );
        
        System.out.println(student);
    }
    
    /**
     * Create a Course object and add it into the ArrayList of courses
     * @param name
     * @param code
     * @param credits
     */
    public void createCourse(String name, String code, String credits){
        int cred = Integer.parseInt( credits );
        
        //Create the object with the variables captured above
        Course course = new Course(name, code, cred);
        //The new object is added in the ArrayList
        courses.add( course );
        
        System.out.println(course);
    }
    
    /**
    * Create a Course object and add it into the ArrayList of courses
    * @param course
    */
    public void createCourse(Course course){
        
        //The new object is added in the ArrayList
        courses.add( course );
        
        System.out.println(course);
    }
    
    /**
     * Find a Student with the given code, this uses for iterator in order to navigate all the ArrayList
     * @param code
     * @return Student
     */
    public Student findStudentByCode( String code ){
        Student temporal = null;
        
        for (Student student : students) {
            System.out.println( "Comparando " + student.getCode() + " con " + code );
            //This function is using Regex, so it can be improved
            if(student.getCode().matches( "^" + code + "$" )){
                return student;
            }
        }
        
        return null;
    }
    
    /**
     * Find a Course with the given code, this uses for iterator in order to navigate all the ArrayList
     * @param code
     * @return Course
     */
    public Course findCourseByCode( String code ){
        Course temporal = null;
        
        for (Course course : courses) {
            System.out.println( "Comparando " + course.getCode() + " con " + code );
            if(course.getCode().matches( "^" + code + "$" )){
                return course;
            }
        }
        
        return null;
    }
    
    public ArrayList listEnrolledStudentsToCourse(Course course, String period){
        String studentList = "";
        Enrolment enrolment;
        ArrayList array = new ArrayList();
        
        if( course != null ){
            for (Student student : students) {
                if( student.isEnrolledToCourse(period, course) ){
                    enrolment = student.getEnrolmentByCourse(period, course);
                    
                    array.add(student);
                }
            }
        }
        
        return array;
    }
    
    public void testing(){
        Student s1 = new Student("Julian", "31263", "Ingenieria de Sistemas");
        Student s2 = new Student("Andres", "54321", "Ingenieria de Materiales");
        
        Course c1 = new Course("Progamación Interctiva", "PI001", 3);
        Course c2 = new Course("Algebra Lineal", "AL001", 3);
        
        courses.add(c1);
        courses.add(c2);
        
        students.add(s1);
        students.add(s2);

        s1.addCourseToEnrolments("2014", c2);
        s1.addCourseToEnrolments("2014", c1);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
    
}
