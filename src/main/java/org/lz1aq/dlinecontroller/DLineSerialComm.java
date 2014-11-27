/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lz1aq.dlinecontroller;

import gnu.io.*;

import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;

/**
 *
 * @author Pottry
 */
public class DLineSerialComm 
{
     private CommPort     commPort = null;
     private String       commPortName = null;
     private OutputStream out = null;
     
    /**Checks which COM ports are available for usage.
     * @return    A HashSet containing the CommPortIdentifier for all serial 
     * ports that are not currently being used.
     */
    public static HashSet<String> getAvailableSerialPorts() 
    {
        HashSet<String> h = new HashSet<String>();
        Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
        while (thePorts.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
            switch (com.getPortType()) {
            case CommPortIdentifier.PORT_SERIAL:
                //try {
                    //CommPort thePort = com.open("CommUtil", 50);
                    //thePort.close();
                    h.add(com.getName());
//                } catch (PortInUseException e) {
//                    System.out.println("Port, "  + com.getName() + ", is in use.");
//                } catch (Exception e) {
//                    System.err.println("Failed to open port " +  com.getName());
//                    e.printStackTrace();
//                }
            }
        }
        return h;
    }

    
    /** Sends command to the DLine Controller device
     * @param portName
     * @param baudRate
     * @param deviceId
     * @param command
     * @throws Exception 
     */
    public void sendCommand(String portName,
                            int    baudRate,
                            byte   deviceId,
                            byte   command)  throws Exception
    {
        
        // If no comm port has ben yet opened
        if(commPort == null)
        {
            System.out.println("First time opening commPort");
            OpenPortAndGetStream(portName, baudRate);
        }
        // If another port needs to be used
        else if(portName.compareTo(commPortName) !=0)
        {
            System.out.println("Opening another commPort");
            commPort.close();
            OpenPortAndGetStream(portName, baudRate);
        }
            
        
        out.write(128+deviceId); // Each command for the DLine controller should be preceeded by 0x80
        out.write(command);      // Send the actual command
        out.flush();
    }
 
   
    public void close()
    {
        if(commPort!=null)
        {
            commPort.close();
        }
    }
    private void OpenPortAndGetStream(String comPort, int baudRate)  throws Exception
    {
        commPortName = comPort;
        
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(comPort);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            throw new Exception("Is currently owned by another application!");
        } 
        
        commPort = portIdentifier.open(this.getClass().getName(),2000);
      
        if (commPort instanceof SerialPort)
        {
            SerialPort serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(baudRate,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            out = serialPort.getOutputStream();
        }
        else
        {
            System.out.println("Error: Only serial ports are handled by this example.");
        }
    }
 
}
