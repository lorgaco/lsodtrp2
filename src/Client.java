import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.*;

public class Client {

    public static void main(String args[]) {
        InterfaceRMI ModuleRMI = null;
        String Key = null;

        if(args.length<1) {
            System.err.println("Not enough arguments");
            return;
        }
        else{
            try {
                if(args.length>2) {
                    if(args[1].equals("-k") || args[1].equals("-K")) {
                        Key=args[2];
                        System.out.println("Admin key: " + Key);
                    }
                }
                ModuleRMI = (InterfaceRMI)Naming.lookup("rmi://" + args[0] + ":" + Data.PORT + "/serverRMI");
            } catch(Exception e) {
                System.err.println("Excepción de Sistema: " + e);
            }
        }

        BufferedReader brComand = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("escriba");
            try {
                String strComand[] = brComand.readLine().split(" ");
                if(strComand.length<1){
                    System.err.println("Not enough arguments");
                    break;
                }
                else{
                    String method = Data.PromptToMethod(strComand[0].toString());
                    if(method.equals("NUEVO")){
                        if(strComand.length<3) System.err.println("Not enough arguments");
                        else{
                            String designation = strComand[1].toString();
                            for(int i = 2; i < strComand.length-1; i++) {
                                designation = designation + " " + strComand[i].toString();
                            }
                            int maximum = Integer.parseInt(strComand[strComand.length-1]);
                            Answer Response = ModuleRMI.nuevo(designation, maximum, Key);
                            int iError = Response.getError();
                            int iServerError = Response.getServer_error();
                            String sResponse = Response.getAnswer();
                            if(iError!=Data.OK  || iServerError!=Data.OK) {
                                System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                            }
                            else {
                                System.out.println("Juego creado con id " + sResponse);
                            }
                        }
                    }
                    else if(method.equals("QUITA")){
                        if(strComand.length<2) System.err.println("Not enough arguments");
                        else{
                            short code = Short.parseShort(strComand[1].toString());
                            Answer Response = ModuleRMI.quita(code, Key);
                            int iError = Response.getError();
                            int iServerError = Response.getServer_error();
                            if(iError!=Data.OK  || iServerError!=Data.OK) {
                                System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                            }
                            else {
                                System.out.println("Juego eliminado");
                            }
                        }
                    }
                    else if(method.equals("INSCRIBE")){
                        if(strComand.length<3) System.err.println("Not enough arguments");
                        else{
                            String name = strComand[1].toString();
                            String alias = strComand[2].toString();
                            Answer Response = ModuleRMI.inscribe(name, alias);
                            int iError = Response.getError();
                            int iServerError = Response.getServer_error();
                            if(iError!=Data.OK  || iServerError!=Data.OK) {
                                System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                            }
                            else {
                                System.out.println("Inscrito");
                            }
                        }
                    }
                    else if(method.equals("PLANTILLA")){
                        Answer Response = ModuleRMI.plantilla(Key);
                        int iError = Response.getError();
                        int iServerError = Response.getServer_error();
                        String sResponse = Response.getAnswer();
                        if(iError!=Data.OK  || iServerError!=Data.OK) {
                            System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                            System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                        }
                        else {
                            String[] aResponse = sResponse.split("(, )|\\]|\\[");
                            for(int ii=0; ii<aResponse.length; ii++){
                                System.out.println(aResponse[ii]);
                            }
                        }
                    }
                    else if(method.equals("REPERTORIO")){
                        if(strComand.length<2) System.err.println("Not enough arguments");
                        else{
                            byte minimum = Byte.parseByte(strComand[1].toString());
                            Answer Response = ModuleRMI.repertorio(minimum);
                            int iError = Response.getError();
                            int iServerError = Response.getServer_error();
                            String sResponse = Response.getAnswer();
                            if(iError!=Data.OK  || iServerError!=Data.OK) {
                                System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                            }
                            else {
                                String[] aResponse = sResponse.split("(, )|\\]|\\[");
                                for(int ii=0; ii<aResponse.length; ii++){
                                    System.out.println(aResponse[ii]);
                                }
                            }
                        }
                    }
                    else if(method.equals("JUEGA")){
                        if(strComand.length<3) System.err.println("Not enough arguments");
                        else{
                            String alias = strComand[1].toString();
                            short code = Short.parseShort(strComand[2].toString());
                            Answer Response = ModuleRMI.juega(alias, code);
                            int iError = Response.getError();
                            int iServerError = Response.getServer_error();
                            if(iError!=Data.OK  || iServerError!=Data.OK) {
                                System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                            }
                            else {
                                System.out.println("jugando");
                            }
                        }
                    }
                    else if(method.equals("TERMINA")){
                        if(strComand.length<3) System.err.println("Not enough arguments");
                        else{
                            String alias = strComand[1].toString();
                            short code = Short.parseShort(strComand[2].toString());
                            Answer Response = ModuleRMI.termina(alias, code);
                            int iError = Response.getError();
                            int iServerError = Response.getServer_error();
                            if(iError!=Data.OK  || iServerError!=Data.OK) {
                                System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                            }
                            else {
                                System.out.println("Desconectado");
                            }
                        }
                    }
                    else if(method.equals("LISTA")){
                        if(strComand.length<2) System.err.println("Not enough arguments");
                        else{
                            short code = Short.parseShort(strComand[1].toString());
                            Answer Response = ModuleRMI.lista(code);
                            int iError = Response.getError();
                            int iServerError = Response.getServer_error();
                            String sResponse = Response.getAnswer();
                            if(iError!=Data.OK  || iServerError!=Data.OK) {
                                System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                System.err.println("METHOD ERROR: " + Data.ErrorToString(iError));
                            }
                            else {
                                String[] aResponse = sResponse.split("(, )|\\]|\\[");
                                for(int ii=0; ii<aResponse.length; ii++){
                                    System.out.println(aResponse[ii]);
                                }
                            }
                        }
                    }
                    else if(method.equals("FINAL")){
                        System.out.println("FINAL");
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println("ERROR: " + e.getMessage());
                break;
            }
        }
    }
}
