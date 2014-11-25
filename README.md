DLineController
===============

Software for controlling LZ1AQ's Variable Delay Line Kit for Wideband Active Antenna Phased Arrays
For more info see http://www.active-antenna.eu/

The program is written in Java and supports the following operating systems:
win32, win64, linux_x86, linux_x86_64, linux_ia64, mac_os_x


Installation:
--------------

1) Make sure you have installed Java Runtime Environment (JRE) version 1.7 or later.
   
2) Download the appropriate .jar file from the /distribution directory.
   For example: if you are running Windows with 32bit version of Java please
   download the file which contains "win32" in its name (i.e. dlinecontroller_v1.0-win32.jar)

3) To start the program double-click on the downloaded file.
   a) If double click doesn't work use the command line to start it by writing:
      java -jar nameofthefile.jar

4) If the program doesn't start because of the following exception:
   "java.lang.UnsatisfiedLinkError: no rxtxSerial in java.library.path thrown while loading gnu.io.RXTXCommDriver"
   
   You will need to do the following:
   a) The .jar file that you have downloaded is actually an archive. Open it with an appropriate 
      file archiver program (e.g. 7zip)
   b) Extract the file from the directory named /binlib to the same place where the .jar files is located.
      If you are using Windows OS, the file that you need to extract is rxtxSerial.dll
   c) Now start the .jar file by typing:
      java -Djava.path.library=./  -jar nameofthefile.jar

      Note: What you do here with this "-Djava.path.library=./" is to tell Java to look for the 
            rxtxSerial.dll file in the local directory.