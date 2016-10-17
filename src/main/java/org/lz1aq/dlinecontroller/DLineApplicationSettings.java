package org.lz1aq.dlinecontroller;


import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Chavdar
 */
public final class DLineApplicationSettings 
{
    static final String SETTINGS_FILE_NAME          = "DLineSettings.properties";
    static final String PROPERTY_DEVICE_ID          = "deviceId";
    static final String PROPERTY_COMPORT            = "comPort";
    static final String PROPERTY_BAUDE_RATE         = "baudRate";
    static final String PROPERTY_BUTTON_ORIENTATION = "buttonOrientation";
    static final String PROPERTY_MAIN_WINDOW_X      = "x";
    static final String PROPERTY_MAIN_WINDOW_Y      = "y";
    static final String PROPERTY_MAIN_WINDOW_WIDTH  = "w";
    static final String PROPERTY_MAIN_WINDOW_HEIGHT = "h";
    static final String PROPERTY_LABEL_PLUS_Y       = "labelPlusY";
    static final String PROPERTY_LABEL_MINUS_Y      = "labelMinusY";
    static final String PROPERTY_LABEL_PLUS_X       = "labelPlusX";
    static final String PROPERTY_LABEL_MINUS_X      = "labelMinusX";
    static final String PROPERTY_LABEL_ANT1         = "labelAnt1";
    static final String PROPERTY_LABEL_ANT2         = "labelAnt2";
    
    
    /**
     *  The possible layout for the direction buttons
     */
    public enum ButtonOrientation{North, NorthWest};
    

    private String  deviceId;   // This is used when composing the command send thru the serial interface
    private String  comPort;
    private String  baudRate; 
    private ButtonOrientation buttonOrientation; // The layout scheme for the direction buttons. 
    private Rectangle jFrameDimensions; // JFrame settings: position and size
    private String labelPlusY;
    private String labelMinusY;
    private String labelPlusX;
    private String labelMinusX;
    private String labelAnt1;
    private String labelAnt2;
    private boolean isSingleElementMode = false; // This setting is not saved to the file
    
    private final Properties prop;
    
    
    
    /**
     * Tries to read the settings from the disk. If it fails default values are used.
     */
    public DLineApplicationSettings()
    {     
        this.prop        = new Properties();
        jFrameDimensions = new Rectangle();
        this.LoadSettingsFromDisk();
    }

    
    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }
    
    public Rectangle getJFrameDimensions()
    {
        return jFrameDimensions;
    }

    public void setJFrameDimensions(Rectangle jFrameDimensions)
    {
        this.jFrameDimensions = jFrameDimensions;
    }

    public String getComPort()
    {
        return comPort;
    }

    public void setComPort(String comPort)
    {
        this.comPort = comPort;
    }

    public String getBaudRate()
    {
        return baudRate;
    }

    public void setBaudRate(String baudRate)
    {
        this.baudRate = baudRate;
    }

    /**The orientation that the buttons should have
     * 
     * @return Possible values are: North or NorthWest
     */
    public ButtonOrientation getButtonOrientation()
    {
        return buttonOrientation;
    }

    /**
     * 
     * @param buttonOrientation   Possible values are: Orientation_North or Orientation_NorthWest
     */
    public void setButtonOrientation(ButtonOrientation buttonOrientation)
    {
        this.buttonOrientation = buttonOrientation;
    }

    
    public String getLabelPlusY()
    {
        return labelPlusY;
    }
    
    public void setLabelPlusY(String labelPlusY)
    {
        this.labelPlusY = labelPlusY;
    }

    
    public String getLabelMinusY()
    {
        return labelMinusY;
    }
    
    public void setLabelMinusY(String labelMinusY)
    {
        this.labelMinusY = labelMinusY;
    }

    
    public String getLabelPlusX()
    {
        return labelPlusX;
    }
    
    public void setLabelPlusX(String labelPlusX)
    {
        this.labelPlusX = labelPlusX;
    }

    
    public String getLabelMinusX()
    {
        return labelMinusX;
    }
    
    public void setLabelMinusX(String labelMinusX)
    {
        this.labelMinusX = labelMinusX;
    }
    
    
    public void setLabelAnt1(String labelAnt1)
    {
      this.labelAnt1 = labelAnt1;
    }
    public String getLabelAnt1()
    {
      return labelAnt1;
    }
    
    public void setLabelAnt2(String labelAnt2)
    {
      this.labelAnt2 = labelAnt2;
    }
    public String getLabelAnt2()
    {
      return labelAnt2;
    }
    
    

    /**
     * This setting is not saved to a file (i.e. it is set to 0 on object
     * creation)
     * 
     * @param isEnabled True is single element mode is enabled
     */
    public void setSingleElementMode(boolean isEnabled)
    {
      this.isSingleElementMode = isEnabled;
    }
    
    /**
     * This setting is not saved to a file (i.e. it is set to 0 every time
     * the object is created)
     * 
   * @return True is single element mode is enabled
     */
    public boolean isSingleElementMode()
    {
      return this.isSingleElementMode;
    }
    
    /**
     * Saves the settings into a file called "DLineSettings.properties"
     */
    public void SaveSettingsToDisk()
    {
        // Store last used antenna and direction:
        prop.setProperty(PROPERTY_DEVICE_ID, deviceId);
        prop.setProperty(PROPERTY_COMPORT, comPort);
        prop.setProperty(PROPERTY_BAUDE_RATE, baudRate);
        prop.setProperty(PROPERTY_BUTTON_ORIENTATION, buttonOrientation.toString());
        
        // Now save the JFrame dimensions:
        prop.setProperty(PROPERTY_MAIN_WINDOW_X, Integer.toString(jFrameDimensions.x));
        prop.setProperty(PROPERTY_MAIN_WINDOW_Y, Integer.toString(jFrameDimensions.y));
        prop.setProperty(PROPERTY_MAIN_WINDOW_WIDTH, Integer.toString(jFrameDimensions.width));
        prop.setProperty(PROPERTY_MAIN_WINDOW_HEIGHT, Integer.toString(jFrameDimensions.height));
        
        // Now save the texts for the Direction Buttons
        prop.setProperty(PROPERTY_LABEL_PLUS_Y, labelPlusY);
        prop.setProperty(PROPERTY_LABEL_MINUS_Y, labelMinusY);
        prop.setProperty(PROPERTY_LABEL_PLUS_X, labelPlusX);
        prop.setProperty(PROPERTY_LABEL_MINUS_X, labelMinusX);
        
        // Texts for antena types
        prop.setProperty(PROPERTY_LABEL_ANT1, labelAnt1);
        prop.setProperty(PROPERTY_LABEL_ANT2, labelAnt2);
        
        try
        {
            prop.store(new FileOutputStream(SETTINGS_FILE_NAME), null);
        } catch (IOException ex)
        {
            Logger.getLogger(DLineApplicationSettings.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }

           
        
     
    }
    
    /**
     * Loads the settings from a file called DLineSettings.properties
     */
    public void LoadSettingsFromDisk()
    {
        try
        {
            
            prop.load(new FileInputStream(SETTINGS_FILE_NAME));
            
            deviceId = prop.getProperty(PROPERTY_DEVICE_ID);
            if(deviceId == null)
                throwMissingPropertyException(PROPERTY_DEVICE_ID);
            
            comPort  = prop.getProperty(PROPERTY_COMPORT);
            if(comPort == null)
                throwMissingPropertyException(PROPERTY_COMPORT);
                
            baudRate = prop.getProperty(PROPERTY_BAUDE_RATE);
            if(baudRate == null)
                throwMissingPropertyException(PROPERTY_BAUDE_RATE);
            
           
            String temp = prop.getProperty(PROPERTY_BUTTON_ORIENTATION);
            if(temp==null)
                throwMissingPropertyException(PROPERTY_BUTTON_ORIENTATION);
            
            if(temp.equals(ButtonOrientation.North.toString()))
            {
                buttonOrientation = ButtonOrientation.North;
            }
            else
            {
                buttonOrientation = ButtonOrientation.NorthWest;
            }
            
            
            // Read the JFrame dimensions:
            int x = Integer.parseInt(prop.getProperty(PROPERTY_MAIN_WINDOW_X));
            int y = Integer.parseInt(prop.getProperty(PROPERTY_MAIN_WINDOW_Y));
            int w = Integer.parseInt(prop.getProperty(PROPERTY_MAIN_WINDOW_WIDTH));
            int h = Integer.parseInt(prop.getProperty(PROPERTY_MAIN_WINDOW_HEIGHT));
            
            this.jFrameDimensions = new Rectangle(x,y,w,h);
            
            // Now read the texts for the Direction Buttons
            labelPlusY  = prop.getProperty(PROPERTY_LABEL_PLUS_Y);
            if(labelPlusY == null)
                throwMissingPropertyException(PROPERTY_LABEL_PLUS_Y);
            labelMinusY = prop.getProperty(PROPERTY_LABEL_MINUS_Y);
            if(labelMinusY == null)
                throwMissingPropertyException(PROPERTY_LABEL_MINUS_Y);
            labelPlusX  = prop.getProperty(PROPERTY_LABEL_PLUS_X);
            if(labelPlusX == null)
                throwMissingPropertyException(PROPERTY_LABEL_PLUS_X);
            labelMinusX = prop.getProperty(PROPERTY_LABEL_MINUS_X);
            if(labelMinusX == null)
                throwMissingPropertyException(PROPERTY_LABEL_MINUS_X);
            
            //Read the antenna type texts
            labelAnt1  = prop.getProperty(PROPERTY_LABEL_ANT1);
            if(labelAnt1 == null)
                throwMissingPropertyException(PROPERTY_LABEL_ANT1);
            labelAnt2 = prop.getProperty(PROPERTY_LABEL_ANT2);
            if(labelAnt2 == null)
                throwMissingPropertyException(PROPERTY_LABEL_ANT2);
                    
        } catch (IOException ex)
        {
            // If some error we will set to default values
            this.SetSettingsToDefault();
            Logger.getLogger(DLineApplicationSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NumberFormatException ex)
        {
            // If some error we will set to default values
            this.SetSettingsToDefault();
            Logger.getLogger(DLineApplicationSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex)
        {
            this.SetSettingsToDefault();
            Logger.getLogger(DLineApplicationSettings.class.getName()).log(Level.SEVERE, null, ex);    
        }
                    
    }
    
    /**
     * Set all settings to default
     */
    private void SetSettingsToDefault()
    {
        deviceId = "0";
        comPort = "";
        baudRate = "2400"; 
        buttonOrientation = ButtonOrientation.North;        
        
        // We have minimum size so we don't have to worry about the values:
        jFrameDimensions.height = 0;
        jFrameDimensions.width = 0;
        jFrameDimensions.x = 0;
        jFrameDimensions.y = 0;
        
        // Set texts for the direction buttons
        labelPlusY  = "+Y";
        labelMinusY = "-Y"; 
        labelPlusX  = "+X";
        labelMinusX = "-X"; 
        
        // Set texts for the antenna types
        labelAnt1 = "Dipole";
        labelAnt2 = "Loop";
                
    }
    
    void throwMissingPropertyException(String propertyName) throws Exception
    {
      throw new Exception("Error when trying to read element " + propertyName + " from file " + SETTINGS_FILE_NAME);
    }
}
