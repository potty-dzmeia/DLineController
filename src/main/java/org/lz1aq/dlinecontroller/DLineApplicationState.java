/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package org.lz1aq.dlinecontroller;

/**
 * 
 * Keeps the state of the current direction selection and the type of the 
 * antenna being used (i.e. Dipole or Loop).
 *
 * @author Pottry
 */
public class DLineApplicationState 
{
   
    private final DLineHardwareState    hardwareState;   // State of the relays in the DLine Controller
    private AntennaDirections           antennaDirection;// Current direction selection
    private AntennaModes                antennaMode;     // Dipole or Loop
   
    private final byte TRUE  = 1;
    private final byte FALSE = 0;
    
    
    DLineApplicationState()
    {
        antennaDirection  = DLineApplicationState.AntennaDirections.undefined;
        antennaMode       = DLineApplicationState.AntennaModes.undefined;
        hardwareState     = new DLineHardwareState();
    }
    
    
    /** Set the desired direction of the antenna.
     * 
     * @param direction Possible antenna directions plusY, minusY, plusX. minusX,
     * Add.
     */
    public void setAntennaDirection(AntennaDirections direction)
    {
        antennaDirection = direction;    
         
        // Mange the DLine Controller states:
        // ---------------------------------       
        switch(direction)
        {
            case plusY:
                hardwareState.setBit0(TRUE); // Y
                hardwareState.setBit1(TRUE); // directive
                hardwareState.setBit2(FALSE); // (+)
                break;
                
            case minusY:
                hardwareState.setBit0(TRUE); // Y
                hardwareState.setBit1(TRUE); // directive
                hardwareState.setBit2(TRUE); // (-)
                break;
                
            case plusX:
                hardwareState.setBit0(FALSE); // X
                hardwareState.setBit1(TRUE); // directive
                hardwareState.setBit2(FALSE); // (+)
                break;
                
            case minusX:
                hardwareState.setBit0(FALSE); // X
                hardwareState.setBit1(TRUE); // directive
                hardwareState.setBit2(TRUE); // (-)
                break;
                
            case add:
                hardwareState.setBit1(FALSE); // Additive
                break;
                
            case undefined:
                // Do nothing
                break;
        }
                
    
        //Also make sure that we have valid antenna mode:
        if(antennaMode == AntennaModes.undefined)
        {
            this.setAntennaMode(AntennaModes.dipole);
        }
    }

    /** Sets the current antenna mode
     * 
     * @param mode Possible modes are dipole or loop
     */
    public void setAntennaMode(AntennaModes mode)
    {
        antennaMode = mode;
           
       // Mange the DLine Controller states:
       // ---------------------------------  
       switch(mode)
       {
           case dipole:
               hardwareState.setDipole();
               break;
           case loop:
               hardwareState.setLoop();
               break;
           case undefined:
               // do nothing
               break;
       }
       
        
       //Also make sure that we have valid antenna direction:
       if(antennaDirection == AntennaDirections.undefined)
       {
           this.setAntennaDirection(AntennaDirections.add);
       }
    }
    
    
    public AntennaModes getAntennaMode()
    {
        return antennaMode;
    }
    
    public AntennaDirections getAntennaDirection()
    {
        return antennaDirection;
    }
    
    /** Returns which was the last directive mode that was used: Y or X
     *  This is useful when the current direction is "Add" and we need to inform
     *  the user about the last directive mode.
     * @return Returns plusY if the last direction mode was Y <br>
     *         Returns plusX if the last direction mode was X
     */
    public AntennaDirections getLastDirectiveMode()
    {
        if( hardwareState.getBit0() == 0)
            return AntennaDirections.plusX;
        else
            return AntennaDirections.plusY;
    }
    
    
    /**
     * Gets the state in form of a byte. This byte should be send over the 
     * serial interface to the DLine Controller device. 
     *  
     * @return bit0- X or Y direction; bit1- directive mode("Add"); 
     * bit2-  (+) or (-); bit3- LoopB; bit4- LoopA; bit5- Dipole; 
     * bits 6 and 7 are not used (always 0)
     */
    public byte getState()
    {
        return hardwareState.getState();        
    }
    
   
    
    /**
     * 
     */
    public static enum AntennaDirections
    {
        plusY,  
        minusY,
        plusX,
        minusX,
        add,
        undefined
    }
    
    /**
     * Two antenna modes are available
     */
    public static enum AntennaModes
    {
        dipole,
        loop,
        undefined
    }
    
    
     /**
     * This class defines the state of the DLine Controller device. The state is
     * defined by the states of its relays.<br> <br>
     * 
     * The class holds the state of each relay. These states later can be packet
     * into a byte and sent over the serial interface towards the DLine Controller
     * device.
     */
    private class DLineHardwareState 
    {
        /**
         * 1: Y direction (relay K1 is ON) <br>
         * 0: X direction (relay K1 is OFF)<br> 
         */
        private byte bit0 = 0; 

        /**
         * 1: Directive mode (relay K2 is ON)      <br>
         * 0: Additive mode (relay K2 is OFF, additive)<br>
         */
        private byte bit1 = 0;

        /**
         * 1: (-) backward direction (relay K3 is ON)  <br>
         * 0: (+) forward direction  (relay K3 is OFF) <br>
         */
        private byte bit2 = 0;

        /**
         * 1: Loop-B mode     (Line Lb is  in  low active level)   <br>
         * 0: not Loop-B mode (Line Lb is  in  high inactive level)<br>
         */
        private byte bit3 = 0;

        /**
         * 1: Loop-A mode    (Line La is  in  low active level)   <br>
         * 0: not Loop-A mode(Line La is  in  high inactive level)<br>
         */
       private byte bit4 = 0;

        /**
         * 1: Dipole mode    (Line Dp is  in  low active level)   <br>
         * 0: not Dipole mode(Line Dp is  in (high inactive level)<br>
         */
        private byte bit5 = 1;

        /**
         * 1: Line Aux  is  in  low level. Reserved <br>
         * 0: Line Aux  is  in high level. Reserved <br>
         */
        private byte bit6 = 0;

        /**
         * must be always 0, reserved
         */
        private byte bit7 = 0;
        
        
        /**
         * 0 - X direction <br>
         * 1 - Y direction <br>
         * @param bit0 
         */
        public void setBit0(byte value) 
        {
            // Set the desired direction
            this.bit0 = value;
            
            if(this.bit5==TRUE)
                return; // Dipole is selected, we should not bother anymore
            
            //else (Loop is selected) - we need to switch the appropriate loop
            this.setLoop();           
        }

        /**
         * 0 - Additive      <br>
         * 1 - Directive <br>
         * @param bit1 
         */
        public void setBit1(byte value) {
            this.bit1 = value;
        }

        /**
         * 0 - Forward  (+) <br>
         * 1 - Backward (-) <br>
         * @param bit2 
         */
        public void setBit2(byte value) {
            this.bit2 = value;
        }

        /**
         * When selecting Loop antenna it matters which direction we have
         * currently selected: X or Y.<br> 
         * If X is selected A-loop must be used, thus bit4 is set
         * If Y direction is selected B-loop must be used, thus bit3is set
         */
        public void setLoop() 
        {
            // X direction is selected: A-loop will be used
            if(bit0 == FALSE)
            {             
                this.bit3 = FALSE;
                this.bit4 = TRUE;
                this.bit5 = FALSE;
            }
            // Y direction is selected: B-loop will be used
            else
            {
                this.bit3 = TRUE;
                this.bit4 = FALSE;
                this.bit5 = FALSE;
                
            }
        }

        /* Sets bit5 to true; Bits3 and 4 are set to false.
        *
        */
        public void setDipole() 
        {
            this.bit3 = FALSE; // Set the B-loop bit to false 
            this.bit4 = FALSE; // Set the A-loop bit to false
            this.bit5 = TRUE;  // Set the Dipole bit;
        }
        
        
        /**
         * Packs all the states into a byte. This byte is to be sent towards the
         * DLine Controller device thru the Serial Interface.
         * @return 
         */
        public byte getState()
        {
            byte command;
            
            command = (byte) (bit0      |
                              bit1 << 1 |
                              bit2 << 2 |
                              bit3 << 3 |
                              bit4 << 4 |
                              bit5 << 5 |
                              bit6 << 6 |
                              bit7 << 7   ); 
            
            return command;          
        }

        public byte getBit0() {
            return bit0;
        }
        
        
    }
    
}
