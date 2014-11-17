package dlinecontroller_lz1aq;


import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chavdar
 */
public final class DLineApplicationSettings 
{

    /**
     *  The possible layout for the direction buttons
     */
    public enum ButtonOrientation{
        North,
        NorthWest
    };
    

    private String  deviceId;   // This is used when composing the command send thru the serial interface

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }
    private String  comPort;
    private String  baudRate; 
    private ButtonOrientation buttonOrientation; // The layout scheme for the direction buttons. 
    private Rectangle jFrameDimensions; // JFrame settings: position and size
    private String labelPlusY;
    private String labelMinusY;
    private String labelPlusX;
    private String labelMinusX;
    
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

    
    
    /**
     * Saves the settings into a file called "DLineSettings.properties"
     */
    public void SaveSettingsToDisk()
    {
        // Store last used antenna and direction:
        prop.setProperty("deviceId", deviceId);
        prop.setProperty("comPort", comPort);
        prop.setProperty("baudRate", baudRate);
        prop.setProperty("buttonOrientation", buttonOrientation.toString());
        
        // Now save the JFrame dimensions:
        prop.setProperty("x", Integer.toString(jFrameDimensions.x));
        prop.setProperty("y", Integer.toString(jFrameDimensions.y));
        prop.setProperty("w", Integer.toString(jFrameDimensions.width));
        prop.setProperty("h", Integer.toString(jFrameDimensions.height));
        
        // Now save the texts for the Direction Buttons
        prop.setProperty("labelPlusY", labelPlusY);
        prop.setProperty("labelMinusY", labelMinusY);
        prop.setProperty("labelPlusX", labelPlusX);
        prop.setProperty("labelMinusX", labelMinusX);
        
        try
        {
            prop.store(new FileOutputStream("DLineSettings.properties"), null);
        } catch (IOException ex)
        {
            Logger.getLogger(DLineApplicationSettings.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }

           
        
     
    }
    
    /**
     * Loads the settings from a file called "DLineSettings.properties"
     */
    public void LoadSettingsFromDisk()
    {
        try
        {
            // Read last used antenna and direction
            prop.load(new FileInputStream("DLineSettings.properties"));
            
            deviceId = prop.getProperty("deviceId");
            if(deviceId == null)
                throw new Exception("DLineSettings.properties error: DeviceId missing");
            
            comPort  = prop.getProperty("comPort");
            if(comPort == null)
                throw new Exception("DLineSettings.properties error: comPort field is missing");
                
            baudRate = prop.getProperty("baudRate");
            if(baudRate == null)
                throw new Exception("DLineSettings.properties error: baudRate field is missing");
            if(prop.getProperty("buttonOrientation").equals(ButtonOrientation.North.toString()))
            {
                buttonOrientation = ButtonOrientation.North;
            }
            else
            {
                buttonOrientation = ButtonOrientation.NorthWest;
            }
            
            
            // Read the JFrame dimensions:
            int x = Integer.parseInt(prop.getProperty("x"));
            int y = Integer.parseInt(prop.getProperty("y"));
            int w = Integer.parseInt(prop.getProperty("w"));
            int h = Integer.parseInt(prop.getProperty("h"));
            
            this.jFrameDimensions = new Rectangle(x,y,w,h);
            
            // Now save the texts for the Direction Buttons
            labelPlusY  = prop.getProperty("labelPlusY");
            if(labelPlusY == null)
                throw new Exception("DLineSettings.properties error: labelPlusY field is missing");    
            labelMinusY = prop.getProperty("labelMinusY");
            if(labelMinusY == null)
                throw new Exception("DLineSettings.properties error: labelMinusY field is missing");
            labelPlusX  = prop.getProperty("labelPlusX");
            if(labelPlusX == null)
                throw new Exception("DLineSettings.properties error: labelPlusX field is missing");
            labelMinusX = prop.getProperty("labelMinusX");
            if(labelMinusX == null)
                throw new Exception("DLineSettings.properties error: labelMinusX field is missing");
                    
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
    }
    
    
      
}
