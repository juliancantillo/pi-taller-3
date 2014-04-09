/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package punto2;

import exceptions.EnrollmentRecordScoreException;
import exceptions.StudentEnrollementException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import punto2.guihelpers.GBHelper;
import punto2.guihelpers.Gap;

/**
 * Universidad del Valle
 * @author Julian Andres Cantillo // cod: 1431263 - 3743
 */
public class frmEnrolment extends JDialog implements ActionListener{

    private JLabel lblPeriod, lblCourse, lblScore;
    private JTextField fldPeriod, fldScore;
    private JComboBox slctCourse;
    private JButton btnAccept, btnClose, btnSave;
    private Enrolment enrolment;
    private final Student student;
    private final boolean added;
    private final Frame owner;
    
    public frmEnrolment(Frame owner, Student student, Enrolment enrolment){
        super(owner, R.STR_ENROLMENT);
        this.owner = owner;
        this.student = student;
        this.enrolment = enrolment;
        this.added = true;
        
        initDialog();
        showEnrolmentInfo();
    }

    public frmEnrolment(Frame owner, Student student) {
        super(owner, R.STR_STUDENTS);
        this.owner = owner;
        this.student = student;
        this.enrolment = new Enrolment(null, null);
        this.added = false;
        
        initDialog();
    }
    
    private void initDialog(){
        JPanel pnl = new JPanel();
        pnl.setLayout(new BorderLayout(R.HGAP, R.HGAP));
        pnl.setBorder(BorderFactory.createEmptyBorder(R.VGAP, R.HGAP, R.VGAP, R.HGAP));
        
        pnl.add(pnlMain(), BorderLayout.CENTER );
        pnl.add(pnlButtons(), BorderLayout.SOUTH );
        
        add(pnl);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(owner);
        setVisible(true);
        setResizable(false);
        pack();
    }
    
    private JPanel pnlMain(){
        JPanel pnl = new JPanel();
        pnl.setLayout( new GridBagLayout() );
        
        lblPeriod = new JLabel(R.STR_ENROLMENT_PERIOD);
        lblCourse = new JLabel(R.STR_ENROLMENT_COURSE);
        lblScore= new JLabel(R.STR_ENROLMENT_SCORE);
        
        fldPeriod = new JTextField(15);
        fldScore = new JTextField(15);
        
        slctCourse = new JComboBox( Punto2.controller.getCourses().toArray() );
                
        GBHelper pos = new GBHelper();
        
        pnl.add(lblPeriod, pos.nextCol());
        pnl.add(new Gap(R.HGAP), pos.nextCol());
        pnl.add(fldPeriod, pos.nextCol().expandW());
        
        pnl.add(new Gap(R.VGAP), pos.nextRow());
        
        pnl.add(lblCourse, pos.nextRow().nextCol());
        pnl.add(new Gap(R.HGAP), pos.nextCol());
        pnl.add(slctCourse, pos.nextCol().expandW());
        
        pnl.add(new Gap(R.VGAP) , pos.nextRow());
        
        pnl.add(lblScore, pos.nextRow().nextCol());
        pnl.add(new Gap(R.HGAP), pos.nextCol());
        pnl.add(fldScore, pos.nextCol().expandW());
        
        pnl.add(new Gap(R.VGAP) , pos.nextRow());
        
        
        
        return pnl;
    }
    
    private JPanel pnlButtons(){
        JPanel pnl = new JPanel();
        pnl.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        btnAccept = new JButton(R.STR_ACCEPT);
        btnClose = new JButton(R.STR_CANCEL);
        btnSave = new JButton(R.STR_SAVE);
        
        btnAccept.setActionCommand("CMD_ACCEPT");
        btnClose.setActionCommand("CMD_CLOSE");
        btnSave.setActionCommand("CMD_SAVE");
        
        btnAccept.addActionListener(this);
        btnClose.addActionListener(this);
        btnSave.addActionListener(this);
        
        pnl.add(btnAccept);
        pnl.add(btnSave);
        pnl.add(btnClose);
        
        return pnl;
    }
    
    public void loadEnrolment(Enrolment cs){
        this.enrolment = cs;
        showEnrolmentInfo();
    }
    
    private void close(){
        setVisible(false);
        dispose();
    }
    
    private void showEnrolmentInfo(){
        fldPeriod.setText( this.enrolment.getPeriod() );
        fldScore.setText( String.valueOf(this.enrolment.getScore()));
        slctCourse.setSelectedItem( this.enrolment.getCourse().toString() );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        
        if(cmd.equals(btnAccept.getActionCommand())){
            close();
        }
        if(cmd.equals(btnSave.getActionCommand())){
            
            enrolment.setPeriod( fldPeriod.getText() );
            enrolment.setCourse( (Course) slctCourse.getSelectedItem() );
            try {
                enrolment.recordScore( fldScore.getText() );
            } catch (EnrollmentRecordScoreException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            
            if(!added){
                try {
                    student.addCourseToEnrolments(enrolment);
                    JOptionPane.showMessageDialog(this, R.STR_STUDENT_HAVE_BEEN_ENROLLED);
                } catch (StudentEnrollementException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
            
            close();
        }
        if(cmd.equals(btnClose.getActionCommand())){
            close();
        }
    }
    
}
