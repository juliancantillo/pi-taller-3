/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author invitado
 */
public class StudentEnrollementException extends Exception{

    public StudentEnrollementException(String message) {
        super(message);
        
        System.out.println(message);
    }
        
}

