#! /bin/csh -f
#
# Recibe un argumento: el nombre del ordenador en el que se ha arrancado
# el Servidor de Eco
if ($#argv < 1) then
cat << end-of-help-message
   uso: cliente.sh máquina
	  máquina    nombre de la máquina en la que corre el Servidor de Eco
end-of-help-message
exit(-1)
endif
setenv CLASSPATH ../bin
java -Djava.security.policy=policy.all Client $1 -k 1 $2 $3
