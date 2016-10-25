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
    private AntennaModes                antennaMode;     // Subtractive or Additive
    private AntennaTypes                antennaType;     // ant1(Dipole) or ant2(Loop)
   
    private final byte TRUE  = 1;
    private final byte FALSE = 0;
    
    
    DLineApplicationState()
    {
        antennaDirection  = AntennaDirections.plusY;
        antennaType       = AntennaTypes.antenna_2;
        antennaMode       = AntennaModes.additive;
        hardwareState     = new DLineHardwareState();
    }
    
    
    /** Set the desired direction of the antenna.
     * 
     * @param direction Possible antenna directions plusY, minusY, plusX. minusX
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
                hardwareState.setBit2(FALSE); // (+)
                break;
                
            case minusY:
                hardwareState.setBit0(TRUE); // Y
                hardwareState.setBit2(TRUE); // (-)
                break;
                
            case plusX:
                hardwareState.setBit0(FALSE); // X
                hardwareState.setBit2(FALSE); // (+)
                break;
                
            case minusX:
                hardwareState.setBit0(FALSE); // X
                hardwareState.setBit2(TRUE); // (-)
                break;    
        }
    }
    
    /**
     * Sets the mode - additive(add) or subtractive
     * @param mode 
     */
    public void setAntennaMode(AntennaModes mode)
    {
      this.antennaMode = mode;
      
      switch (mode)
      {
        case additive:
          hardwareState.setBit1(FALSE); 
          break;
        case subtractive:
          hardwareState.setBit1(TRUE);
          break;
      }
    }
    

    /** Sets the current antenna mode
     * 
     * @param type Possible types are dipole or loop
     */
    public void setAntennaType(AntennaTypes type)
    {
        antennaType = type;
           
       // Mange the DLine Controller states:
       // ---------------------------------  
       switch(type)
       {
           case antenna_1:
               hardwareState.setDipole();
               break;
           case antenna_2:
               hardwareState.setLoop();
               break;
       }
    }
    
    
    public AntennaModes getAntennaMode()
    {
      return antennaMode;
    }
    
    
    public AntennaTypes getAntennaType()
    {
        return antennaType;
    }
    
    
    public AntennaDirections getAntennaDirection()
    {
        return antennaDirection;
    }
    
    
    /**
     * Gets the state in form of a byte. This byte should be send over the 
     * serial interface to the DLine Controller device. 
     *  
     * @return bit0- X or Y direction; bit1- mode; 
     * bit2-  (+) or (-); bit3- LoopB; bit4- LoopA; bit5- Dipole; 
     * bits 6 and 7 are not used (always 0)
     */
    public byte getState()
    {
        return hardwareState.getState();        
    }
    
    
    /**
     * @param bEnabled It true sets the device into single element mode
     */
    public void setSingleElementMode(boolean bEnabled)
    {
      if(bEnabled)
      {
        hardwareState.setBit6(TRUE);
      }
      else
      {
        hardwareState.setBit6(FALSE);
      }
 
    }
   
    
    /**
     * 
     */
    public static enum AntennaDirections
    {
        plusY,  
        minusY,
        plusX,
        minusX
    }
    
    
    public static enum AntennaModes
    {
      additive,
      subtractive
    }
    
    
    /**
     * Two antenna types are available
     */
    public static enum AntennaTypes
    {
        antenna_1,  // by default this should be Dipole
        antenna_2   // by default this should be Loop
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
         * 1: Subtractive mode (relay K2 is ON)      <br>
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

        
        public byte getBit0() 
        {
            return bit0;
        }
           
        
        /**
         * 0 - Additive  <br>
         * 1 - Subtractive <br>
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
         * Enables/disables single element mode
         * @param value 
         */
        public void setBit6(byte value) 
        {
          this.bit6 = value;
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

     
        
        
    }
    
}
