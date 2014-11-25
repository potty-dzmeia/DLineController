#!/bin/bash    

#Available build profiles:
# win32
# win64
# linux_x86
# linux_x86_64
# linux_ia64
# mac_os_x

declare -a profiles=("win32" "win64" "linux_x86" "linux_x86_64" "linux_ia64" "mac_os_x")

# make the dir where the jar files will be copied
mkdir distribution

for i in "${profiles[@]}"
do
   # 
   echo "mvn -P "$i" clean install"
   echo "-------------------------------------------------"
   # build each of the profiles
   mvn -P "$i" clean install
   # copy the jar file to /distribution 
   cp ./target/dlinecontroller_v*.jar ./distribution/

done

mvn clean

