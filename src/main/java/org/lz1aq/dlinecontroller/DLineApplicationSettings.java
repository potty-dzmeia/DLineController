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
    static final String PROPERTY_DIRECTION_LABEL    = "direction_label";
    static final String PROPERTY_LABEL_ANT1         = "labelAnt1";
    static final String PROPERTY_LABEL_ANT2         = "labelAnt2";
    
    static final String PROPERTY_DIRECTION_SWITCHING_PERIOD  = "direction_period";
    static final String PROPERTY_DIRECTION_IS_CHECKMARKED  = "direction_is_checkmarked";
    
    public static final int DIRECTIONS_COUNT = 4; // The number of direction buttons
    
    /**
     *  The possible layout for the direction buttons
     */
    public enum ButtonsOrientation{North, NorthWest};
    

    private String      deviceId;   // This is used when composing the command send thru the serial interface
    private String      comPort;
    private String      baudRate; 
    private String      labelAnt1;
    private String      labelAnt2;
    private Rectangle   jFrameDimensions;             // JFrame settings: position and size
    private boolean     isSingleElementMode = false;  // This setting is not saved to the file
    private final String[]  arrayDirectionsSwitchingPeriods;  // Duration period for a direction during the automatic switching
    private final String[]  arrayIsDirectionCheckmarked;      // If the direction is enabled for automatic switching
    private final String[]  arrayDirectionLabels;             // Labels for the direction buttons
    private ButtonsOrientation buttonsOrientation; // The layout scheme for the direction buttons. 
    private final Properties   prop;
    
    /**
     * Tries to read the settings from the disk. If it fails default values are used.
     */
    public DLineApplicationSettings()
    {     
        this.prop         = new Properties();
        jFrameDimensions  = new Rectangle();
        arrayDirectionLabels            = new String[DIRECTIONS_COUNT];
        arrayIsDirectionCheckmarked     = new String[DIRECTIONS_COUNT];
        arrayDirectionsSwitchingPeriods = new String[DIRECTIONS_COUNT];
        
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
    public ButtonsOrientation getButtonsOrientation()
    {
        return buttonsOrientation;
    }

    /**
     * 
     * @param orientation   Possible values are: Orientation_North or Orientation_NorthWest
     */
    public void setButtonsOrientation(ButtonsOrientation orientation)
    {
        this.buttonsOrientation = orientation;
    }

    /**
     * Get the name for the direction button
     * 
     * @param directionIndex - Number from 0 to DIRECTION_COUNT. 
     * @return The label for the selected direction
     */
    public String getDirectionLabel(int directionIndex)
    {
        return arrayDirectionLabels[directionIndex];
    }
    
    /**
     * Sets the name of the direction button
     * 
     * @param directionIndex - Number from 0 to DIRECTION_COUNT. 
     * @param label - name for the direction button
     */
    public void setDirectionLabel(int directionIndex, String label)
    {
        arrayDirectionLabels[directionIndex] = label;
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
     * This setting is not saved to a file (also it is initialized to 0 on object
     * creation)
     * 
     * @param isEnabled True is single element mode is enabled
     */
    public void setSingleElementMode(boolean isEnabled)
    {
      this.isSingleElementMode = isEnabled;
    }
    
    /**
     * This setting is not saved to a file (also it is initialized to 0 on object
     * creation)
     * 
   * @return True is single element mode is enabled
     */
    public boolean isSingleElementMode()
    {
      return this.isSingleElementMode;
    }
    
    
    public void setDirectionSwitchingPeriod(int directionIndex, int periodInMs)
    {
      arrayDirectionsSwitchingPeriods[directionIndex] = Integer.toString(periodInMs);
    }
    
    public int getDirectionSwitchingPeriod(int directionIndex)
    {
      return Integer.parseInt(arrayDirectionsSwitchingPeriods[directionIndex]);
    }
    
    public void setIsDirectionCheckmarked(int directionIndex, boolean isCycled)
    {
      arrayIsDirectionCheckmarked[directionIndex] = Boolean.toString(isCycled);
    }   
    
    public boolean getIsDirectionCheckmarked(int directionIndex)
    {
      return Boolean.parseBoolean(arrayIsDirectionCheckmarked[directionIndex]);
    }
 
    
    /**
     * Stores the array of values into properties which are named using
     * key+index of the value
     * 
     * @param key - property key that will be used for writing this property
     * @param values - array of values that will be written
     */
    private void setProperties(String key, String[] values)
    {
      for(int i=0; i<values.length; i++)
      {
        prop.setProperty(key+i, values[i]);
      }
    }
    
    
    private void getProperties(String key, String[] values)
    {
      for(int i=0; i<values.length; i++)
      {
        values[i] = prop.getProperty(key+i);
      }
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
        prop.setProperty(PROPERTY_BUTTON_ORIENTATION, buttonsOrientation.toString());
        
        // Now save the JFrame dimensions:
        prop.setProperty(PROPERTY_MAIN_WINDOW_X, Integer.toString(jFrameDimensions.x));
        prop.setProperty(PROPERTY_MAIN_WINDOW_Y, Integer.toString(jFrameDimensions.y));
        prop.setProperty(PROPERTY_MAIN_WINDOW_WIDTH, Integer.toString(jFrameDimensions.width));
        prop.setProperty(PROPERTY_MAIN_WINDOW_HEIGHT, Integer.toString(jFrameDimensions.height));
        
        // Now save the texts for the Direction Buttons
        setProperties(PROPERTY_DIRECTION_LABEL, arrayDirectionLabels);
         
        // Texts for antena types
        prop.setProperty(PROPERTY_LABEL_ANT1, labelAnt1);
        prop.setProperty(PROPERTY_LABEL_ANT2, labelAnt2);
        
        // Switching periods for the directions
        setProperties(PROPERTY_DIRECTION_SWITCHING_PERIOD, arrayDirectionsSwitchingPeriods);
        // isCheckmarked for each directions button
        setProperties(PROPERTY_DIRECTION_IS_CHECKMARKED, arrayIsDirectionCheckmarked);
        
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
     * Loads the settings from a settings file
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
            if(temp == null)
                throwMissingPropertyException(PROPERTY_BUTTON_ORIENTATION);
            
            if(temp.equals(ButtonsOrientation.North.toString()))
            {
                buttonsOrientation = ButtonsOrientation.North;
            }
            else
            {
                buttonsOrientation = ButtonsOrientation.NorthWest;
            }
            
            
            // Read the JFrame dimensions:
            int x = Integer.parseInt(prop.getProperty(PROPERTY_MAIN_WINDOW_X));
            int y = Integer.parseInt(prop.getProperty(PROPERTY_MAIN_WINDOW_Y));
            int w = Integer.parseInt(prop.getProperty(PROPERTY_MAIN_WINDOW_WIDTH));
            int h = Integer.parseInt(prop.getProperty(PROPERTY_MAIN_WINDOW_HEIGHT));
            
            this.jFrameDimensions = new Rectangle(x,y,w,h);
            
            // Now read the texts for the Direction Buttons
            getProperties(PROPERTY_DIRECTION_LABEL, arrayDirectionLabels);
            
            //Read the antenna type texts
            labelAnt1  = prop.getProperty(PROPERTY_LABEL_ANT1);
            if(labelAnt1 == null)
                throwMissingPropertyException(PROPERTY_LABEL_ANT1);
            labelAnt2 = prop.getProperty(PROPERTY_LABEL_ANT2);
            if(labelAnt2 == null)
                throwMissingPropertyException(PROPERTY_LABEL_ANT2);
                    
            // Switching periods for the antennas
            getProperties(PROPERTY_DIRECTION_SWITCHING_PERIOD, arrayDirectionsSwitchingPeriods);
            // isCycled for each antenna
            getProperties(PROPERTY_DIRECTION_IS_CHECKMARKED, arrayIsDirectionCheckmarked);
            
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
        buttonsOrientation = ButtonsOrientation.North;        
        
        // We have minimum size so we don't have to worry about the values:
        jFrameDimensions.height = 0;
        jFrameDimensions.width = 0;
        jFrameDimensions.x = 0;
        jFrameDimensions.y = 0;
        
        // Set texts for the direction buttons
        arrayDirectionLabels[DLineApplicationState.AntennaDirections.plusY.ordinal()]  = "+Y";
        arrayDirectionLabels[DLineApplicationState.AntennaDirections.minusY.ordinal()] = "-Y"; 
        arrayDirectionLabels[DLineApplicationState.AntennaDirections.plusX.ordinal()]  = "+X";
        arrayDirectionLabels[DLineApplicationState.AntennaDirections.minusX.ordinal()] = "-X"; 
        
        // Set texts for the antenna types
        labelAnt1 = DLineApplicationState.AntennaTypes.dipole.toString();
        labelAnt2 = DLineApplicationState.AntennaTypes.loop.toString();;
        
        // Switching periods
        for(int i=0; i<arrayDirectionsSwitchingPeriods.length; i++)
        {
          arrayDirectionsSwitchingPeriods[i] = Integer.toString(DLineApplication.DEFAULT_SWITCHING_PERIOD_IN_MS);
        }
        
        // isDirectionCheckmarked
        for(int i=0; i<arrayIsDirectionCheckmarked.length; i++)
        {
          arrayIsDirectionCheckmarked[i] = Boolean.toString(true);
        }
                
    }
    
    void throwMissingPropertyException(String propertyName) throws Exception
    {
      throw new Exception("Error when trying to read element " + propertyName + " from file " + SETTINGS_FILE_NAME);
    }
}
