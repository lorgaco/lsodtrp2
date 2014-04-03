import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.*;

public class Client {

	public static void main(String args[]) {
		InterfaceRMI ModuleRMI = null;
		String Key;
		
		if(args.length<1) {
			System.err.println("Not enough arguments");
			return;
		}
		else{
			try {
				ModuleRMI = (InterfaceRMI)Naming.lookup("rmi://" + args[0] + ":" + Data.PORT + "/serverRMI");
			} catch(Exception e) {
				System.err.println("Excepción de Sistema: " + e);
			}
		}
		
		//TODO store key
		Key="clave";
		
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
					String method = strComand[0].toString().toUpperCase();
					if(method.equals("NUEVO")){
						if(strComand.length<3) System.err.println("Not enough arguments");
						else{
							String designation = strComand[1].toString();
							for(int i = 2; i < strComand.length-1; i++) {
								designation = designation + " " + strComand[i].toString();
							}
							int maximum = Integer.parseInt(strComand[strComand.length-1]);
							Answer result = ModuleRMI.nuevo(designation, maximum);
							System.out.println(result);
						}
					}
					else if(method.equals("QUITA")){
						if(strComand.length<2) System.err.println("Not enough arguments");
						else{
							short code = Short.parseShort(strComand[1].toString());
							Answer result = ModuleRMI.quita(code);
							System.out.println(result);
						}
					}
					else if(method.equals("INSCRIBE")){
						if(strComand.length<3) System.err.println("Not enough arguments");
						else{
							String name = strComand[1].toString();
							String alias = strComand[2].toString();
							Answer result = ModuleRMI.inscribe(name, alias);
							System.out.println(result);
						}
					}
					else if(method.equals("PLANTILLA")){
						Answer result = ModuleRMI.plantilla();
						System.out.println(result);
					}
					else if(method.equals("REPERTORIO")){
						if(strComand.length<2) System.err.println("Not enough arguments");
						else{
							byte minimum = Byte.parseByte(strComand[1].toString());
							Answer result = ModuleRMI.repertorio(minimum);
							System.out.println(result);
						}
					}
					else if(method.equals("JUEGA")){
						if(strComand.length<3) System.err.println("Not enough arguments");
						else{
							String alias = strComand[1].toString();
							short code = Short.parseShort(strComand[2].toString());
							Answer result = ModuleRMI.juega(alias, code);
							System.out.println(result);
						}
					}
					else if(method.equals("TERMINA")){
						if(strComand.length<3) System.err.println("Not enough arguments");
						else{
							String alias = strComand[1].toString();
							short code = Short.parseShort(strComand[2].toString());
							Answer result = ModuleRMI.termina(alias, code);
							System.out.println(result);
						}
					}
					else if(method.equals("LISTA")){
						if(strComand.length<2) System.err.println("Not enough arguments");
						else{
							short code = Short.parseShort(strComand[1].toString());
							Answer result = ModuleRMI.lista(code);
							System.out.println(result);
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
