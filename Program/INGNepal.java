//importing swing and awt libraries
import javax.swing.JFrame;
import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class INGNepal implements ActionListener {
    //initilaizing global variables and swing components
    private boolean partTimeWindowCheck, fullTimeWindowCheck = false;
    private JFrame frameMainMenu, framePartTimeStaffHire, frameFullTimeStaffHire;
    private JTextField tfPartTimeName, tfPartTimeDesignation, tfPartTimeJobType, tfPartTimeQualification, tfPartTimeWorkingHour, tfPartTimeWagesPerHour, tfPartTimeShifts, tfPartTimeJoiningDate, tfPartTimeAppointedBy,tfPartTimeVacancyNumberVacancyPanel, tfPartTimeVacancyNumberAppointPanel, tfPartTimeVacancyNumberDisplayPanel;
    private JTextField tfFullTimeName, tfFullTimeDesignation, tfFullTimeJobType, tfFullTimeQualification, tfFullTimeWorkingHour, tfFullTimeSalary, tfFullTimeJoiningDate, tfFullTimeAppointedBy, tfFullTimeVacancyNumberVacancyPanel, tfFullTimeVacancyNumberAppointPanel, tfFullTimeVacancyNumberDisplayPanel;

    private JTable staffTable, vacancyTable;
    private DefaultTableModel dtmStaff, dtmVacancy;
    private ArrayList <StaffHire> arrayListStaffHire = new ArrayList <StaffHire>();
    private JTabbedPane tpPartTimeForm, tpFullTimeForm;

    INGNepal () {
        //setting up frame for main menu
        frameMainMenu = new JFrame("ING Nepal");
        frameMainMenu.setSize(600, 600);
        frameMainMenu.setLocationRelativeTo(null);
        frameMainMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameMainMenu.setResizable(true);
        //using windowlistener to set states and check certain conditions
        frameMainMenu.addWindowListener(new WindowAdapter () {
            public void windowClosing(WindowEvent we) {
                if (partTimeWindowCheck == true) {
                    framePartTimeStaffHire.requestFocus();
                    framePartTimeStaffHire.setState(JFrame.NORMAL); 
                }
                else if (fullTimeWindowCheck == true) {
                    frameFullTimeStaffHire.requestFocus();
                    frameFullTimeStaffHire.setState(JFrame.NORMAL);
                }
                else if (partTimeWindowCheck == false && fullTimeWindowCheck == false) {
                    System.exit(0);
                }
            }
        });
        //creating cursor object to change it in needed swing components
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        //creating a menu bar
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        //creating a menu item
        JMenuItem menuItemAbout = new JMenuItem("About");
        menuItemAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //creating a dialog box with text inside it
                JDialog jd = new JDialog(frameMainMenu, "About");
                jd.setSize(250, 150);

                JLabel l = new JLabel("<html>ING Nepal v1.0.0<br>Developed by : Ashesh Rai</html>", SwingConstants.CENTER);

                jd.add(l);

                jd.setVisible(true);
                jd.setLocationRelativeTo(null);
            }
        });
        //creating another menu item
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        //adding menu items to menu and adding menu to menubar
        menu.add(menuItemAbout);
        menu.add(menuItemExit);
        mb.add(menu);
        //creating a part time operations button
        JButton buttonPartTimeForm = new JButton("Part Time Operations");
        buttonPartTimeForm.setCursor(cursor);
        buttonPartTimeForm.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                //checking to see whether any operation windows are open or not
                if (partTimeWindowCheck == false && fullTimeWindowCheck == false) {
                    partTimeForm();
                    partTimeWindowCheck = true;
                }
                else if (fullTimeWindowCheck == true) {
                    frameFullTimeStaffHire.requestFocus();
                    frameFullTimeStaffHire.setState(JFrame.NORMAL);
                }
                else {
                    framePartTimeStaffHire.requestFocus();
                    framePartTimeStaffHire.setState(JFrame.NORMAL);
                }
            }
        }
        );
        //creating a full time operations button
        JButton buttonFullTimeForm = new JButton("Full Time Operations");
        buttonFullTimeForm.setCursor(cursor);
        buttonFullTimeForm.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                if (fullTimeWindowCheck == false && partTimeWindowCheck == false) {
                    fullTimeForm();
                    fullTimeWindowCheck = true;
                }
                else if (partTimeWindowCheck == true) {
                    framePartTimeStaffHire.requestFocus();
                    framePartTimeStaffHire.setState(JFrame.NORMAL);
                }
                else {
                    frameFullTimeStaffHire.requestFocus();
                    frameFullTimeStaffHire.setState(JFrame.NORMAL);
                }
            }
        });
        //adding part time and full time operations button to a panel
        JPanel mainMenuButtonPanel = new JPanel();
        mainMenuButtonPanel.setLayout(new FlowLayout());
        mainMenuButtonPanel.add(buttonPartTimeForm);
        mainMenuButtonPanel.add(buttonFullTimeForm);
        //creating a scrollable table for staff to place it on the tabbed pane of main menu
        staffTable = new JTable();
        dtmStaff = new DefaultTableModel(new Object[]{"Vacancy No.", "Name", "Employment Type"}, 0);
        staffTable.setModel(dtmStaff);
        JScrollPane staffTableScrollPane = new JScrollPane();
        staffTableScrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, mainMenuButtonPanel);
        staffTableScrollPane.setViewportView(staffTable);
        //creating a scrollable vacancy table to place it on the tabbed pane of main menu
        vacancyTable = new JTable();
        dtmVacancy = new DefaultTableModel(new Object[]{"Vacancy No.", "Employment Type"}, 0);
        vacancyTable.setModel(dtmVacancy);
        JScrollPane vacancyTableScrollPane = new JScrollPane();
        vacancyTableScrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, mainMenuButtonPanel);
        vacancyTableScrollPane.setViewportView(vacancyTable);
        //adding tables to the panels
        JPanel panelStaffTbl = new JPanel();
        panelStaffTbl.setLayout(new BorderLayout());
        panelStaffTbl.add(staffTableScrollPane, BorderLayout.CENTER);
        JPanel panelVacancyTbl = new JPanel();
        panelVacancyTbl.setLayout(new BorderLayout());
        panelVacancyTbl.add(vacancyTableScrollPane, BorderLayout.CENTER);
        //adding panels with table to the tabbedpane
        JTabbedPane tpMain = new JTabbedPane();
        tpMain.add("Staffs", panelStaffTbl);
        tpMain.add("Vacancy", panelVacancyTbl);
        //setting the parameters of layout of main menu
        frameMainMenu.add(tpMain, BorderLayout.CENTER);
        frameMainMenu.add(mainMenuButtonPanel, BorderLayout.SOUTH);
        frameMainMenu.add(mb, BorderLayout.NORTH);

}

    public void partTimeForm () {
        //setting up part time operations form
        framePartTimeStaffHire = new JFrame("Part Time Staff Hire");
        framePartTimeStaffHire.setSize(340, 380);
        framePartTimeStaffHire.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePartTimeStaffHire.setLayout(new BorderLayout());
        framePartTimeStaffHire.setResizable(false);
        framePartTimeStaffHire.setAlwaysOnTop(true);
        framePartTimeStaffHire.setLocationRelativeTo(null);
        framePartTimeStaffHire.addWindowListener(new WindowAdapter() {
        @Override
            public void windowClosing(WindowEvent we) {
                partTimeWindowCheck = false;
            }
        });
        //checking whether part time operations is already opened or not
        if(!partTimeWindowCheck){
            framePartTimeStaffHire.setVisible(true);
        }

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        //creating label for use in part time operations form
        JLabel lblPartTimeName = new JLabel("Name:");
        JLabel lblPartTimeDesignation = new JLabel("Designation:");
        JLabel lblPartTimeJobType = new JLabel("Job Type:");
        JLabel lblPartTimeQualification = new JLabel("Qualification:");
        JLabel lblPartTimeWorkingHour = new JLabel("Working Hour:");
        JLabel lblPartTimeWagesPerHour = new JLabel("Wages Per Hour:");
        JLabel lblPartTimeShifts = new JLabel("Shifts:");
        JLabel lblPartTimeJoiningDate = new JLabel("Joining Date:");
        JLabel lblPartTimeAppointedBy = new JLabel("Appointed By:");
        JLabel lblPartTimeVacancyNumberVacancyPanel = new JLabel("Vacancy Number:");
        JLabel lblPartTimeVacancyNumberAppointPanel = new JLabel("Vacancy Number:");
        JLabel lblPartTimeVacancyNumberDisplayPanel = new JLabel("Vacancy Number:");
        //creating text field for use in part time operations form
        tfPartTimeName = new JTextField();
        tfPartTimeDesignation = new JTextField();
        tfPartTimeJobType = new JTextField();
        tfPartTimeQualification = new JTextField();
        tfPartTimeWorkingHour = new JTextField();
        tfPartTimeWagesPerHour = new JTextField();
        tfPartTimeShifts = new JTextField();
        tfPartTimeJoiningDate = new JTextField();
        tfPartTimeAppointedBy = new JTextField();
        tfPartTimeVacancyNumberVacancyPanel = new JTextField();
        tfPartTimeVacancyNumberAppointPanel = new JTextField();
        tfPartTimeVacancyNumberDisplayPanel = new JTextField();
        //creating buttons for use in part time opperations form
        //multiple clear and back buttons are created to place it in different pane of tabbed pane
        JButton partTimeAddVacancyButton = new JButton("Add Vacancy");
        JButton partTimeClearButtonVacancyPanel = new JButton("Clear");
        JButton partTimeClearButtonAppointPanel = new JButton("Clear");
        JButton partTimeClearButtonDisplayPanel = new JButton("Clear");
        JButton partTimeDisplayButton = new JButton("Display");
        JButton partTimeAppointButton = new JButton("Appoint");
        JButton partTimeBackButtonVacancyPanel = new JButton("Back");
        JButton partTimeBackButtonAppointPanel = new JButton("Back");
        JButton partTimeBackButtonDisplayPanel = new JButton("Back");
        JButton partTimeTerminateButton = new JButton("Terminate");
        //setting up add vacancy pane of part time operations
        JPanel vacancyPanel = new JPanel ();
        vacancyPanel.setLayout(null);

        lblPartTimeVacancyNumberVacancyPanel.setBounds(50, 45, 120, 25);
        tfPartTimeVacancyNumberVacancyPanel.setBounds(180, 45, 100, 25);

        lblPartTimeDesignation.setBounds(50, 75, 100, 25);
        tfPartTimeDesignation.setBounds(180, 75, 100, 25);

        lblPartTimeJobType.setBounds(50, 105, 100, 25);
        tfPartTimeJobType.setBounds(180, 105, 100, 25);

        lblPartTimeWorkingHour.setBounds(50, 135, 100, 25);
        tfPartTimeWorkingHour.setBounds(180, 135, 100, 25);

        lblPartTimeWagesPerHour.setBounds(50, 165, 100, 25);
        tfPartTimeWagesPerHour.setBounds(180, 165, 100, 25);

        lblPartTimeShifts.setBounds(50, 195, 100, 25);
        tfPartTimeShifts.setBounds(180, 195, 100, 25);

        partTimeBackButtonVacancyPanel.setBounds(50, 10, 70, 20);
        partTimeBackButtonVacancyPanel.setCursor(cursor);
        partTimeBackButtonVacancyPanel.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                //disposing part time operations form and setting the boolean value to false if back button is pressed
                framePartTimeStaffHire.dispose();
                partTimeWindowCheck = false;
            }
        }
        );

        partTimeAddVacancyButton.setBounds(50, 245, 110, 25);
        partTimeAddVacancyButton.setCursor(cursor);
        partTimeAddVacancyButton.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                int vacancyNumber = getPartTimeTfVacancyNumber();
                String designation = getPartTimeTfDesignation();
                String jobType = getPartTimeTfJobType();
                int workingHour = getPartTimeTfWorkingHour();
                int wagesPerHour = getPartTimeTfWagesPerHour();
                String shifts = getPartTimeTfShifts();
                //checking to see if value returned by the getter methods of textfields is null or not
                if(vacancyNumber != 0 && workingHour != 0 && wagesPerHour != 0 && !designation.isEmpty() && !jobType.isEmpty() && !shifts.isEmpty()) {
                    int size = arrayListStaffHire.size();
                    //checking the vacancy number only if arraylist size is 0
                    if(size > 0) {
                        for(StaffHire sh : arrayListStaffHire) {
                            int num = sh.getVacancyNumber();
                            if(num == vacancyNumber) {
                                //displaying a error message if vacancy number already exists and returning back the control
                                JOptionPane.showMessageDialog(framePartTimeStaffHire, "Vacancy number " + vacancyNumber + " already exists.", "Error!", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        //adding the vacancy if the looping and checking does not find the same vacancy number match
                        PartTimeStaffHire partTime = new PartTimeStaffHire (vacancyNumber, designation, jobType, workingHour, wagesPerHour, shifts);
                        arrayListStaffHire.add(partTime);
                        JOptionPane.showMessageDialog(framePartTimeStaffHire, "Vacancy number " + vacancyNumber + " added", "Successful", JOptionPane.INFORMATION_MESSAGE);

                        clearTextPartTime();
                    }
                    
                    //adding vacancy number immediately if arraylist size is 0
                    else {
                        PartTimeStaffHire partTime = new PartTimeStaffHire (vacancyNumber, designation, jobType, workingHour, wagesPerHour, shifts);
                       arrayListStaffHire.add(partTime);
                       JOptionPane.showMessageDialog(framePartTimeStaffHire, "Vacancy number " + vacancyNumber + " added", "Successful", JOptionPane.INFORMATION_MESSAGE);

                       clearTextPartTime();
                    }
                    //adding the vacancy number to the table in tabbed pane of main menu
                    DefaultTableModel modelVacancyTable = (DefaultTableModel) vacancyTable.getModel();
                    modelVacancyTable.addRow(new Object[]{vacancyNumber, "Part Time"});
                    }
                //displaying error messages accordingly if textfields are empty
                else if (designation.isEmpty() && jobType.isEmpty() && shifts.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Designation, job type, and shifts text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (designation.isEmpty() && jobType.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Designation and job type text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (designation.isEmpty() && shifts.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Designation and shifts text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (shifts.isEmpty() && jobType.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Shifts and job type text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (designation.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Designation text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (jobType.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Job type text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Shifts text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
        );

        partTimeClearButtonVacancyPanel.setBounds(170, 245, 110, 25);
        partTimeClearButtonVacancyPanel.setCursor(cursor);
        partTimeClearButtonVacancyPanel.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                //calling a method to clear the text fields
                clearTextPartTime();
            }
        }
        );
        //adding all the components to the panel
        vacancyPanel.add(lblPartTimeVacancyNumberVacancyPanel);
        vacancyPanel.add(tfPartTimeVacancyNumberVacancyPanel);
        vacancyPanel.add(lblPartTimeDesignation);
        vacancyPanel.add(tfPartTimeDesignation);
        vacancyPanel.add(lblPartTimeJobType);
        vacancyPanel.add(tfPartTimeJobType);
        vacancyPanel.add(lblPartTimeWorkingHour);
        vacancyPanel.add(tfPartTimeWorkingHour);
        vacancyPanel.add(lblPartTimeWagesPerHour);
        vacancyPanel.add(tfPartTimeWagesPerHour);
        vacancyPanel.add(lblPartTimeShifts);
        vacancyPanel.add(tfPartTimeShifts);
        vacancyPanel.add(partTimeBackButtonVacancyPanel);
        vacancyPanel.add(partTimeAddVacancyButton);
        vacancyPanel.add(partTimeClearButtonVacancyPanel);
        //creating a panel and setting its layout
        JPanel appointPanel = new JPanel ();
        appointPanel.setLayout(null);

        lblPartTimeVacancyNumberAppointPanel.setBounds(50, 45, 120, 25);
        tfPartTimeVacancyNumberAppointPanel.setBounds(180, 45, 100, 25);

        lblPartTimeName.setBounds(50, 75, 100, 25);
        tfPartTimeName.setBounds(180, 75, 100, 25);

        lblPartTimeJoiningDate.setBounds(50, 105, 100, 25);
        tfPartTimeJoiningDate.setBounds(180, 105, 100, 25);

        lblPartTimeQualification.setBounds(50, 135, 100, 25);
        tfPartTimeQualification.setBounds(180, 135, 100, 25);

        lblPartTimeAppointedBy.setBounds(50, 165, 100, 25);
        tfPartTimeAppointedBy.setBounds(180, 165, 100, 25);

        partTimeBackButtonAppointPanel.setBounds(50, 10, 70, 20);
        partTimeBackButtonAppointPanel.setCursor(cursor);
        partTimeBackButtonAppointPanel.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                framePartTimeStaffHire.dispose();
                partTimeWindowCheck = false;
            }
        }
        );

        partTimeAppointButton.setBounds(50, 245, 110, 25);
        partTimeAppointButton.setCursor(cursor);
        partTimeAppointButton.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                String name = getPartTimeTfName();
                String joiningDate = getPartTimeTfJoiningDate();
                String qualification = getPartTimeTfQualification();
                String appointedBy = getPartTimeTfAppointedBy();
                int vacancyNumber = getPartTimeTfVacancyNumber();
                int size = arrayListStaffHire.size();
                if (vacancyNumber != 0 && !joiningDate.isEmpty() && !qualification.isEmpty() && !name.isEmpty() && !appointedBy.isEmpty()) {
                    if(size > 0) {
                        for(int i = 0; i < arrayListStaffHire.size(); ++i) {
                            StaffHire sh = arrayListStaffHire.get(i);
                            if (sh instanceof PartTimeStaffHire) {
                                PartTimeStaffHire p = (PartTimeStaffHire) sh;
                                int vacancyNo = sh.getVacancyNumber();
                                boolean joinCheck = p.getJoined();
                                if (vacancyNo == vacancyNumber && joinCheck == false) {
                                    //hiring staff if vacancy number matches the number that was previously added as an open vacancy and if no one has joined on that vacancy number
                                    p.hirePartTimeStaff(name, joiningDate, qualification, appointedBy);
                                    arrayListStaffHire.set(i, p);
                                    JOptionPane.showMessageDialog(framePartTimeStaffHire, name + " hired as a part time staff.", "Successful", JOptionPane.INFORMATION_MESSAGE);
                                    //adding the appointed staff on the table that is shown on the main menu
                                    DefaultTableModel modelStaffTable = (DefaultTableModel) staffTable.getModel();
                                    modelStaffTable.addRow(new Object[]{vacancyNo, name, "Part Time"});
                                    //calling a method to remove the vacancy number from the vacancy table
                                    vacancyTableRowRemover(vacancyNumber);

                                    clearTextPartTime();
                                    break;
                                }
                                else if (vacancyNo == vacancyNumber && joinCheck == true) {
                                    //showing error message if someone already has joined that position
                                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Vacancy number " + vacancyNumber + " already filled.", "Error!", JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                                else if ((size - 1) == i && vacancyNo != vacancyNumber) {
                                    //showing error message if there is no such vacancy number
                                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Vacancy number " + vacancyNumber + " not found.", "Error!", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                                                        
                        }
                    }
                    else if (size == 0) {
                        //showing error message if there is no vacancy available
                        JOptionPane.showMessageDialog(framePartTimeStaffHire, "No vacancy available.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
                //showin appropriate error message if the text fields are empty
                else if (joiningDate.isEmpty() && qualification.isEmpty() && name.isEmpty() && appointedBy.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Joining date, qualification, name, and appointed by text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (joiningDate.isEmpty() && qualification.isEmpty() && name.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Joining date, qualification, and name text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (joiningDate.isEmpty() && qualification.isEmpty() && appointedBy.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Joining date, qualification, and appointed by text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (qualification.isEmpty() && name.isEmpty() && appointedBy.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Qualification, name, and appointed by text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (joiningDate.isEmpty() && qualification.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Joining date and qualification text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (joiningDate.isEmpty() && name.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Joining date and name text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (joiningDate.isEmpty() && appointedBy.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Joining date and appointed by text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (qualification.isEmpty() && name.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Qualification and name text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (qualification.isEmpty() && appointedBy.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Qualification and appointedBy text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (name.isEmpty() && appointedBy.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Name and appointed by text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (joiningDate.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Joining date text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (qualification.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Qualification text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Name text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Appointed by text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        partTimeClearButtonAppointPanel.setBounds(170, 245, 110, 25);
        partTimeClearButtonAppointPanel.setCursor(cursor);
        partTimeClearButtonAppointPanel.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                clearTextPartTime();
            }
        }
        );

        appointPanel.add(lblPartTimeVacancyNumberAppointPanel);
        appointPanel.add(tfPartTimeVacancyNumberAppointPanel);
        appointPanel.add(lblPartTimeName);
        appointPanel.add(tfPartTimeName);
        appointPanel.add(lblPartTimeJoiningDate);
        appointPanel.add(tfPartTimeJoiningDate);
        appointPanel.add(lblPartTimeQualification);
        appointPanel.add(tfPartTimeQualification);
        appointPanel.add(lblPartTimeAppointedBy);
        appointPanel.add(tfPartTimeAppointedBy);
        appointPanel.add(partTimeBackButtonAppointPanel);
        appointPanel.add(partTimeAppointButton);
        appointPanel.add(partTimeClearButtonAppointPanel);

        JPanel displayPanel = new JPanel ();
        displayPanel.setLayout(null);

        lblPartTimeVacancyNumberDisplayPanel.setBounds(50, 45, 120, 25);
        tfPartTimeVacancyNumberDisplayPanel.setBounds(180, 45, 100, 25);

        partTimeBackButtonDisplayPanel.setBounds(50, 10, 70, 20);
        partTimeBackButtonDisplayPanel.setCursor(cursor);
        partTimeBackButtonDisplayPanel.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                framePartTimeStaffHire.dispose();
                partTimeWindowCheck = false;
            }
        }
        );

        partTimeClearButtonDisplayPanel.setBounds(170, 150, 110, 25);
        partTimeClearButtonDisplayPanel.setCursor(cursor);
        partTimeClearButtonDisplayPanel.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                clearTextPartTime();
            }
        }
        );

        partTimeDisplayButton.setBounds(50, 150, 110, 25);
        partTimeDisplayButton.setCursor(cursor);
        partTimeDisplayButton.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                int vacancyNo = getPartTimeTfVacancyNumber();
                boolean found = false;

                if (vacancyNo != 0) {
                    for(StaffHire s : arrayListStaffHire) {
                        if(s instanceof PartTimeStaffHire) {
                            PartTimeStaffHire p = (PartTimeStaffHire) s;

                            if (s.getVacancyNumber() == vacancyNo) {
                                p.displayDetails();
                                found = true;
                                JOptionPane.showMessageDialog(framePartTimeStaffHire, "Name: " + p.getStaffName() + "\nDesignation: " + s.getDesignation() + "\nJob Type: " + s.getJobType() + "\nQualification: " + p.getQualification() + "\nWorking Hour: " + p.getWorkingHour() + "\nWages Per Hour: " + p.getWagesPerHour() + "\nShifts: " + p.getShifts() + "\nJoining Date: " + p.getJoiningDate() + "\nAppointed By: " + p.getAppointedBy(), "Staff Info.",JOptionPane.INFORMATION_MESSAGE);
                                clearTextPartTime();
                                break;
                            }
                        }
                    }
                    if(!found) {
                    JOptionPane.showMessageDialog(framePartTimeStaffHire, "Vacancy number not found.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        }
        );

        partTimeTerminateButton.setBounds(115, 200, 100, 25);
        partTimeTerminateButton.setCursor(cursor);
        partTimeTerminateButton.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                int vacancyNumber = getPartTimeTfVacancyNumber();
                boolean found = false;
                boolean terminateCheck = false;
                if (vacancyNumber != 0) {
                    for(StaffHire s : arrayListStaffHire) {
                        if(s instanceof PartTimeStaffHire) {
                            PartTimeStaffHire p = (PartTimeStaffHire) s;
                            if (s.getVacancyNumber() == vacancyNumber && p.getTerminated() == false) {
                                String name = p.getStaffName();
                                //terminating the staff if vacancy number matches and has not already been terminated
                                //calling the method terminatePartTimeStaff of PartTimeStaffHire class
                                p.terminatePartTimeStaff();
                                arrayListStaffHire.remove(s);
                                staffTableRowRemover(vacancyNumber);
                                found = true;
                                JOptionPane.showMessageDialog(framePartTimeStaffHire, name + " terminated.", "Termination.",JOptionPane.INFORMATION_MESSAGE);
                                clearTextPartTime();
                                break;
                            }
                        }
                    }
                        if(!found) {
                            //showing error message if vacancy number is not found
                            JOptionPane.showMessageDialog(framePartTimeStaffHire, "Vacancy number " + vacancyNumber + " not found.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        }

        );

        //adding the components to a panel
        displayPanel.add(lblPartTimeVacancyNumberDisplayPanel);
        displayPanel.add(tfPartTimeVacancyNumberDisplayPanel);
        displayPanel.add(partTimeBackButtonDisplayPanel);
        displayPanel.add(partTimeClearButtonDisplayPanel);
        displayPanel.add(partTimeDisplayButton);
        displayPanel.add(partTimeTerminateButton);
        //adding all the panels to a tabbed pane
        tpPartTimeForm = new JTabbedPane();
        tpPartTimeForm.add("Add vacancy", vacancyPanel);
        tpPartTimeForm.add("Appoint", appointPanel);
        tpPartTimeForm.add("Display and terminate", displayPanel);

        framePartTimeStaffHire.add(tpPartTimeForm, BorderLayout.CENTER);
    }

    public void fullTimeForm () {
        //setting up full time operations form
        frameFullTimeStaffHire = new JFrame("Full Time Staff Hire");
        frameFullTimeStaffHire.setSize(340, 360);
        frameFullTimeStaffHire.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameFullTimeStaffHire.setLayout(new BorderLayout());
        frameFullTimeStaffHire.setResizable(false);
        frameFullTimeStaffHire.setAlwaysOnTop(true);
        frameFullTimeStaffHire.setLocationRelativeTo(null);
        frameFullTimeStaffHire.addWindowListener(new WindowAdapter() {
        @Override
            public void windowClosing(WindowEvent we) {
                fullTimeWindowCheck = false;
            }
        });

        if(!fullTimeWindowCheck){
            //checking if the full time operations is already opened or not
            frameFullTimeStaffHire.setVisible(true);
        }

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        //creating labels to be used in the full time operations form
        JLabel lblFullTimeName = new JLabel("Name:");
        JLabel lblFullTimeDesignation = new JLabel("Designation:");
        JLabel lblFullTimeJobType = new JLabel("Job Type:");
        JLabel lblFullTimeQualification = new JLabel("Qualification:");
        JLabel lblFullTimeWorkingHour = new JLabel("Working Hour:");
        JLabel lblFullTimeSalary = new JLabel("Salary:");
        JLabel lblFullTimeJoiningDate = new JLabel("Joining Date:");
        JLabel lblFullTimeAppointedBy = new JLabel("Appointed By:");
        JLabel lblFullTimeVacancyNumberVacancyPanel = new JLabel("Vacancy Number:");
        JLabel lblFullTimeVacancyNumberAppointPanel = new JLabel("Vacancy Number:");
        JLabel lblFullTimeVacancyNumberDisplayPanel = new JLabel("Vacancy Number:");
        //setting up text fields that are going to be used in the full time operations form
        tfFullTimeName = new JTextField();
        tfFullTimeDesignation = new JTextField();
        tfFullTimeJobType = new JTextField();
        tfFullTimeQualification = new JTextField();
        tfFullTimeWorkingHour = new JTextField();
        tfFullTimeSalary = new JTextField();
        tfFullTimeJoiningDate = new JTextField();
        tfFullTimeAppointedBy = new JTextField();
        tfFullTimeVacancyNumberVacancyPanel = new JTextField();
        tfFullTimeVacancyNumberAppointPanel = new JTextField();
        tfFullTimeVacancyNumberDisplayPanel = new JTextField();
        //creating buttons that are going to be used in the full time operations form
        //creating multiple clear and back buttons that are going to be added in each panel of each tabbed pane
        JButton fullTimeAddVacancyButton = new JButton("Add Vacancy");
        JButton fullTimeClearButtonVacancyPanel = new JButton("Clear");
        JButton fullTimeClearButtonAppointPanel = new JButton("Clear");
        JButton fullTimeClearButtonDisplayPanel = new JButton("Clear");
        JButton fullTimeDisplayButton = new JButton("Display");
        JButton fullTimeAppointButton = new JButton("Appoint");
        JButton fullTimeBackButtonVacancyPanel = new JButton("Back");
        JButton fullTimeBackButtonAppointPanel = new JButton("Back");
        JButton fullTimeBackButtonDisplayPanel = new JButton("Back");
        //setting up a add vacancy panel
        JPanel vacancyPanel = new JPanel ();
        vacancyPanel.setLayout(null);

        lblFullTimeVacancyNumberVacancyPanel.setBounds(50, 45, 120, 25);
        tfFullTimeVacancyNumberVacancyPanel.setBounds(180, 45, 100, 25);

        lblFullTimeDesignation.setBounds(50, 75, 100, 25);
        tfFullTimeDesignation.setBounds(180, 75, 100, 25);

        lblFullTimeJobType.setBounds(50, 105, 100, 25);
        tfFullTimeJobType.setBounds(180, 105, 100, 25);

        lblFullTimeWorkingHour.setBounds(50, 135, 100, 25);
        tfFullTimeWorkingHour.setBounds(180, 135, 100, 25);

        lblFullTimeSalary.setBounds(50, 165, 100, 25);
        tfFullTimeSalary.setBounds(180, 165, 100, 25);

        fullTimeBackButtonVacancyPanel.setBounds(50, 10, 70, 20);
        fullTimeBackButtonVacancyPanel.setCursor(cursor);
        fullTimeBackButtonVacancyPanel.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                //disposing full time operations form and setting the boolean value to false when the back button is pressed
                frameFullTimeStaffHire.dispose();
                fullTimeWindowCheck = false;
            }
        }
        );

        fullTimeAddVacancyButton.setBounds(50, 215, 110, 25);
        fullTimeAddVacancyButton.setCursor(cursor);
        fullTimeAddVacancyButton.addActionListener (new ActionListener (){
            public void actionPerformed (ActionEvent ae) {
                int vacancyNumber = getFullTimeTfVacancyNumber();
                String designation = getFullTimeTfDesignation();
                String jobType = getFullTimeTfJobType();
                int workingHour = getFullTimeTfWorkingHour();
                int salary = getFullTimeTfSalary();
                //checking if all the inputs are null or not
                if (vacancyNumber != 0 && workingHour != 0 && salary != 0 && !designation.isEmpty() && !jobType.isEmpty()) {
                    int size = arrayListStaffHire.size();
                    //checking if the arraylist size is greater than 0 or not
                    if (size > 0) {
                        for(StaffHire sh : arrayListStaffHire) {
                            int num = sh.getVacancyNumber();
                            if(num == vacancyNumber) {
                                //displaying an error message if vacancy number already exists
                                JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Vacancy number " + vacancyNumber + " already exists.", "Error!", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                        }
                        //adding the vacancy number if looping and checking the vacancy number that exists do not match the inputted vacancy number
                        FullTimeStaffHire fullTime = new FullTimeStaffHire (vacancyNumber, designation, jobType, workingHour, salary);
                        arrayListStaffHire.add(fullTime);
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Vacancy number " + vacancyNumber + " added", "Successful", JOptionPane.INFORMATION_MESSAGE);

                        clearTextFullTime();
                    }

                    else {
                        //adding the vacancy number if arraylist size is 0
                        FullTimeStaffHire fullTime = new FullTimeStaffHire (vacancyNumber, designation, jobType, workingHour, salary);
                        arrayListStaffHire.add(fullTime);
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Vacancy number " + vacancyNumber + " added", "Successful", JOptionPane.INFORMATION_MESSAGE);

                        clearTextFullTime();
                    }
                    //adding a row with inputted vacancy number in the main menu table
                    DefaultTableModel modelVacancyTable = (DefaultTableModel) vacancyTable.getModel();
                    modelVacancyTable.addRow(new Object[]{vacancyNumber, "Full Time"});
                }
                //displaying appropriate error messages if textfield is empty
                else if (designation.isEmpty() && jobType.isEmpty()) {
                    JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Designation and job type text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (designation.isEmpty()) {
                    JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Designation text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Job type text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        fullTimeClearButtonVacancyPanel.setBounds(170, 215, 110, 25);
        fullTimeClearButtonVacancyPanel.setCursor(cursor);
        fullTimeClearButtonVacancyPanel.addActionListener (new ActionListener (){
            public void actionPerformed (ActionEvent ae) {
                clearTextFullTime();
            }
        }
        );
        //adding the components to the vacancy panel
        vacancyPanel.add(lblFullTimeVacancyNumberVacancyPanel);
        vacancyPanel.add(tfFullTimeVacancyNumberVacancyPanel);
        vacancyPanel.add(lblFullTimeDesignation);
        vacancyPanel.add(tfFullTimeDesignation);
        vacancyPanel.add(lblFullTimeJobType);
        vacancyPanel.add(tfFullTimeJobType);
        vacancyPanel.add(lblFullTimeWorkingHour);
        vacancyPanel.add(tfFullTimeWorkingHour);
        vacancyPanel.add(lblFullTimeSalary);
        vacancyPanel.add(tfFullTimeSalary);
        vacancyPanel.add(fullTimeBackButtonVacancyPanel);
        vacancyPanel.add(fullTimeAddVacancyButton);
        vacancyPanel.add(fullTimeClearButtonVacancyPanel);
        //setting up appoint panel
        JPanel appointPanel = new JPanel();
        appointPanel.setLayout(null);

        lblFullTimeVacancyNumberAppointPanel.setBounds(50, 45, 120, 25);
        tfFullTimeVacancyNumberAppointPanel.setBounds(180, 45, 100, 25);

        lblFullTimeName.setBounds(50, 75, 100, 25);
        tfFullTimeName.setBounds(180, 75, 100, 25);

        lblFullTimeJoiningDate.setBounds(50, 105, 100, 25);
        tfFullTimeJoiningDate.setBounds(180, 105, 100, 25);

        lblFullTimeQualification.setBounds(50, 135, 100, 25);
        tfFullTimeQualification.setBounds(180, 135, 100, 25);

        lblFullTimeAppointedBy.setBounds(50, 165, 100, 25);
        tfFullTimeAppointedBy.setBounds(180, 165, 100, 25);

        fullTimeBackButtonAppointPanel.setBounds(50, 10, 70, 20);
        fullTimeBackButtonAppointPanel.setCursor(cursor);
        fullTimeBackButtonAppointPanel.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                frameFullTimeStaffHire.dispose();
                fullTimeWindowCheck = false;
            }
        }
        );

            fullTimeAppointButton.setBounds(50, 215, 110, 25);
            fullTimeAppointButton.setCursor(cursor);
            fullTimeAppointButton.addActionListener (new ActionListener (){
                public void actionPerformed(ActionEvent ae){
                    String name = getFullTimeTfName();
                    String joiningDate = getFullTimeTfJoiningDate();
                    String qualification = getFullTimeTfQualification();
                    String appointedBy = getFullTimeTfAppointedBy();
                    int vacancyNumber = getFullTimeTfVacancyNumber();
                    int size = arrayListStaffHire.size();
                    if (vacancyNumber != 0 && !name.isEmpty() && !joiningDate.isEmpty() && !qualification.isEmpty() && !appointedBy.isEmpty()) {
                        if(size > 0) {
                            for(int i = 0; i < arrayListStaffHire.size(); ++i) {
                                StaffHire sh = arrayListStaffHire.get(i);
                                if (sh instanceof FullTimeStaffHire) {
                                    FullTimeStaffHire f = (FullTimeStaffHire) sh;
                                    int vacancyNo = sh.getVacancyNumber();
                                    boolean joinedCheck = f.getJoined();
                                    if(vacancyNo == vacancyNumber && joinedCheck == false) {
                                        //hiring the staff if vacancy number is found and no one else has joined in that position
                                        f.hireFullTimeStaff(name, joiningDate, qualification, appointedBy);
                                        arrayListStaffHire.set(i, f);
                                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, name + " hired as a full-time staff.", "Successful", JOptionPane.INFORMATION_MESSAGE);
                                        //adding the vacancy number and staff name to the staff table in the main menu
                                        DefaultTableModel modelStaffTable = (DefaultTableModel) staffTable.getModel();
                                        modelStaffTable.addRow(new Object[]{vacancyNo, name, "Full Time"});
                                        //removing vacancy number from the vacancy table
                                        vacancyTableRowRemover(vacancyNumber);

                                        clearTextFullTime();
                                        break;
                                    }
                                    else if (vacancyNo == vacancyNumber && joinedCheck == true) {
                                        //displaying an error message if the position is already filled
                                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Vacancy number " + vacancyNumber + " already filled.", "Error!", JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
                                    else if ((size - 1) == i && vacancyNo != vacancyNumber) {
                                        //displaying an error message if the vacancy number is not found
                                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Vacancy number " + vacancyNumber + " not found.", "Error!", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            
                            }
                        }
                        else if (size == 0) {
                            //displaying an error message if there are no vacancy available
                            JOptionPane.showMessageDialog(framePartTimeStaffHire, "No vacancy available.", "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    //displaying appropriate error message if the text fields are empty
                    else if (joiningDate.isEmpty() && qualification.isEmpty() && name.isEmpty() && appointedBy.isEmpty()) {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Joining date, qualification, name, and appointed by text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (joiningDate.isEmpty() && qualification.isEmpty() && name.isEmpty()) {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Joining date, qualification, and name text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (joiningDate.isEmpty() && qualification.isEmpty() && appointedBy.isEmpty()) {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Joining date, qualification, and appointed by text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (qualification.isEmpty() && name.isEmpty() && appointedBy.isEmpty()) {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Qualification, name, and appointed by text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (joiningDate.isEmpty() && qualification.isEmpty()) {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Joining date and qualification text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (joiningDate.isEmpty() && name.isEmpty()) {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Joining date and name text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (joiningDate.isEmpty() && appointedBy.isEmpty()) {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Joining date and appointed by text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (qualification.isEmpty() && name.isEmpty()) {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Qualification and name text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (qualification.isEmpty() && appointedBy.isEmpty()) {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Qualification and appointedBy text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (name.isEmpty() && appointedBy.isEmpty()) {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Name and appointed by text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (joiningDate.isEmpty()) {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Joining date text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (qualification.isEmpty()) {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Qualification text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Name text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Appointed by text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            );

        fullTimeClearButtonAppointPanel.setBounds(170, 215, 110, 25);
        fullTimeClearButtonAppointPanel.setCursor(cursor);
        fullTimeClearButtonAppointPanel.addActionListener (new ActionListener (){
            public void actionPerformed (ActionEvent ae) {
                clearTextFullTime();
            }
        }
        );
        //adding the components of appoint panel
        appointPanel.add(lblFullTimeVacancyNumberAppointPanel);
        appointPanel.add(tfFullTimeVacancyNumberAppointPanel);
        appointPanel.add(lblFullTimeName);
        appointPanel.add(tfFullTimeName);
        appointPanel.add(lblFullTimeJoiningDate);
        appointPanel.add(tfFullTimeJoiningDate);
        appointPanel.add(lblFullTimeQualification);
        appointPanel.add(tfFullTimeQualification);
        appointPanel.add(lblFullTimeAppointedBy);
        appointPanel.add(tfFullTimeAppointedBy);
        appointPanel.add(fullTimeBackButtonAppointPanel);
        appointPanel.add(fullTimeAppointButton);
        appointPanel.add(fullTimeClearButtonAppointPanel);
        //setting up display panel
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(null);

        lblFullTimeVacancyNumberDisplayPanel.setBounds(50, 45, 120, 25);
        tfFullTimeVacancyNumberDisplayPanel.setBounds(180, 45, 100, 25);

        fullTimeBackButtonDisplayPanel.setBounds(50, 10, 70, 20);
        fullTimeBackButtonDisplayPanel.setCursor(cursor);
        fullTimeBackButtonDisplayPanel.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                frameFullTimeStaffHire.dispose();
                fullTimeWindowCheck = false;
            }
        }
        );

        fullTimeClearButtonDisplayPanel.setBounds(170, 150, 110, 25);
        fullTimeClearButtonDisplayPanel.setCursor(cursor);
        fullTimeClearButtonDisplayPanel.addActionListener (new ActionListener (){
            public void actionPerformed (ActionEvent ae) {
                clearTextFullTime();
            }
        }
        );

        fullTimeDisplayButton.setBounds(50, 150, 110, 25);
        fullTimeDisplayButton.setCursor(cursor);
        fullTimeDisplayButton.addActionListener (new ActionListener (){
            public void actionPerformed (ActionEvent ae) {

                int vacancyNo = getFullTimeTfVacancyNumber();
                boolean found = false;

                if (vacancyNo != 0) {
                    for (StaffHire s : arrayListStaffHire) {
                        if(s instanceof FullTimeStaffHire) {
                            FullTimeStaffHire f = (FullTimeStaffHire) s;

                            if (s.getVacancyNumber() == vacancyNo) {
                                //displaying details of the staff if the vacancy number is found
                                f.displayDetails();
                                found = true;
                                JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Name: " + f.getStaffName() + "\nDesignation: " + s.getDesignation() + "\nJob Type: " + s.getJobType() + "\nQualification: " + f.getQualification() + "\nWorking Hour: " + f.getWorkingHour() + "\nSalary: " + f.getSalary() + "\nJoining Date: " + f.getJoiningDate() + "\nAppointed By: " + f.getAppointedBy(), "Staff Info.",JOptionPane.INFORMATION_MESSAGE);
                                clearTextFullTime();
                                break;
                            }
                        }
                    }
                    if(!found) {
                        //displaying an error message if vacancy number is not found
                        JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Vacancy number not found.", "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                }

            }
        }
        );
        //adding the components to the display panel
        displayPanel.add(lblFullTimeVacancyNumberDisplayPanel);
        displayPanel.add(tfFullTimeVacancyNumberDisplayPanel);
        displayPanel.add(fullTimeBackButtonDisplayPanel);
        displayPanel.add(fullTimeClearButtonDisplayPanel);
        displayPanel.add(fullTimeDisplayButton);
        //adding all the panels to the tabbed pane
        tpFullTimeForm = new JTabbedPane();
        tpFullTimeForm.add("Add vacancy", vacancyPanel);
        tpFullTimeForm.add("Appoint", appointPanel);
        tpFullTimeForm.add("Display details", displayPanel);
        //setting the layout of the full time operations form
        frameFullTimeStaffHire.add(tpFullTimeForm, BorderLayout.CENTER);
    }
    //creating an override method of actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    //clearing the text fields of part time operations form using a method
    public void clearTextPartTime () {
        //checking the selected tabbed pane and clearing the text fields accordingly
        if (tpPartTimeForm.getSelectedIndex() == 0) {
            tfPartTimeDesignation.setText(null);
            tfPartTimeJobType.setText(null);
            tfPartTimeWorkingHour.setText(null);
            tfPartTimeWagesPerHour.setText(null);
            tfPartTimeShifts.setText(null);
            tfPartTimeVacancyNumberVacancyPanel.setText(null);
        }
        else if (tpPartTimeForm.getSelectedIndex() == 1) {
            tfPartTimeName.setText(null);
            tfPartTimeQualification.setText(null);
            tfPartTimeJoiningDate.setText(null);
            tfPartTimeAppointedBy.setText(null);
            tfPartTimeVacancyNumberAppointPanel.setText(null);
        }
        else if (tpPartTimeForm.getSelectedIndex() == 2) {
            tfPartTimeVacancyNumberDisplayPanel.setText(null);
        }

    }
    //getter method for name in part time operations form
    public String getPartTimeTfName () {
        String tfName = tfPartTimeName.getText().trim();

        return tfName;
    }
    //getter method for designation in part time operations form
    public String getPartTimeTfDesignation () {
        String tfDesignation = tfPartTimeDesignation.getText().trim();

        return tfDesignation;   
    }
    //getter method for job type in part time operations form
    public String getPartTimeTfJobType () {
        String tfJobType = tfPartTimeJobType.getText().trim();

        return tfJobType;
    }
    //getter method for qualification in part time operations form
    public String getPartTimeTfQualification () {
        String tfQualification = tfPartTimeQualification.getText().trim();

        return tfQualification;
    }
    //getter method for shifts in part time operations form
    public String getPartTimeTfShifts () {
        String tfShifts = tfPartTimeShifts.getText().trim();

        return tfShifts;
    }
    //getter method for joining date in part time operations form
    public String getPartTimeTfJoiningDate () {
        String tfJoiningDate = tfPartTimeJoiningDate.getText().trim();

        return tfJoiningDate;
    }
    //getter method for appointed by in part time operations form
    public String getPartTimeTfAppointedBy () {
        String tfAppointedBy = tfPartTimeAppointedBy.getText().trim();

        return tfAppointedBy;
    }
    //getter method for working hour in part time operations form
    public int getPartTimeTfWorkingHour () {
        int tfWorkingHour = 0;
        if((tfPartTimeWorkingHour.getText().trim()).equals("")){
            JOptionPane.showMessageDialog(framePartTimeStaffHire, "Working hour text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);    
        }
        else{
            try {
                //trying to parse the text field input into an integer
                tfWorkingHour = Integer.parseInt((tfPartTimeWorkingHour.getText().trim()));
            }

            catch (NumberFormatException nfe) {
                //throwing a number format exception if the input cannot be parsed into an integer
                JOptionPane.showMessageDialog(framePartTimeStaffHire, "Working hour input must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return tfWorkingHour;
    }
    //getter method for wages per hour in part time operations form
    public int getPartTimeTfWagesPerHour () {
        int tfWagesPerHour = 0;
        if((tfPartTimeWagesPerHour.getText().trim()).equals("")) {
            JOptionPane.showMessageDialog(framePartTimeStaffHire, "Wages per hour text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);            
        }
        else {
            try {
                //trying to parse the text field input into an integer
                tfWagesPerHour = Integer.parseInt((tfPartTimeWagesPerHour.getText().trim()));
            }

            catch (NumberFormatException nfe) {
                //throwing a number format exception if the input cannot be parsed into an integer
                JOptionPane.showMessageDialog(framePartTimeStaffHire, "Wages per hour input must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }           
        }
        return tfWagesPerHour;
    }
    //getter method for vacancy number in part time operations form
    public int getPartTimeTfVacancyNumber () {
        int tfVacancyNumber = 0;
        //checking the selected tabbed pane in the part time operations form to throw an error message if the field is empty
        if ((tfPartTimeVacancyNumberVacancyPanel.getText().trim()).equals("") && tpPartTimeForm.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(framePartTimeStaffHire, "Vacancy number text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if ((tfPartTimeVacancyNumberAppointPanel.getText().trim()).equals("") && tpPartTimeForm.getSelectedIndex() == 1) {
            JOptionPane.showMessageDialog(framePartTimeStaffHire, "Vacancy number text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if ((tfPartTimeVacancyNumberDisplayPanel.getText().trim()).equals("") && tpPartTimeForm.getSelectedIndex() == 2) {
            JOptionPane.showMessageDialog(framePartTimeStaffHire, "Vacancy number text field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {

            try {
                //trying to parse the text field input into an integer
                if (tpPartTimeForm.getSelectedIndex() == 0) {
                    tfVacancyNumber = Integer.parseInt((tfPartTimeVacancyNumberVacancyPanel.getText().trim()));
                }
                else if (tpPartTimeForm.getSelectedIndex() == 1) {
                    tfVacancyNumber = Integer.parseInt((tfPartTimeVacancyNumberAppointPanel.getText().trim()));
                }
                else {
                    tfVacancyNumber = Integer.parseInt((tfPartTimeVacancyNumberDisplayPanel.getText().trim()));
                }
            }

            catch (NumberFormatException nfe) {
                //throwing a number format exception if the input cannot be parsed into an integer
                JOptionPane.showMessageDialog(framePartTimeStaffHire, "Vacancy number input must be a number.", "Error" + nfe.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return tfVacancyNumber;
    }
    //getter method for name in full time operations form
    public String getFullTimeTfName() {
        String tfName = tfFullTimeName.getText().trim();

        return tfName;
    }
    //getter method for designation in full time operations form
    public String getFullTimeTfDesignation() {
        String tfDesignation = tfFullTimeDesignation.getText().trim();

        return tfDesignation;   
    }
    //getter method for job type in full time operations form
    public String getFullTimeTfJobType() {
        String tfJobType = tfFullTimeJobType.getText().trim();

        return tfJobType;
    }
    //getter method for qualification in full time operations form
    public String getFullTimeTfQualification() {
        String tfQualification = tfFullTimeQualification.getText().trim();

        return tfQualification;
    }
    //getter method for joining date in full time operations form
    public String getFullTimeTfJoiningDate() {
        String tfJoiningDate = tfFullTimeJoiningDate.getText().trim();

        return tfJoiningDate;
    }
    //getter method for appointed by in full time operations form
    public String getFullTimeTfAppointedBy() {
        String tfAppointedBy = tfFullTimeAppointedBy.getText().trim();

        return tfAppointedBy;
    }
    //getter method for working hour in full time operations form
    public int getFullTimeTfWorkingHour() {
        int tfWorkingHour = 0;
        if((tfFullTimeWorkingHour.getText().trim().equals(""))) {
            //displaying an error message if the input is empty
            JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Working hour text field cannot be empty.", "Error! ", JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                //trying to parse the input into an integer
                tfWorkingHour = Integer.parseInt((tfFullTimeWorkingHour.getText().trim()));
            }

            catch (NumberFormatException nfe) {
                //throwing a number format exception if the input cannot be parsed into an integer
                JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Working hour input must be a number.", "Error! " + nfe.getMessage(), JOptionPane.ERROR_MESSAGE);
            }    
        }
        return tfWorkingHour;
    }
    //getter method for salary in full time operations form
    public int getFullTimeTfSalary() {
        int tfSalary = 0;
        if ((tfFullTimeSalary.getText().trim()).equals("")) {
            //displaying an error message if the input is empty
            JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Salary text field cannot be empty.", "Error! ", JOptionPane.ERROR_MESSAGE);
        }
        else{
            try {
                //trying to parse the input into an integer
                tfSalary = Integer.parseInt((tfFullTimeSalary.getText().trim()));
            }

            catch (NumberFormatException nfe) {
                //throwing a number format exception if the input cannot be parsed into an integer
                JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Salary input must be a number.", "Error! " + nfe.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        }
        return tfSalary;
    }
    //getter method for vacancy number in full time operations
    public int getFullTimeTfVacancyNumber() {
        int tfVacancyNumber = 0;
        //checking to see if the inputs are empty or not for the vacancy number and throwing an error message if they are empty
        if ((tfFullTimeVacancyNumberVacancyPanel.getText().trim()).equals("") && tpFullTimeForm.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Vacancy number text field cannot be empty.", "Error! ", JOptionPane.ERROR_MESSAGE);
        }
        else if ((tfFullTimeVacancyNumberAppointPanel.getText().trim()).equals("") && tpFullTimeForm.getSelectedIndex() == 1) {
            JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Vacancy number text field cannot be empty.", "Error! ", JOptionPane.ERROR_MESSAGE);
        }
        else if ((tfFullTimeVacancyNumberDisplayPanel.getText().trim()).equals("") && tpFullTimeForm.getSelectedIndex() == 2) {
            JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Vacancy number text field cannot be empty.", "Error! ", JOptionPane.ERROR_MESSAGE);
        }
        else {
            //trying to parse the input into an integer by getting the index of tabbed panes and taking the appropriate input from the text field
            try {
                if (tpFullTimeForm.getSelectedIndex() == 0) {
                    tfVacancyNumber = Integer.parseInt((tfFullTimeVacancyNumberVacancyPanel.getText().trim()));
                }
                else if (tpFullTimeForm.getSelectedIndex() == 1) {
                    tfVacancyNumber = Integer.parseInt((tfFullTimeVacancyNumberAppointPanel.getText().trim()));
                }
                else {
                    tfVacancyNumber = Integer.parseInt((tfFullTimeVacancyNumberDisplayPanel.getText().trim()));
                }
            }

            catch (NumberFormatException nfe) {
                //throwing a number format exception if the input cannot be parsed into an integer
                JOptionPane.showMessageDialog(frameFullTimeStaffHire, "Vacancy number input must be a number.", "Error! " + nfe.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        }
        return tfVacancyNumber;
    }
    //method to clear the text fields of the full time operations form
    public void clearTextFullTime() {
        //getting the index of the tabbed pane and clearing textfields accordingly
        if (tpFullTimeForm.getSelectedIndex() == 0) {
            tfFullTimeDesignation.setText(null);
            tfFullTimeJobType.setText(null);
            tfFullTimeWorkingHour.setText(null);
            tfFullTimeSalary.setText(null);
            tfFullTimeVacancyNumberVacancyPanel.setText(null);
        }
        else if (tpFullTimeForm.getSelectedIndex() == 1) {
            tfFullTimeName.setText(null);
            tfFullTimeQualification.setText(null);
            tfFullTimeJoiningDate.setText(null);
            tfFullTimeAppointedBy.setText(null);
            tfFullTimeVacancyNumberAppointPanel.setText(null);
        }
        else if (tpFullTimeForm.getSelectedIndex() == 2) {
            tfFullTimeVacancyNumberDisplayPanel.setText(null);
        }
    }
    //method to remove rows from the staff table in the main menu
    public void staffTableRowRemover(int x) {
        //getting the row count of the table
        int rowCount = staffTable.getRowCount();
        int rowToDelete = 0;
        //looping through all the available row numbers
        for (int i = 0; i < rowCount; i++) {
            String rowEntry = staffTable.getValueAt(i, 0).toString().trim();
            int rowEntryNum = Integer.parseInt(rowEntry);

            if (x == rowEntryNum) {
                //checking whether the parameter equals to the value in the row and if it matches setting the index of the row to a variable
                rowToDelete = i;
                break;
            }
        }
        //deleting the row of the table
        DefaultTableModel modelStaffTable = (DefaultTableModel) staffTable.getModel();
        modelStaffTable.removeRow(rowToDelete);
    }
    //method to remove rows from the vacancy table in the main menu
    public void vacancyTableRowRemover(int x) {
        //getting the row count of the table
        int rowCount = vacancyTable.getRowCount();
        int rowToDelete = 0;
        //looping through all the available row numberss
        for (int i = 0; i < rowCount; i++) {
            String rowEntry = vacancyTable.getValueAt(i, 0).toString().trim();
            int rowEntryNum = Integer.parseInt(rowEntry);

            if (x == rowEntryNum) {
                //checking whether the parameter equals to the value in the row and if it matches setting the index number of the row to a variable
                rowToDelete = i;
                break;
            }
        }
        //deleting the row of the table
        DefaultTableModel modelVacancyTable = (DefaultTableModel) vacancyTable.getModel();
        modelVacancyTable.removeRow(rowToDelete);
    }
    //main method of the class
    public static void main (String [] args) {
        //creating a new object of the class and setting the main menu frame to visible
        new INGNepal().frameMainMenu.setVisible(true);
    }

}