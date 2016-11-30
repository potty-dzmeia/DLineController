package org.lz1aq.dlinecontroller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.Timer;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.lz1aq.dlinecontroller.DLineApplicationState.AntennaDirections;

/**
 *
 * @author Chavdar
 */
public class DLineApplication extends javax.swing.JFrame
{

    private static final long serialVersionUID = 1L;
    static final String PROGRAM_VERSION = "1.6.1";
    static final String PROGRAM_NAME    = "DLineController";
    
    private final DLineApplicationState     dLineApplicationState;
    private final DLineApplicationSettings  dLineSettings;
    private final DLineSerialComm           dLineSerialComm;

    private final DefaultComboBoxModel comPortComboBoxModel;
    private final DefaultComboBoxModel baudRateComboBoxModel;
    private final DefaultComboBoxModel deviceIdComboBoxModel;

    
    // Stuff used for automatic direction switching
    static final int MIN_SWITCHING_PERIOD_IN_MS     = 150;
    static final int DEFAULT_SWITCHING_PERIOD_IN_MS = 1000;
    static final int MAX_SWITCHING_PERIOD_IN_MS     = 60000;
    public static final int DIRECTION_NOT_SET  = -1;   
    
    private JToggleButton[]   buttonsNorthwestDirection;
    private JToggleButton[]   buttonsNorthDirection;
    private JFormattedTextField[] textfieldsDirectionsLabels;
    private JTextField[]      textfieldsSwitchingPeriods;
    private JCheckBox[]       checkboxesDirections;
    
    private Timer directionSwitchingTimer;

    /**
     * Constructor
     */
    public DLineApplication()
    {
        // Data for the Combo boxes in the settings Dialog
        this.baudRateComboBoxModel = new DefaultComboBoxModel(new String[]
        {
            "1200", "2400", "4800", "9600", "19200", "38400", "57600", "115200"
        });
        this.comPortComboBoxModel = DLineSerialComm.getComportsComboboxModel();
        this.deviceIdComboBoxModel = new DefaultComboBoxModel(new String[]
        {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        });
       
                
        // Load user settings from the properties file
        dLineSettings = new DLineApplicationSettings();
        // Init the DLineState object
        dLineApplicationState = new DLineApplicationState();

        // Create the object which will take care of sending data through the UART
        dLineSerialComm = new DLineSerialComm();

        // Print the working path and the 
        System.out.println("Working Directory = "
                + System.getProperty("user.dir"));
        System.out.println("java.library.path = " + System.getProperty("java.library.path"));
        //System.setProperty("java.library.path", yourPath); // not used 

        // Init GUI
        initComponents();
        
        // For convenience put the direction related Swing controls into an arrays
        packDirectionSwitchingControls();
    }

    
    /**
     *  Adds all the Swing Controls for direction switching into arrays
     */
    private void packDirectionSwitchingControls()
    {
        /**
         *  The order of inclusion should be the same as defined in
         *  the class AntennaDirections
         */
        
        // Direction buttons for North orientation
        buttonsNorthDirection = new JToggleButton[]{toggleButtonNDirection_0, // plusY
                                                    toggleButtonNDirection_1, // minusX
                                                    toggleButtonNDirection_2, // minusY
                                                    toggleButtonNDirection_3};// plusX
        
        // Direction buttons for North-West orientation 
        buttonsNorthwestDirection = new JToggleButton[]{toggleButtonNwDirection_0, // plusY
                                                        toggleButtonNwDirection_1, // minusX
                                                        toggleButtonNwDirection_2, // minusY
                                                        toggleButtonNwDirection_3};// plusX
        
        // Text fields for changing direction buttons labels
        textfieldsDirectionsLabels = new JFormattedTextField[]{textfieldDirectionLabel_0,
                                                         textfieldDirectionLabel_1,
                                                         textfieldDirectionLabel_2,
                                                         textfieldDirectionLabel_3};
        
        // Checkboxes for enabling/disabling direction during automatic switching
        textfieldsSwitchingPeriods = new JTextField[]{textfieldDirectionPeriod_0,
                                                      textfieldDirectionPeriod_1,
                                                      textfieldDirectionPeriod_2,
                                                      textfieldDirectionPeriod_3};
        
        checkboxesDirections = new JCheckBox[]{checkboxDirectionSwitching_0,
                                               checkboxDirectionSwitching_1,
                                               checkboxDirectionSwitching_2,
                                               checkboxDirectionSwitching_3};
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jDialogSettings = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        textfieldDirectionLabel_1 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jRadioButtonNorthWest = new javax.swing.JRadioButton();
        textfieldDirectionLabel_3 = new javax.swing.JFormattedTextField();
        textfieldDirectionLabel_2 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        textfieldDirectionLabel_0 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jRadioButtonNorth = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textfieldSettingsAntenna_1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        textfieldSettingsAntenna_2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jComboBoxDeviceId = new javax.swing.JComboBox();
        jComboBoxComPort = new javax.swing.JComboBox();
        jComboBoxBaudRate = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        textfieldVirtualCommPort = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jButtonCancel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jCheckBoxSingleElementMode = new javax.swing.JCheckBox();
        buttonGroupInSettings = new javax.swing.ButtonGroup();
        buttonGroupDirections = new javax.swing.ButtonGroup();
        jDialogPeriodicSwitching = new javax.swing.JDialog();
        checkboxDirectionSwitching_1 = new javax.swing.JCheckBox();
        checkboxDirectionSwitching_2 = new javax.swing.JCheckBox();
        checkboxDirectionSwitching_0 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        checkboxDirectionSwitching_3 = new javax.swing.JCheckBox();
        textfieldDirectionPeriod_0 = new javax.swing.JTextField();
        textfieldDirectionPeriod_1 = new javax.swing.JTextField();
        jButtonPeriodicSwitchingDialogSet = new javax.swing.JButton();
        textfieldDirectionPeriod_2 = new javax.swing.JTextField();
        textfieldDirectionPeriod_3 = new javax.swing.JTextField();
        jDialogAbout = new javax.swing.JDialog();
        buttonAboutDialogOK = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        buttonGroupDebugWindow = new javax.swing.ButtonGroup();
        jPanel11 = new javax.swing.JPanel();
        toggleButtonNwDirection_0 = new javax.swing.JToggleButton();
        toggleButtonNDirection_0 = new javax.swing.JToggleButton();
        toggleButtonNwDirection_1 = new javax.swing.JToggleButton();
        toggleButtonNDirection_3 = new javax.swing.JToggleButton();
        toggleButtonDirectionMode = new javax.swing.JToggleButton();
        toggleButtonNDirection_1 = new javax.swing.JToggleButton();
        toggleButtonNwDirection_3 = new javax.swing.JToggleButton();
        toggleButtonNDirection_2 = new javax.swing.JToggleButton();
        toggleButtonNwDirection_2 = new javax.swing.JToggleButton();
        jPanel12 = new javax.swing.JPanel();
        jToggleButtonAntennaType = new javax.swing.JToggleButton();
        jCheckBoxPeriodicSwitching = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabelSerialComm = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        jMenuSettigns = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        jDialogSettings.setTitle("Settings");
        jDialogSettings.setAlwaysOnTop(true);
        jDialogSettings.setModal(true);
        jDialogSettings.setType(java.awt.Window.Type.UTILITY);
        jDialogSettings.addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentShown(java.awt.event.ComponentEvent evt)
            {
                jDialogSettingsComponentShown(evt);
            }
        });
        jDialogSettings.getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Button settings"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        textfieldDirectionLabel_1.setText("-X");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 5);
        jPanel3.add(textfieldDirectionLabel_1, gridBagConstraints);

        jLabel6.setText("-X:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.01;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 0);
        jPanel3.add(jLabel6, gridBagConstraints);

        buttonGroupInSettings.add(jRadioButtonNorthWest);
        jRadioButtonNorthWest.setText("NW-SE orientation");
        jRadioButtonNorthWest.setToolTipText("NorthWest-SouthEast");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 5);
        jPanel3.add(jRadioButtonNorthWest, gridBagConstraints);

        textfieldDirectionLabel_3.setText("+X");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanel3.add(textfieldDirectionLabel_3, gridBagConstraints);

        textfieldDirectionLabel_2.setText("-Y");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel3.add(textfieldDirectionLabel_2, gridBagConstraints);

        jLabel5.setText("+X:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.01;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        jPanel3.add(jLabel5, gridBagConstraints);

        textfieldDirectionLabel_0.setText("+Y");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(textfieldDirectionLabel_0, gridBagConstraints);

        jLabel8.setText("-Y:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.01;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel3.add(jLabel8, gridBagConstraints);

        jLabel4.setText("+Y:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.01;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabel4, gridBagConstraints);

        jLabel3.setText("Labels of direction buttons:");
        jLabel3.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        jPanel3.add(jLabel3, gridBagConstraints);

        jLabel2.setText("Button orientation:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(jLabel2, gridBagConstraints);

        buttonGroupInSettings.add(jRadioButtonNorth);
        jRadioButtonNorth.setText("N-S orientation");
        jRadioButtonNorth.setToolTipText("North-South");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jRadioButtonNorth, gridBagConstraints);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Labels of antenna button:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        jPanel3.add(jLabel15, gridBagConstraints);

        jLabel16.setText("ant 1:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.01;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabel16, gridBagConstraints);

        textfieldSettingsAntenna_1.setText("Dipole");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(textfieldSettingsAntenna_1, gridBagConstraints);

        jLabel17.setText("ant 2:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.01;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel3.add(jLabel17, gridBagConstraints);

        textfieldSettingsAntenna_2.setText("Loop");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel3.add(textfieldSettingsAntenna_2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("CommPort settings"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jComboBoxDeviceId.setModel(deviceIdComboBoxModel);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        jPanel4.add(jComboBoxDeviceId, gridBagConstraints);

        jComboBoxComPort.setModel(this.comPortComboBoxModel);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel4.add(jComboBoxComPort, gridBagConstraints);

        jComboBoxBaudRate.setModel(this.getBaudRateComboBoxModel());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel4.add(jComboBoxBaudRate, gridBagConstraints);

        jLabel7.setText("Device ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel4.add(jLabel7, gridBagConstraints);

        jLabel12.setText("CommPort");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jLabel12, gridBagConstraints);

        jLabel13.setText("Baud rate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel4.add(jLabel13, gridBagConstraints);

        jLabel18.setText("Virtual ComPort");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jLabel18, gridBagConstraints);

        textfieldVirtualCommPort.setText(" ");
        textfieldVirtualCommPort.setToolTipText("Make sure this field is empty if you don't want to use a custom comport!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel4.add(textfieldVirtualCommPort, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 5);
        jPanel5.add(jButtonCancel, gridBagConstraints);

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 2);
        jPanel5.add(jButtonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jPanel5, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Other"));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jCheckBoxSingleElementMode.setText("Single element mode");
        jCheckBoxSingleElementMode.setToolTipText("If checked this will set bit6 to TRUE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        jPanel6.add(jCheckBoxSingleElementMode, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jDialogSettings.getContentPane().add(jPanel1, gridBagConstraints);

        jDialogPeriodicSwitching.setTitle("Periodic direction switching");
        jDialogPeriodicSwitching.setAlwaysOnTop(true);
        jDialogPeriodicSwitching.setMinimumSize(new java.awt.Dimension(50, 50));
        jDialogPeriodicSwitching.setPreferredSize(new java.awt.Dimension(200, 200));
        jDialogPeriodicSwitching.addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentShown(java.awt.event.ComponentEvent evt)
            {
                jDialogPeriodicSwitchingComponentShown(evt);
            }
            public void componentHidden(java.awt.event.ComponentEvent evt)
            {
                jDialogPeriodicSwitchingComponentHidden(evt);
            }
        });
        jDialogPeriodicSwitching.getContentPane().setLayout(new java.awt.GridBagLayout());

        checkboxDirectionSwitching_1.setText("-X");
        checkboxDirectionSwitching_1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                checkboxDirectionSwitching_1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jDialogPeriodicSwitching.getContentPane().add(checkboxDirectionSwitching_1, gridBagConstraints);

        checkboxDirectionSwitching_2.setText("-Y");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jDialogPeriodicSwitching.getContentPane().add(checkboxDirectionSwitching_2, gridBagConstraints);

        checkboxDirectionSwitching_0.setText("+Y");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jDialogPeriodicSwitching.getContentPane().add(checkboxDirectionSwitching_0, gridBagConstraints);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Direction");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jDialogPeriodicSwitching.getContentPane().add(jLabel1, gridBagConstraints);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Period [ms]");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jDialogPeriodicSwitching.getContentPane().add(jLabel9, gridBagConstraints);

        checkboxDirectionSwitching_3.setText("+X");
        checkboxDirectionSwitching_3.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jDialogPeriodicSwitching.getContentPane().add(checkboxDirectionSwitching_3, gridBagConstraints);

        textfieldDirectionPeriod_0.setText("1000");
        textfieldDirectionPeriod_0.setToolTipText("Enter a value (150-60000) and press the \"Set\" button.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jDialogPeriodicSwitching.getContentPane().add(textfieldDirectionPeriod_0, gridBagConstraints);

        textfieldDirectionPeriod_1.setText("1000");
        textfieldDirectionPeriod_1.setToolTipText("Enter a value (150-60000) and press the \"Set\" button.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jDialogPeriodicSwitching.getContentPane().add(textfieldDirectionPeriod_1, gridBagConstraints);

        jButtonPeriodicSwitchingDialogSet.setText("Set");
        jButtonPeriodicSwitchingDialogSet.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonPeriodicSwitchingDialogSetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jDialogPeriodicSwitching.getContentPane().add(jButtonPeriodicSwitchingDialogSet, gridBagConstraints);

        textfieldDirectionPeriod_2.setText("1000");
        textfieldDirectionPeriod_2.setToolTipText("Enter a value (150-60000) and press the \"Set\" button.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jDialogPeriodicSwitching.getContentPane().add(textfieldDirectionPeriod_2, gridBagConstraints);

        textfieldDirectionPeriod_3.setText("1000");
        textfieldDirectionPeriod_3.setToolTipText("Enter a value (150-60000) and press the \"Set\" button.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jDialogPeriodicSwitching.getContentPane().add(textfieldDirectionPeriod_3, gridBagConstraints);

        jDialogAbout.setTitle("DLineController v1.5");
        jDialogAbout.setAlwaysOnTop(true);
        jDialogAbout.setMinimumSize(new java.awt.Dimension(50, 50));
        jDialogAbout.setModal(true);
        jDialogAbout.setResizable(false);

        buttonAboutDialogOK.setText("OK");
        buttonAboutDialogOK.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonAboutDialogOKActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Source code available at ");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("www.active-antenna.eu");
        jTextField1.setToolTipText("");
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("by LZ1AQ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText(PROGRAM_NAME+" v."+PROGRAM_VERSION);

        jTextField3.setEditable(false);
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("github.com/potty-dzmeia/DLineController");
        jTextField3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogAboutLayout = new javax.swing.GroupLayout(jDialogAbout.getContentPane());
        jDialogAbout.getContentPane().setLayout(jDialogAboutLayout);
        jDialogAboutLayout.setHorizontalGroup(
            jDialogAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAboutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                    .addComponent(buttonAboutDialogOK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDialogAboutLayout.setVerticalGroup(
            jDialogAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAboutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAboutDialogOK)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Serial Interface to LZ1AQ's DLine Controller");
        setMinimumSize(new java.awt.Dimension(100, 100));
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowOpened(java.awt.event.WindowEvent evt)
            {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel11.setLayout(new java.awt.GridLayout(3, 3));

        buttonGroupDirections.add(toggleButtonNwDirection_0);
        toggleButtonNwDirection_0.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toggleButtonNwDirection_0.setText("+Y");
        toggleButtonNwDirection_0.setMinimumSize(new java.awt.Dimension(0, 0));
        toggleButtonNwDirection_0.setPreferredSize(new java.awt.Dimension(40, 40));
        toggleButtonNwDirection_0.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                toggleButtonNwDirection_0ItemStateChanged(evt);
            }
        });
        jPanel11.add(toggleButtonNwDirection_0);

        buttonGroupDirections.add(toggleButtonNDirection_0);
        toggleButtonNDirection_0.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toggleButtonNDirection_0.setText("+Y");
        toggleButtonNDirection_0.setMinimumSize(new java.awt.Dimension(0, 0));
        toggleButtonNDirection_0.setPreferredSize(new java.awt.Dimension(40, 40));
        toggleButtonNDirection_0.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                toggleButtonNDirection_0ItemStateChanged(evt);
            }
        });
        jPanel11.add(toggleButtonNDirection_0);

        buttonGroupDirections.add(toggleButtonNwDirection_1);
        toggleButtonNwDirection_1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toggleButtonNwDirection_1.setText("-X");
        toggleButtonNwDirection_1.setMinimumSize(new java.awt.Dimension(0, 0));
        toggleButtonNwDirection_1.setPreferredSize(new java.awt.Dimension(40, 40));
        toggleButtonNwDirection_1.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                toggleButtonNwDirection_1ItemStateChanged(evt);
            }
        });
        jPanel11.add(toggleButtonNwDirection_1);

        buttonGroupDirections.add(toggleButtonNDirection_3);
        toggleButtonNDirection_3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toggleButtonNDirection_3.setText("+X");
        toggleButtonNDirection_3.setMinimumSize(new java.awt.Dimension(0, 0));
        toggleButtonNDirection_3.setPreferredSize(new java.awt.Dimension(40, 40));
        toggleButtonNDirection_3.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                toggleButtonNDirection_3ItemStateChanged(evt);
            }
        });
        jPanel11.add(toggleButtonNDirection_3);

        toggleButtonDirectionMode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toggleButtonDirectionMode.setText("Add");
        toggleButtonDirectionMode.setToolTipText("");
        toggleButtonDirectionMode.setMinimumSize(new java.awt.Dimension(0, 0));
        toggleButtonDirectionMode.setPreferredSize(new java.awt.Dimension(40, 40));
        toggleButtonDirectionMode.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                toggleButtonDirectionModeItemStateChanged(evt);
            }
        });
        jPanel11.add(toggleButtonDirectionMode);

        buttonGroupDirections.add(toggleButtonNDirection_1);
        toggleButtonNDirection_1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toggleButtonNDirection_1.setText("-X");
        toggleButtonNDirection_1.setMinimumSize(new java.awt.Dimension(0, 0));
        toggleButtonNDirection_1.setPreferredSize(new java.awt.Dimension(40, 40));
        toggleButtonNDirection_1.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                toggleButtonNDirection_1ItemStateChanged(evt);
            }
        });
        jPanel11.add(toggleButtonNDirection_1);

        buttonGroupDirections.add(toggleButtonNwDirection_3);
        toggleButtonNwDirection_3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toggleButtonNwDirection_3.setText("+X");
        toggleButtonNwDirection_3.setMinimumSize(new java.awt.Dimension(0, 0));
        toggleButtonNwDirection_3.setPreferredSize(new java.awt.Dimension(40, 40));
        toggleButtonNwDirection_3.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                toggleButtonNwDirection_3ItemStateChanged(evt);
            }
        });
        jPanel11.add(toggleButtonNwDirection_3);

        buttonGroupDirections.add(toggleButtonNDirection_2);
        toggleButtonNDirection_2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toggleButtonNDirection_2.setText("-Y");
        toggleButtonNDirection_2.setMinimumSize(new java.awt.Dimension(0, 0));
        toggleButtonNDirection_2.setPreferredSize(new java.awt.Dimension(40, 40));
        toggleButtonNDirection_2.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                toggleButtonNDirection_2ItemStateChanged(evt);
            }
        });
        jPanel11.add(toggleButtonNDirection_2);

        buttonGroupDirections.add(toggleButtonNwDirection_2);
        toggleButtonNwDirection_2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        toggleButtonNwDirection_2.setText("-Y");
        toggleButtonNwDirection_2.setMinimumSize(new java.awt.Dimension(0, 0));
        toggleButtonNwDirection_2.setPreferredSize(new java.awt.Dimension(40, 40));
        toggleButtonNwDirection_2.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                toggleButtonNwDirection_2ItemStateChanged(evt);
            }
        });
        jPanel11.add(toggleButtonNwDirection_2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel11, gridBagConstraints);

        jPanel12.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jToggleButtonAntennaType.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jToggleButtonAntennaType.setText("Antenna Type");
        jToggleButtonAntennaType.setToolTipText("Antenna type");
        jToggleButtonAntennaType.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                jToggleButtonAntennaTypeItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jToggleButtonAntennaType, gridBagConstraints);

        jCheckBoxPeriodicSwitching.setText("Periodic Switching");
        jCheckBoxPeriodicSwitching.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBoxPeriodicSwitching.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jCheckBoxPeriodicSwitchingActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.6;
        jPanel12.add(jCheckBoxPeriodicSwitching, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.4;
        getContentPane().add(jPanel12, gridBagConstraints);

        jPanel2.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabelSerialComm.setText("Serial Comm:");
        jLabelSerialComm.setMinimumSize(null);
        jLabelSerialComm.setPreferredSize(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabelSerialComm, gridBagConstraints);
        jLabelSerialComm.getAccessibleContext().setAccessibleName("jLabelSerialComm");
        jLabelSerialComm.getAccessibleContext().setAccessibleDescription("");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(jPanel2, gridBagConstraints);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");
        fileMenu.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                fileMenuKeyPressed(evt);
            }
        });

        jMenuSettigns.setText("Settings");
        jMenuSettigns.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuSettignsActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuSettigns);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileMenuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fileMenuKeyPressed

    }//GEN-LAST:event_fileMenuKeyPressed

    private void jDialogSettingsComponentShown(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_jDialogSettingsComponentShown
    {//GEN-HEADEREND:event_jDialogSettingsComponentShown
        // Settings dialog is shown and we need to set the states of the controls
        initSettingsDialog();
    }//GEN-LAST:event_jDialogSettingsComponentShown

    private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
    {//GEN-HEADEREND:event_formWindowOpened
       initMainWindow(true);
    }//GEN-LAST:event_formWindowOpened

    private void toggleButtonNwDirection_0ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_toggleButtonNwDirection_0ItemStateChanged
    {//GEN-HEADEREND:event_toggleButtonNwDirection_0ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            onDirectionButtonPressed(AntennaDirections.plusY);
        }
    }//GEN-LAST:event_toggleButtonNwDirection_0ItemStateChanged

    private void toggleButtonNDirection_0ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_toggleButtonNDirection_0ItemStateChanged
    {//GEN-HEADEREND:event_toggleButtonNDirection_0ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {     
            onDirectionButtonPressed(AntennaDirections.plusY);
        }
    }//GEN-LAST:event_toggleButtonNDirection_0ItemStateChanged

    private void toggleButtonNwDirection_1ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_toggleButtonNwDirection_1ItemStateChanged
    {//GEN-HEADEREND:event_toggleButtonNwDirection_1ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            onDirectionButtonPressed(AntennaDirections.minusX);
        }
    }//GEN-LAST:event_toggleButtonNwDirection_1ItemStateChanged

    private void toggleButtonNDirection_1ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_toggleButtonNDirection_1ItemStateChanged
    {//GEN-HEADEREND:event_toggleButtonNDirection_1ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            onDirectionButtonPressed(AntennaDirections.minusX);
        }
    }//GEN-LAST:event_toggleButtonNDirection_1ItemStateChanged

    private void toggleButtonNwDirection_2ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_toggleButtonNwDirection_2ItemStateChanged
    {//GEN-HEADEREND:event_toggleButtonNwDirection_2ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            onDirectionButtonPressed(AntennaDirections.minusY);
        }
    }//GEN-LAST:event_toggleButtonNwDirection_2ItemStateChanged

    private void toggleButtonNDirection_2ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_toggleButtonNDirection_2ItemStateChanged
    {//GEN-HEADEREND:event_toggleButtonNDirection_2ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            onDirectionButtonPressed(AntennaDirections.minusY);
        }
    }//GEN-LAST:event_toggleButtonNDirection_2ItemStateChanged

    private void toggleButtonNwDirection_3ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_toggleButtonNwDirection_3ItemStateChanged
    {//GEN-HEADEREND:event_toggleButtonNwDirection_3ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            onDirectionButtonPressed(AntennaDirections.plusX);
        }
    }//GEN-LAST:event_toggleButtonNwDirection_3ItemStateChanged

    private void toggleButtonDirectionModeItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_toggleButtonDirectionModeItemStateChanged
    {//GEN-HEADEREND:event_toggleButtonDirectionModeItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            dLineApplicationState.setAntennaMode(DLineApplicationState.AntennaModes.additive);
            toggleButtonDirectionMode.setText(DLineApplicationState.AntennaModes.additive.toString());      
        }
        else
        {
            dLineApplicationState.setAntennaMode(DLineApplicationState.AntennaModes.subtractive);
            toggleButtonDirectionMode.setText(DLineApplicationState.AntennaModes.subtractive.toString());       
        }
        
        setJFrameTitleToShowCurrentState();
        sendSerialCommand();
    }//GEN-LAST:event_toggleButtonDirectionModeItemStateChanged

    private void jToggleButtonAntennaTypeItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_jToggleButtonAntennaTypeItemStateChanged
    {//GEN-HEADEREND:event_jToggleButtonAntennaTypeItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            dLineApplicationState.setAntennaType(DLineApplicationState.AntennaTypes.loop);
            jToggleButtonAntennaType.setText(dLineSettings.getLabelAnt2());
        }
        else if (evt.getStateChange() == ItemEvent.DESELECTED)
        {
            dLineApplicationState.setAntennaType(DLineApplicationState.AntennaTypes.dipole);
            jToggleButtonAntennaType.setText(dLineSettings.getLabelAnt1());
        }

        setJFrameTitleToShowCurrentState();
        sendSerialCommand();
    }//GEN-LAST:event_jToggleButtonAntennaTypeItemStateChanged

    private void toggleButtonNDirection_3ItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_toggleButtonNDirection_3ItemStateChanged
    {//GEN-HEADEREND:event_toggleButtonNDirection_3ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            onDirectionButtonPressed(AntennaDirections.plusX);
        }
    }//GEN-LAST:event_toggleButtonNDirection_3ItemStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        storeMainWindowParams();
        dLineSerialComm.close();
    }//GEN-LAST:event_formWindowClosing

    private void jDialogPeriodicSwitchingComponentShown(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_jDialogPeriodicSwitchingComponentShown
    {//GEN-HEADEREND:event_jDialogPeriodicSwitchingComponentShown
        initSwitchingDialog();

        // Switching logic is inside the Listener - so let's get there by starting it
        directionSwitchingTimer = new Timer(100, directionSwitchingListener);
        directionSwitchingTimer.setRepeats(false);
        directionSwitchingTimer.start();
    }//GEN-LAST:event_jDialogPeriodicSwitchingComponentShown

    private void jDialogPeriodicSwitchingComponentHidden(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_jDialogPeriodicSwitchingComponentHidden
    {//GEN-HEADEREND:event_jDialogPeriodicSwitchingComponentHidden
        directionSwitchingTimer.stop();

        if (jCheckBoxPeriodicSwitching.isSelected())
        {
            jCheckBoxPeriodicSwitching.setSelected(false);
        }
    }//GEN-LAST:event_jDialogPeriodicSwitchingComponentHidden

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_exitMenuItemActionPerformed
    {//GEN-HEADEREND:event_exitMenuItemActionPerformed
        dLineSerialComm.close();
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void jMenuSettignsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuSettignsActionPerformed
    {//GEN-HEADEREND:event_jMenuSettignsActionPerformed
        jDialogSettings.pack();
        jDialogSettings.setVisible(true);
    }//GEN-LAST:event_jMenuSettignsActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_aboutMenuItemActionPerformed
    {//GEN-HEADEREND:event_aboutMenuItemActionPerformed
        jDialogAbout.pack();
        jDialogAbout.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void buttonAboutDialogOKActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonAboutDialogOKActionPerformed
    {//GEN-HEADEREND:event_buttonAboutDialogOKActionPerformed
        jDialogAbout.setVisible(false);
    }//GEN-LAST:event_buttonAboutDialogOKActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelActionPerformed
    {//GEN-HEADEREND:event_jButtonCancelActionPerformed
        jDialogSettings.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSaveActionPerformed
    {//GEN-HEADEREND:event_jButtonSaveActionPerformed
        jDialogSettings.setVisible(false); // Hide the SettingsDialog
        storeSettingsDialogParams();       // Read the state of the controls and save them
        
        initMainWindow(false);        
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField3ActionPerformed
    {//GEN-HEADEREND:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void checkboxDirectionSwitching_1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_checkboxDirectionSwitching_1ActionPerformed
    {//GEN-HEADEREND:event_checkboxDirectionSwitching_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxDirectionSwitching_1ActionPerformed

    private void jCheckBoxPeriodicSwitchingActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jCheckBoxPeriodicSwitchingActionPerformed
    {//GEN-HEADEREND:event_jCheckBoxPeriodicSwitchingActionPerformed
        if (jCheckBoxPeriodicSwitching.isSelected())
        {
            jDialogPeriodicSwitching.pack();
            jDialogPeriodicSwitching.setVisible(true);
        }
        else
        {
            jDialogPeriodicSwitching.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBoxPeriodicSwitchingActionPerformed

    private void jButtonPeriodicSwitchingDialogSetActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonPeriodicSwitchingDialogSetActionPerformed
    {//GEN-HEADEREND:event_jButtonPeriodicSwitchingDialogSetActionPerformed
        storeSwitchingDialogParams();
    }//GEN-LAST:event_jButtonPeriodicSwitchingDialogSetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(DLineApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DLineApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DLineApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DLineApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new DLineApplication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton buttonAboutDialogOK;
    private javax.swing.ButtonGroup buttonGroupDebugWindow;
    private javax.swing.ButtonGroup buttonGroupDirections;
    private javax.swing.ButtonGroup buttonGroupInSettings;
    private javax.swing.JCheckBox checkboxDirectionSwitching_0;
    private javax.swing.JCheckBox checkboxDirectionSwitching_1;
    private javax.swing.JCheckBox checkboxDirectionSwitching_2;
    private javax.swing.JCheckBox checkboxDirectionSwitching_3;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonPeriodicSwitchingDialogSet;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBoxPeriodicSwitching;
    private javax.swing.JCheckBox jCheckBoxSingleElementMode;
    private javax.swing.JComboBox jComboBoxBaudRate;
    private javax.swing.JComboBox jComboBoxComPort;
    private javax.swing.JComboBox jComboBoxDeviceId;
    private javax.swing.JDialog jDialogAbout;
    private javax.swing.JDialog jDialogPeriodicSwitching;
    private javax.swing.JDialog jDialogSettings;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelSerialComm;
    private javax.swing.JMenuItem jMenuSettigns;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButtonNorth;
    private javax.swing.JRadioButton jRadioButtonNorthWest;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JToggleButton jToggleButtonAntennaType;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JFormattedTextField textfieldDirectionLabel_0;
    private javax.swing.JFormattedTextField textfieldDirectionLabel_1;
    private javax.swing.JFormattedTextField textfieldDirectionLabel_2;
    private javax.swing.JFormattedTextField textfieldDirectionLabel_3;
    private javax.swing.JTextField textfieldDirectionPeriod_0;
    private javax.swing.JTextField textfieldDirectionPeriod_1;
    private javax.swing.JTextField textfieldDirectionPeriod_2;
    private javax.swing.JTextField textfieldDirectionPeriod_3;
    private javax.swing.JTextField textfieldSettingsAntenna_1;
    private javax.swing.JTextField textfieldSettingsAntenna_2;
    private javax.swing.JTextField textfieldVirtualCommPort;
    private javax.swing.JToggleButton toggleButtonDirectionMode;
    private javax.swing.JToggleButton toggleButtonNDirection_0;
    private javax.swing.JToggleButton toggleButtonNDirection_1;
    private javax.swing.JToggleButton toggleButtonNDirection_2;
    private javax.swing.JToggleButton toggleButtonNDirection_3;
    private javax.swing.JToggleButton toggleButtonNwDirection_0;
    private javax.swing.JToggleButton toggleButtonNwDirection_1;
    private javax.swing.JToggleButton toggleButtonNwDirection_2;
    private javax.swing.JToggleButton toggleButtonNwDirection_3;
    // End of variables declaration//GEN-END:variables

    
    // Called when the state of the direction button changes to pressed
    private void onDirectionButtonPressed(AntennaDirections direction)
    {
        dLineApplicationState.setAntennaDirection(direction);
        setJFrameTitleToShowCurrentState();
        sendSerialCommand();
    }
    
    
    private void storeMainWindowParams()
    {
        // If not maximized...
        if (this.getExtendedState() != MAXIMIZED_BOTH)
        {
            dLineSettings.setJFrameDimensions(this.getBounds()); // save the dimensions of the JFrame
        }

        dLineSettings.SaveSettingsToDisk(); // Save all settings to disk
    }
    
    
    public DefaultComboBoxModel getBaudRateComboBoxModel()
    {
        return baudRateComboBoxModel;
    }

    /* Sets the labels of the direction buttons.*/
    private void setDirectionButtonsLabels()
    {
        for(int i=0; i<buttonsNorthDirection.length; i++)
        {
            buttonsNorthDirection[i].setText(dLineSettings.getDirectionLabel(i));
            buttonsNorthwestDirection[i].setText(dLineSettings.getDirectionLabel(i));
        }
    }

    private void setAntennaButtonsLabels()
    {
        if (jToggleButtonAntennaType.isSelected() == false)
        {
            jToggleButtonAntennaType.setText(dLineSettings.getLabelAnt1());
        }
        else
        {
            jToggleButtonAntennaType.setText(dLineSettings.getLabelAnt2());
        }
    }

    /**
     * Enables and disables the desired direction button set depending on the
     * orientation stored in dLineSettings. <br>
     */
    private void setButtonsOrientation()
    {
        if (dLineSettings.getButtonsOrientation() == DLineApplicationSettings.ButtonsOrientation.North)
        {
            for(int i=0; i<buttonsNorthDirection.length; i++)
            {
                buttonsNorthDirection[i].setVisible(true);
                buttonsNorthwestDirection[i].setVisible(false);
            }
        }
        else
        {
            for(int i=0; i<buttonsNorthDirection.length; i++)
            {
                buttonsNorthDirection[i].setVisible(false);
                buttonsNorthwestDirection[i].setVisible(true);
            }
        }

    }

    /**
     * Sends a command to the DLine Controller device. The command represents
     * the current state of the Java application.
     */
    private void sendSerialCommand()
    {
        try
        {
            // Send the command to the DLine Controller device
            dLineSerialComm.sendCommand(dLineSettings.getComPort(),
                    Integer.parseInt(dLineSettings.getBaudRate()),
                    Byte.parseByte(dLineSettings.getDeviceId()),
                    dLineApplicationState.getState());
            // Draw the command being send at the bottom of the jFrame
            final Dimension size = jLabelSerialComm.getPreferredSize();
            jLabelSerialComm.setMinimumSize(size);
            jLabelSerialComm.setPreferredSize(size);
            jLabelSerialComm.setText(dLineSettings.getComPort() + ": " + String.format("%8s", Integer.toBinaryString(dLineApplicationState.getState())).replace(" ", "0"));
        }
        catch (Exception ex)
        {
            // Draw debug info at the bottom of the jFrame
            final Dimension size = jLabelSerialComm.getPreferredSize();
            jLabelSerialComm.setMinimumSize(size);
            jLabelSerialComm.setPreferredSize(size);
            jLabelSerialComm.setText(dLineSettings.getComPort() + ": " + ex.toString());
            Logger.getLogger(DLineApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets the title of the JFrame to show the currently selected antenna and
     * direction. <br>
     * For example: "Loop: +Y(additive)" quite low
     */
    private void setJFrameTitleToShowCurrentState()
    {
        String type;
        String mode;
        String direction;

        // Read antenna Type
        switch (dLineApplicationState.getAntennaType())
        {
            case dipole:
                type = dLineSettings.getLabelAnt1();
                break;
            case loop:
            default:
                type = dLineSettings.getLabelAnt2();
                break;
        }

        // Read antenna Mode
        mode = "(" + dLineApplicationState.getAntennaMode().toString() + ")";

        // Read the antenna Direction
        switch (dLineApplicationState.getAntennaDirection())
        {
            case plusY:
                direction = dLineSettings.getDirectionLabel(AntennaDirections.plusY.ordinal());
                break;
            case minusY:
                direction = dLineSettings.getDirectionLabel(AntennaDirections.minusY.ordinal());
                break;
            case plusX:
                direction = dLineSettings.getDirectionLabel(AntennaDirections.plusX.ordinal());
                break;
            case minusX:
            default:
                direction = dLineSettings.getDirectionLabel(AntennaDirections.minusX.ordinal());
                break;
        }

        this.setTitle(type + ": " + direction + mode);
    }

  
    /**
     * User has closed the setting dialog and we need to save the state of the
     * controls
     */
    private void storeSettingsDialogParams()
    {
        dLineSettings.setDeviceId(jComboBoxDeviceId.getSelectedItem().toString());

        if (jRadioButtonNorth.isSelected())
        {
            dLineSettings.setButtonsOrientation(DLineApplicationSettings.ButtonsOrientation.North);
        }
        else
        {
            dLineSettings.setButtonsOrientation(DLineApplicationSettings.ButtonsOrientation.NorthWest);
        }

        // In case there is Virtual ComPort defined we will use it...
        if (!textfieldVirtualCommPort.getText().trim().isEmpty())
        {
            dLineSettings.setComPort(textfieldVirtualCommPort.getText());
        }
        else
        {
            if (jComboBoxComPort.getSelectedItem() != null)
            {
                dLineSettings.setComPort(jComboBoxComPort.getSelectedItem().toString());
            }
        }

        dLineSettings.setBaudRate(jComboBoxBaudRate.getSelectedItem().toString());

        // Store direction button texts
        for(int i=0; i<textfieldsDirectionsLabels.length; i++)
        {
            dLineSettings.setDirectionLabel(i, textfieldsDirectionsLabels[i].getText());     
        }

        // Store antenna button texts
        dLineSettings.setLabelAnt1(textfieldSettingsAntenna_1.getText());
        dLineSettings.setLabelAnt2(textfieldSettingsAntenna_2.getText());

        // 
        dLineSettings.setSingleElementMode(jCheckBoxSingleElementMode.isSelected());
    }

    
    /**
     * Initialize the controls of the main windows
     */
    private void initMainWindow(boolean isStartup)
    {     
        // Set the desired buttons orientation and labels  
        setButtonsOrientation();
        setDirectionButtonsLabels();
        setAntennaButtonsLabels();
        dLineApplicationState.setSingleElementMode(dLineSettings.isSingleElementMode());
        
// Read last used JFrame dimensions and restore it
        if(isStartup)
        {
            if(dLineSettings.getJFrameDimensions().isEmpty() == false)
            {
                this.setBounds(dLineSettings.getJFrameDimensions());             
            }
           
        }
        
        // set the Direction button
        getAntennaDirectionButton(dLineApplicationState.getAntennaDirection()).setSelected(true);
        
        // set Additive/Subtractive
        if(dLineApplicationState.getAntennaMode() == DLineApplicationState.AntennaModes.additive)
        {
            toggleButtonDirectionMode.setSelected(true); 
        }
        else
        {
            toggleButtonDirectionMode.setSelected(false); 
        }
        
        // set Dipole/Loop
        if(dLineApplicationState.getAntennaType() == DLineApplicationState.AntennaTypes.loop)
        {
            jToggleButtonAntennaType.setSelected(true);
        }
        else
        {
            jToggleButtonAntennaType.setSelected(false);
        }
    }
    
    
    /**
     * User has opened the setting dialog and we need to load the state of the
     * controls
     */
    private void initSettingsDialog()
    {
        jComboBoxDeviceId.setSelectedItem(dLineSettings.getDeviceId());

        if (dLineSettings.getButtonsOrientation() == DLineApplicationSettings.ButtonsOrientation.North)
        {
            jRadioButtonNorth.setSelected(true);
            jRadioButtonNorthWest.setSelected(false);
        }
        else
        {
            jRadioButtonNorth.setSelected(false);
            jRadioButtonNorthWest.setSelected(true);
        }

        // If standart ComPort is set...
        if (comPortComboBoxModel.getIndexOf(dLineSettings.getComPort()) >= 0)
        {
            jComboBoxComPort.setSelectedItem(dLineSettings.getComPort());
            textfieldVirtualCommPort.setText(""); // Custom 
        }
        else
        {
            textfieldVirtualCommPort.setText(dLineSettings.getComPort());
        }

        jComboBoxBaudRate.setSelectedItem(dLineSettings.getBaudRate());

        // Direction buttons text  
        for(int i=0; i<textfieldsDirectionsLabels.length; i++)
        {
            textfieldsDirectionsLabels[i].setText(dLineSettings.getDirectionLabel(i));
        }

        // Antenna button texts
        textfieldSettingsAntenna_1.setText(dLineSettings.getLabelAnt1());
        textfieldSettingsAntenna_2.setText(dLineSettings.getLabelAnt2());

        //
        jCheckBoxSingleElementMode.setSelected(dLineSettings.isSingleElementMode());
    }

    
    /**
     * Initializes the controls of the Switching dialog 
     */
    void initSwitchingDialog()
    {
        for(int i=0; i<checkboxesDirections.length; i++)
        {
            // Set the correct texts for each direction
            checkboxesDirections[i].setText(dLineSettings.getDirectionLabel(i));
            // Set the state for each direction
            checkboxesDirections[i].setSelected(dLineSettings.getIsDirectionCheckmarked(i));
            // Set the switching period for each direction
            textfieldsSwitchingPeriods[i].setText(Integer.toString(dLineSettings.getDirectionSwitchingPeriod(i)));
        }
    }
    
    
    /**
     * Stores all the parameters from the Switching dialog
     */
    void storeSwitchingDialogParams()
    {
        for(int i=0; i<checkboxesDirections.length; i++)
        {
            // Store which antennas are checked
            dLineSettings.setIsDirectionCheckmarked(i, checkboxesDirections[i].isSelected());
            
            // Store the switching periods for each antenna
            int period = convertStringPeriodToInt(textfieldsSwitchingPeriods[i].getText());
            dLineSettings.setDirectionSwitchingPeriod(i, period);
            textfieldsSwitchingPeriods[i].setText(Integer.toString(period)); // make sure we show the actual period
        }
    }
    
    
    /**
     * Converts the string into integer and in the process validates if the
     * value has the expected value.
     *
     * @param period Period in milliseconds
     */
    private int convertStringPeriodToInt(String stingPeriod)
    {
        int period;

        try
        {
            period = Integer.parseInt(stingPeriod);
        } 
        catch (NumberFormatException exc)
        {
            period = DEFAULT_SWITCHING_PERIOD_IN_MS;
            return period; // Return default value if the user didn't enter a number
        }

        if (period < MIN_SWITCHING_PERIOD_IN_MS)
        {
            period = MIN_SWITCHING_PERIOD_IN_MS;
        } 
        else if (period > MAX_SWITCHING_PERIOD_IN_MS)
        {
            period = MAX_SWITCHING_PERIOD_IN_MS;
        }
        
        return period;
    }
    
    
    private JToggleButton getAntennaDirectionButton(AntennaDirections button)
    {
        if (dLineSettings.getButtonsOrientation() == DLineApplicationSettings.ButtonsOrientation.North)
        {
            return buttonsNorthDirection[button.ordinal()];  
        }
        else
        {
            return buttonsNorthwestDirection[button.ordinal()]; 
        }
    }
    
    private JToggleButton getAntennaDirectionButton(int button)
    {
        if (dLineSettings.getButtonsOrientation() == DLineApplicationSettings.ButtonsOrientation.North)
        {
            return buttonsNorthDirection[button];  
        }
        else
        {
            return buttonsNorthwestDirection[button]; 
        }
    }

    
    /**
     * Handler for the automatic direction switching
     */
    private final ActionListener directionSwitchingListener = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent evt)
        {
            int next;

            //ToDo: sfsdfsf
            next = findNextCheckmarkedDirection(dLineApplicationState.getAntennaDirection().ordinal());

            // Start timer for the required period
            if (next == DIRECTION_NOT_SET)
            {
                directionSwitchingTimer.setDelay(200); // let's come back in some time and check if the user has checked some direction
                directionSwitchingTimer.start();
            }
            else
            {
                // Press the direction button
                getAntennaDirectionButton(next).setSelected(true);

                int perdiod = dLineSettings.getDirectionSwitchingPeriod(next);
                directionSwitchingTimer.setInitialDelay(perdiod);
                directionSwitchingTimer.start();
            }
        }
    };
    
    
    
    /**
     * Finds the next direction from the SwitchingDialog that is checkmarked
     * 
     * Example: current CheckmarkedDirection is 2 and we have the 
     * following direction selection: 
     * 0 - no checked 
     * 1 - checked 
     * 2 - checked 
     * 3 - not checked.
     *
     * In this case the currentAnt will be set to ANT2
     * 
     * @param currentCheckmarkedAntenna - a value from ANT_NOTSET to ANT_4
     * @return Next checkmarked antenna (a value from ANT_NOTSET to ANT_4)
     */
    int findNextCheckmarkedDirection(int currentCheckmarkedDirection)
    {
        // Get the checkmark status for each direction
        boolean[] arrayIsEnabled = new boolean[checkboxesDirections.length];
        for(int i=0; i<arrayIsEnabled.length; i++)
        {
            arrayIsEnabled[i] = dLineSettings.getIsDirectionCheckmarked(i);
        }

        // Traverse all antennas starting from the currentCheckmarkedAntenna
        int direction = currentCheckmarkedDirection;
        
        for (int i = 0; i < checkboxesDirections.length; i++)
        {
            direction++;
            if (direction >= checkboxesDirections.length)
            {
                // start from the first direction if we reach the end of the array
                direction = 0; 
            }

            if (arrayIsEnabled[direction])
            {
                return direction; // found next
            }
        }

        return DIRECTION_NOT_SET; // We didn't find selected antenna
    }
}
