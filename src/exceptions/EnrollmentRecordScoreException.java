/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author invitado
 */
public class EnrollmentRecordScoreException extends Exception{

    public EnrollmentRecordScoreException(String message) {
        super(message);
        
        System.out.println(message);
    }
        
}

