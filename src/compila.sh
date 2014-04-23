#! /bin/csh -f
#
# Para hacer EcoRMI. No recibe argumentos
#
if ( ! -d ../bin ) mkdir ../bin
setenv CLASSPATH ../bin
echo javac  -d ../bin Data.java
javac  -d ../bin Data.java
echo javac -d ../bin Answer.java
javac -d ../bin Answer.java
echo javac -d ../bin Methods.java
javac -d ../bin Methods.java
echo javac -d ../bin InterfaceRMI.java
javac -d ../bin InterfaceRMI.java
echo javac  -d ../bin ServantRMI.java
javac  -d ../bin ServantRMI.java
echo javac -d ../bin Client.java
javac -d ../bin Client.java
echo javac -d ../bin Server.java
javac -d ../bin Server.java
echo rmic -d ../bin ServantRMI
rmic -d ../bin ServantRMI
cp -f policy.all ../bin

