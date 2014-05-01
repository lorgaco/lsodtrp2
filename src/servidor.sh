#! /bin/csh -f
#
# No recibe argumentos
#
setenv CLASSPATH ../bin
java -Djava.security.policy=policy.all Server -k 0
