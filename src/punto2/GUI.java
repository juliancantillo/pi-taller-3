/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package punto2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Universidad del Valle
 * @author Julian Andres Cantillo // cod: 1431263 - 3743
 */
public class GUI extends JFrame implements ActionListener, MouseListener, ListSelectionListener{
    
    private JButton btnNewStudent, btnNewCourse;
    private JList lstStudents, lstCourses;
    private final JTabbedPane tabbedPane;
    
    private final String CMD_CREATE_NEW_STUDENT = "CREATE_NEW_STUDENT";
    private final String CMD_CREATE_NEW_COURSE = "CREATE_NEW_COURSE";
    private final String CMD_UPDATE_STUDENTS_LIST = "UPDATE_STUDENTS_LIST";
    private final String CMD_UPDATE_COURSES_LIST = "UPDATE_COURSES_LIST";

    public GUI() {
        super("Registro Academico");
                
        tabbedPane = new JTabbedPane();
        //tabbedPane.setLayout(new BorderLayout(R.HGAP, R.HGAP));
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(R.VGAP, R.HGAP, R.VGAP, R.HGAP));
        
        tabbedPane.add(R.STR_STUDENTS, pnlStudents());
        tabbedPane.add(R.STR_COURSES, pnlCourses());
        
        add(tabbedPane);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setSize(300, 400);
    }
    
    private JPanel pnlStudents(){
        JPanel panel = new JPanel(new BorderLayout(R.HGAP, R.VGAP));
        JToolBar studentsToolbar = new JToolBar();
        
        btnNewStudent = new JButton(R.STR_CREATE_STUDENT);
        btnNewStudent.setActionCommand(CMD_CREATE_NEW_STUDENT);
        btnNewStudent.addActionListener(this);
        
        JButton btnUpdateStudents = new JButton("Actualizar");
        btnUpdateStudents.setActionCommand(CMD_UPDATE_STUDENTS_LIST);
        btnUpdateStudents.addActionListener(this);
        
        lstStudents = new JList();
        lstStudents.setSize(300, 300);
        lstStudents.addListSelectionListener(this);
        lstStudents.addMouseListener(this);
        
        studentsToolbar.add(btnNewStudent);
        studentsToolbar.add(btnUpdateStudents);
        
        panel.add(studentsToolbar, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(lstStudents, BorderLayout.CENTER);
        
        return panel;
    }
    
    
    private JPanel pnlCourses(){
        JPanel panel = new JPanel(new BorderLayout(R.HGAP, R.VGAP));
        JToolBar studentsToolbar = new JToolBar();
        
        btnNewCourse = new JButton(R.STR_CREATE_COURSE);
        btnNewCourse.setActionCommand(CMD_CREATE_NEW_COURSE);
        btnNewCourse.addActionListener(this);
        
        JButton btnUpdateCourses = new JButton("Actualizar");
        btnUpdateCourses.setActionCommand(CMD_UPDATE_COURSES_LIST);
        btnUpdateCourses.addActionListener(this);
        
        lstCourses = new JList();
        lstCourses.setSize(300, 300);
        lstCourses.addListSelectionListener(this);
        lstCourses.addMouseListener(this);
        
        studentsToolbar.add(btnNewCourse);
        studentsToolbar.add(btnUpdateCourses);
        
        panel.add(studentsToolbar, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(lstCourses, BorderLayout.CENTER);
        
        return panel;
    }
    
    public void loadStudentsList(){
        lstStudents.setListData( Punto2.controller.getStudents().toArray() );
    }
    
    public void loadCoursesList(){
        lstCourses.setListData( Punto2.controller.getCourses().toArray() );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        
        if(cmd.equals(CMD_CREATE_NEW_STUDENT)){
            frmStudent frm = new frmStudent(this);
        }
        
        if(cmd.equals(CMD_CREATE_NEW_COURSE)){
            frmCourse frm = new frmCourse(this);
        }
        
        if(cmd.equals(CMD_UPDATE_STUDENTS_LIST)){
            this.loadStudentsList();
        }
        
        if(cmd.equals(CMD_UPDATE_COURSES_LIST)){
            this.loadCoursesList();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == lstStudents && e.getClickCount() >= 2){
            Student tmp = (Student) lstStudents.getSelectedValue();
            frmStudent frm = new frmStudent(this, tmp);
        }
        
        if(e.getSource() == lstCourses && e.getClickCount() >= 2){
            Course tmp = (Course) lstCourses.getSelectedValue();
            frmCourse frm = new frmCourse(this, tmp);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    
    
}
