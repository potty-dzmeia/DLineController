DLineController
===============

Software for controlling LZ1AQ's Variable Delay Line Kit for Wideband Active Antenna Phased Arrays
For more info see http://www.active-antenna.eu/

The program is written in Java and supports the following operating systems:
win32, win64, linux_x86, linux_x86_64, linux_ia64, mac_os_x


Installation:
--------------

1) Make sure you have installed Java Runtime Environment (JRE) version 1.7 or later.

2) From the button "branch:master" select "tags" and select the newest available version (currently 1.2). 

3) Download the appropriate .jar file from the `/distribution` directory.
* For example: if you are running Windows with 32bit version of Java please download the file which contains "win32" in its name (i.e. dlinecontroller_v1.2-win32.jar)
* For demonstrative purposes I will designate the downloaded file name by `nameofthedownloadedfile.jar`

4) To start the program double-click on the downloaded file.
  * If double click doesn't work use the command line to start it by writing: `java -jar nameofthedownloadedfile.jar`

**In case of problems**: 

1) If the program doesn't start because of the following exception:
   `java.lang.UnsatisfiedLinkError: no rxtxSerial in java.library.path thrown while loading gnu.io.RXTXCommDriver`

You will need to do the following:
  * The `nameofthedownloadedfile.jar` file that you have downloaded is actually an archive. 
 Open it with an appropriate file archiver program (e.g. 7zip).
  * Extract the file from the directory named `/binlib` to the same place where the `nameofthedownloadedfile.jar` file is located.
E.g. if you are using Windows OS, the file that you need to extract is called rxtxSerial.dll
  * Now start the program by typing: `java -Djava.library.path=./  -jar nameofthedownloadedfile.jar`

Note: What you do here with this `-Djava.library.path=./` is to tell Java to look for the rxtxSerial.dll file in the local directory.

2) [Linux] If you have started the program but you don't see the comm port in the "Settings" dialog, 
probably you don't have the permission to access the commport file (e.g. /dev/ttyS0)
* You need to add your username to the `dialout` group by writing: `sudo gpasswd --add yourusername dialout`
* Then either login and logout or write `exec su – $USER`


Development - IDE Installation:
-----------------

Clone or download the project.

1) Netbeans [recommended]
* Go to `File->Open Project` and select the directory where you have downloaded the project
* From the drop-down box in the Run tab select your current environment (e.g. linux_x86_64)
* Right click on the project dlinecontroller and select `Properties->Run->VM Options` and write down `-Djava.library.path=./target/native_libs`
* Now you are ready to run and debug the project

2) Eclipse
* From command line in the root directory of the project write `mvn -P linux_x86_64 clean install` 
  * The `-P` option specifies the environment that we are working on, so pickup the appropriate value for you.
* From command line in root directory of the project write `mvn eclipse:eclipse`
  * This creates all the needed files so that Eclipse recognizes the folder as a project
* From Eclipse select `File->Import->General->Existing Project into Workspace` and point to the place where you have downloaded the project
* Right click on the project and select `Run As->Run Configurations...` Double-click on `Java Application`
  * In the `Main` tab specify the `Main Class:` to be  `DLineApplication` 
  * In the `Arguments` tab specify the VM Arguments: `-Djava.library.path=./target/native_libs`
  * Press on `Apply` and then `Run`
* Note that every time you make changes in the source code you will need to: 
  * Write again in the command line `mvn -P linux_x86_64 clean install` in order to compile your changes 
  * Then from Eclipse right-click on the project and select `Run As(or Debug As)->Java Application`
