/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package punto2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import punto2.guihelpers.GBHelper;
import punto2.guihelpers.Gap;

/**
 * Universidad del Valle
 * @author Julian Andres Cantillo // cod: 1431263 - 3743
 */
public class frmCourse extends JDialog implements ActionListener{

    private JLabel lblName, lblCode, lblCredits;
    private JTextField fldName, fldCode, fldCredits;
    private JButton btnClose, btnSave, btnLoadStudents;
    private Course course;
    private JList lstStudents;
    private final Frame owner;
    private final boolean added;
    
    public frmCourse(Frame owner, Course course){
        super(owner, R.STR_COURSES); 
        this.owner = owner;
        this.course = course;
        this.added = true;
        
        initDialog();
        showCourseInfo();
    }

    public frmCourse(Frame owner) {
        super(owner, R.STR_COURSES);
        this.owner = owner;
        
        this.course = new Course(null, null, 0);
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
        
        lblName = new JLabel(R.STR_CREATE_COURSE_NAME);
        lblCode = new JLabel(R.STR_CREATE_COURSE_CODE);
        lblCredits= new JLabel(R.STR_CREATE_COURSE_CREDITS);
        
        fldName = new JTextField(15);
        fldCode = new JTextField(15);
        fldCredits = new JTextField(15);
                
        GBHelper pos = new GBHelper();
        
        pnl.add(lblName, pos.nextCol());
        pnl.add(new Gap(R.HGAP), pos.nextCol());
        pnl.add(fldName, pos.nextCol().expandW());
        
        pnl.add(new Gap(R.VGAP), pos.nextRow());
        
        pnl.add(lblCode, pos.nextRow().nextCol());
        pnl.add(new Gap(R.HGAP), pos.nextCol());
        pnl.add(fldCode, pos.nextCol().expandW());
        
        pnl.add(new Gap(R.VGAP) , pos.nextRow());
        
        pnl.add(lblCredits, pos.nextRow().nextCol());
        pnl.add(new Gap(R.HGAP), pos.nextCol());
        pnl.add(fldCredits, pos.nextCol().expandW());
        
        pnl.add(new Gap(R.VGAP) , pos.nextRow()); 
        
        return pnl;
    }
    
    private JPanel pnlButtons(){
        JPanel pnl = new JPanel();
        pnl.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        btnClose = new JButton(R.STR_CANCEL);
        btnSave = new JButton(R.STR_SAVE);
        btnLoadStudents = new JButton("Ver estudiantes");
        
        btnClose.setActionCommand("CMD_CLOSE");
        btnSave.setActionCommand("CMD_SAVE");
        btnLoadStudents.setActionCommand("CMD_LOAD");
        
        btnClose.addActionListener(this);
        btnSave.addActionListener(this);
        btnLoadStudents.addActionListener(this);
        
        pnl.add(btnLoadStudents);
        pnl.add(btnSave);
        pnl.add(btnClose);
        
        return pnl;
    }
    
    private JPanel pnlStudents(){
        JPanel panel = new JPanel(new BorderLayout(R.HGAP, R.VGAP));
        
        lstStudents = new JList();
        lstStudents.setSize(300, 300);
        
        panel.add(lstStudents, BorderLayout.CENTER);
        
        return panel;
    }
    
    public void loadCourse(Course cs){
        this.course = cs;
        showCourseInfo();
    }
    
    public void loadStudents(){
        String tmp = JOptionPane.showInputDialog(this, R.STR_PERIOD);
        JDialog dlgStudents = new JDialog();
        JPanel pnl = pnlStudents();
        dlgStudents.setLocationRelativeTo(this);
        dlgStudents.add(pnl);
        dlgStudents.setSize(300, 300);
        
        lstStudents.setListData( Punto2.controller.listEnrolledStudentsToCourse(course, tmp).toArray() );
        dlgStudents.setVisible(true);
        dlgStudents.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    private void close(){
        setVisible(false);
        dispose();
    }
    
    private void showCourseInfo(){
        fldName.setText( this.course.getName() );
        fldCode.setText( this.course.getCode() );
        fldCredits.setText( String.valueOf( this.course.getCredits() ) );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        
        if(cmd.equals(btnSave.getActionCommand())){
            
            course.setName(fldName.getText());
            course.setCode(fldCode.getText());
            course.setCredits( Integer.parseInt(fldCredits.getText()) );
            
            if(!added)
                Punto2.controller.createCourse(course);
            
            close();
        }
        if(cmd.equals(btnClose.getActionCommand())){
            close();
        }
        
        if(cmd.equals(btnLoadStudents.getActionCommand())){
            loadStudents();
        }
        
    }
    
}
