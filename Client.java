import java.io.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class Client {

	public static void main(String args[]) {
		String mensajeEnviado;
		String mensajeRecibido;
		DataInputStream dataIn = new DataInputStream(System.in);
		BufferedReader in = new BufferedReader(new InputStreamReader(dataIn));
		// Crea e instala el gestor de seguridad
		System.setSecurityManager(new RMISecurityManager());

		try {

			//Ojo, cambiar ese 4000 por un número de puerto propio
			EcoRMI miEco = (EcoRMI)Naming.lookup("rmi://" + args[0] + ":" +  + "/" + "mi-EcoRMI");

			// hace un bucle hasta el fin de la entrada
			System.out.print("Eco> ");
			while ((mensajeEnviado = in.readLine()) != null) {
				mensajeRecibido = miEco.eco(mensajeEnviado);
				System.out.println(mensajeRecibido);
				System.out.print("Eco> ");
			}
		} catch(Exception e) {
			System.err.println("Excepción de Sistema: " + e);
		}
		System.exit(0);
	}
}
