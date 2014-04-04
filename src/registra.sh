#! /bin/csh -f
#
# No recibe argumentos
#
setenv CLASSPATH ../bin
#  Ojo, cambiar ese 4000 por un número de puerto propio
rmiregistry 4005 -J-Djava.security.policy=policy.all &
