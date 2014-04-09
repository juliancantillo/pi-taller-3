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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import punto2.guihelpers.GBHelper;
import punto2.guihelpers.Gap;

/**
 * Universidad del Valle
 * @author Julian Andres Cantillo // cod: 1431263 - 3743
 */
public class frmStudent extends JDialog implements ActionListener, MouseListener{

    private JLabel lblName, lblCode, lblCareer;
    private JTextField fldName, fldCode;
    private JComboBox slctCarrer;
    private JButton btnAccept, btnClose, btnSave, btnNewEnrolment;
    private JList lstEnrolments;
    private Student student;
    private final String[] carrers = {"Ingenieria de Sistemas","Ingenieria de Electrónica", "Ingenieria de Materiales", "Ingenieria Quimica"};
    private final boolean added;
    private final String CMD_UPDATE_ENROLMENT_LIST = "UPDATE_ENROLMENTS";
    private final String CMD_CREATE_NEW_ENROLMENT = "NEW_ENROLMENT";
    private final Frame owner;
    
    public frmStudent(Frame owner, Student student){
        super(owner, R.STR_STUDENTS);
        this.owner = owner;
        
        this.student = student;
        this.added = true;
        
        initDialog();
        showStudentInfo();
    }

    public frmStudent(Frame owner) {
        super(owner, R.STR_STUDENTS);
        this.owner = owner;
        
        this.student = new Student(null, null, null);
        this.added = false;
        
        initDialog();
    }
    
    private void initDialog(){
        JPanel pnl = new JPanel();
        pnl.setLayout(new BorderLayout(R.HGAP, R.HGAP));
        pnl.setBorder(BorderFactory.createEmptyBorder(R.VGAP, R.HGAP, R.VGAP, R.HGAP));
        
        JTabbedPane tabs = new JTabbedPane();
        
        tabs.add(R.STR_CREATE_STUDENT_INFO, pnlMain());
        tabs.add(R.STR_STUDENT_ENROLMENTS, pnlEnrolments());
        
        pnl.add(tabs, BorderLayout.CENTER );
        pnl.add(pnlButtons(), BorderLayout.SOUTH );
        
        add(pnl);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(owner);
        setVisible(true);
        setResizable(false);
        setSize(450,400);
    }
    
    private JPanel pnlMain(){
        JPanel pnl = new JPanel();
        pnl.setLayout( new GridBagLayout() );
        
        lblName = new JLabel(R.STR_CREATE_STUDENT_NAME);
        lblCode = new JLabel(R.STR_CREATE_STUDENT_CODE);
        lblCareer= new JLabel(R.STR_CREATE_STUDENT_CAREER);
        
        fldName = new JTextField(15);
        fldCode = new JTextField(15);
        slctCarrer = new JComboBox(carrers);
                
        GBHelper pos = new GBHelper();
        
        pnl.add(lblName, pos.nextCol());
        pnl.add(new Gap(R.HGAP), pos.nextCol());
        pnl.add(fldName, pos.nextCol().expandW());
        
        pnl.add(new Gap(R.VGAP), pos.nextRow());
        
        pnl.add(lblCode, pos.nextRow().nextCol());
        pnl.add(new Gap(R.HGAP), pos.nextCol());
        pnl.add(fldCode, pos.nextCol().expandW());
        
        pnl.add(new Gap(R.VGAP) , pos.nextRow());
        
        pnl.add(lblCareer, pos.nextRow().nextCol());
        pnl.add(new Gap(R.HGAP), pos.nextCol());
        pnl.add(slctCarrer, pos.nextCol().expandW());
        
        pnl.add(new Gap(), pos.nextRow().expandH());
        return pnl;
    }
    
    private JPanel pnlEnrolments(){
        JPanel panel = new JPanel(new BorderLayout(R.HGAP, R.VGAP));
        JToolBar studentsToolbar = new JToolBar();
        
        btnNewEnrolment= new JButton(R.STR_CREATE_ENROLMENT);
        btnNewEnrolment.setActionCommand(CMD_CREATE_NEW_ENROLMENT);
        btnNewEnrolment.addActionListener(this);
        
        JButton btnUpdateCourses = new JButton("Actualizar");
        btnUpdateCourses.setActionCommand(CMD_UPDATE_ENROLMENT_LIST);
        btnUpdateCourses.addActionListener(this);
        
        lstEnrolments = new JList();
        lstEnrolments.setSize(300, 300);
        lstEnrolments.addMouseListener(this);
        
        studentsToolbar.add(btnNewEnrolment);
        studentsToolbar.add(btnUpdateCourses);
        
        panel.add(studentsToolbar, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(lstEnrolments, BorderLayout.CENTER);
        
        return panel;
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
    
    public void loadStudent(Student st){
        this.student = st;
        showStudentInfo();
    }
    
    private void close(){
        setVisible(false);
        dispose();
    }
    
    private void showStudentInfo(){
        fldName.setText( this.student.getName() );
        fldCode.setText( this.student.getCode() );
        slctCarrer.setSelectedItem( this.student.getCareer() );
        lstEnrolments.setListData( student.getEnrolments().toArray() );
    }
    
    private void addEnrolment(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        
        if(cmd.equals(btnAccept.getActionCommand())){
            close();
        }
        if(cmd.equals(btnSave.getActionCommand())){
            
            student.setName(fldName.getText());
            student.setCode(fldCode.getText());
            student.setCareer( (String) slctCarrer.getSelectedItem());
            
            if(!added)
                Punto2.controller.createStudent(student);
            
            close();
        }
        if(cmd.equals(btnClose.getActionCommand())){
            close();
        }
        
        if(cmd.equals(CMD_CREATE_NEW_ENROLMENT)){
            frmEnrolment frm = new frmEnrolment(owner, student);
        }
        
        if(cmd.equals(CMD_UPDATE_ENROLMENT_LIST)){
            lstEnrolments.setListData( student.getEnrolments().toArray() );
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == lstEnrolments && e.getClickCount() >= 2){
            Enrolment tmp = (Enrolment) lstEnrolments.getSelectedValue();
            frmEnrolment frm = new frmEnrolment(owner, student, tmp);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
