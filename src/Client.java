import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.*;
import java.util.List;
import java.util.ListIterator;

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
                        System.out.println("NUEVO");
                        if(Key == null) System.out.println("No Key provided. This operation can only be done by Admin");
                        else if(strComand.length<3) System.err.println("Not enough arguments");
                        else{
                            String designation = strComand[1].toString();
                            for(int i = 2; i < strComand.length-1; i++) {
                                designation = designation + " " + strComand[i].toString();
                            }
                            if(designation.length() > 30) System.err.println("FORMAT ERROR > 30 characters");
                            else {
                                try {
                                    int maximum = Integer.parseInt(strComand[strComand.length - 1]);
                                    AnswerNuevo Response = ModuleRMI.nuevo(designation, maximum, Key);
                                    int iServerError = Response.server_error;
                                    sNuevo resultado = Response.answer;
                                    if (resultado.error != Data.OK || iServerError != Data.OK) {
                                        System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                        System.err.println("METHOD ERROR: " + Data.ErrorToString(resultado.error));
                                    } else {
                                        System.out.println("Juego creado con id " + resultado.code);
                                    }
                                } catch (Exception e) {
                                    System.err.println("FORMAT ERROR: " + e.getMessage());
                                }
                            }
                        }
                    }
                    else if(method.equals("QUITA")){
                        System.out.println("QUITA");
                        if(Key == null) System.out.println("No Key provided. This operation can only be done by Admin");
                        else if(strComand.length<2) System.err.println("Not enough arguments");
                        else{
                            try {
                                short code = Short.parseShort(strComand[1].toString());
                                AnswerInt Response = ModuleRMI.quita(code, Key);
                                int iServerError = Response.server_error;
                                int resultado = Response.answer;
                                if(resultado!=Data.OK  || iServerError!=Data.OK) {
                                    System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                    System.err.println("METHOD ERROR: " + Data.ErrorToString(resultado));
                                }
                                else {
                                    System.out.println("Juego eliminado");
                                }
                            } catch (Exception e) {
                                System.err.println("FORMAT ERROR: " + e.getMessage());
                            }
                        }
                    }
                    else if(method.equals("INSCRIBE")){
                        System.out.println("INSCRIBE");
                        if(strComand.length<3) System.err.println("Not enough arguments");
                        else{
                            String name = strComand[1].toString();
                            for(int i = 2; i < strComand.length-1; i++) {
                                name = name + " " + strComand[i].toString();
                            }
                            if(name.length() > 48) System.err.println("FORMAT ERROR > 48 characters");
                            else {
                                String alias = strComand[strComand.length - 1].toString();
                                if(alias.length() > 8) System.err.println("FORMAT ERROR > 8 characters");
                                else {
                                    AnswerInt Response = ModuleRMI.inscribe(name, alias);
                                    int iServerError = Response.server_error;
                                    int resultado = Response.answer;
                                    if(resultado!=Data.OK  || iServerError!=Data.OK) {
                                        System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                        System.err.println("METHOD ERROR: " + Data.ErrorToString(resultado));
                                    }
                                    else {
                                        System.out.println("Inscrito");
                                    }
                                }
                            }
                        }
                    }
                    else if(method.equals("PLANTILLA")){
                        System.out.println("PLANTILLA");
                        if(Key == null) System.out.println("No Key provided. This operation can only be done by Admin");
                        else {
                            AnswerPlantilla Response = ModuleRMI.plantilla(Key);
                            int iServerError = Response.server_error;
                            List<Jugador> resultado = Response.answer;
                            if(iServerError!=Data.OK) {
                                System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                            }
                            else {
                                ListIterator<Jugador> it = resultado.listIterator();
                                for(int ii=0; ii<resultado.size(); ii++) {
                                    Jugador player = it.next();
                                    System.out.println(player.name + " (" + player.alias + ").");
                                }
                            }
                        }
                    }
                    else if(method.equals("REPERTORIO")){
                        System.out.println("REPERTORIO");
                        if(strComand.length<2) System.err.println("Not enough arguments");
                        else{
                            try {
                                byte minimum = Byte.parseByte(strComand[1].toString());
                                AnswerRepertorio Response = ModuleRMI.repertorio(minimum);
                                int iServerError = Response.server_error;
                                List<Juego> resultado = Response.answer;
                                if(iServerError!=Data.OK) {
                                    System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                }
                                else {
                                    ListIterator<Juego> it = resultado.listIterator();
                                    for(int ii=0; ii<resultado.size(); ii++) {
                                        Juego game = it.next();
                                        System.out.println(game.designation + " (" + game.code + "): Max=" + game.maximum + ".");
                                    }
                                }
                            } catch (Exception e) {
                                System.err.println("FORMAT ERROR: " + e.getMessage());
                            }
                        }
                    }
                    else if(method.equals("JUEGA")){
                        System.out.println("JUEGA");
                        if(strComand.length<3) System.err.println("Not enough arguments");
                        else{
                            String alias = strComand[1].toString();
                            try {
                                short code = Short.parseShort(strComand[2].toString());
                                AnswerInt Response = ModuleRMI.juega(alias, code);
                                int iServerError = Response.server_error;
                                int resultado = Response.answer;
                                if(resultado!=Data.OK  || iServerError!=Data.OK) {
                                    System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                    System.err.println("METHOD ERROR: " + Data.ErrorToString(resultado));
                                }
                                else {
                                    System.out.println("jugando");
                                }
                            } catch (Exception e) {
                                System.err.println("FORMAT ERROR: " + e.getMessage());
                            }
                        }
                    }
                    else if(method.equals("TERMINA")){
                        System.out.println("TERMINA");
                        if(strComand.length<3) System.err.println("Not enough arguments");
                        else{
                            String alias = strComand[1].toString();
                            try {
                                short code = Short.parseShort(strComand[2].toString());
                                AnswerInt Response = ModuleRMI.termina(alias, code);
                                int iServerError = Response.server_error;
                                int resultado = Response.answer;
                                if(resultado!=Data.OK  || iServerError!=Data.OK) {
                                    System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                    System.err.println("METHOD ERROR: " + Data.ErrorToString(resultado));
                                }
                                else {
                                    System.out.println("Desconectado");
                                }
                            } catch (Exception e) {
                                System.err.println("FORMAT ERROR: " + e.getMessage());
                            }
                        }
                    }
                    else if(method.equals("LISTA")){
                        System.out.println("LISTA");
                        if(strComand.length<2) System.err.println("Not enough arguments");
                        else{
                            try {
                                short code = Short.parseShort(strComand[1].toString());
                                AnswerLista Response = ModuleRMI.lista(code);
                                int iServerError = Response.server_error;
                                sLista resultado = Response.answer;
                                if(resultado.error!=Data.OK  || iServerError!=Data.OK) {
                                    System.err.println("SERVER ERROR: " + Data.ErrorToString(iServerError));
                                    System.err.println("METHOD ERROR: " + Data.ErrorToString(resultado.error));
                                }
                                else {
                                    ListIterator<Jugador> it = resultado.lista.listIterator();
                                    for(int ii=0; ii<resultado.lista.size(); ii++) {
                                        Jugador player = it.next();
                                        System.out.println(player.name + " (" + player.alias + ").");
                                    }
                                }
                            } catch (Exception e) {
                                System.err.println("FORMAT ERROR: " + e.getMessage());
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
