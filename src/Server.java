import java.rmi.*;

public class Server {

	public static void main(String args[]) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			ServantRMI ModuleRMI = new ServantRMI("//:" + Data.PORT + "/serverRMI");
			System.out.println("Servidor de EcoRMI listo.");
		} catch (Exception e) {
			System.out.println("Excepción: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
