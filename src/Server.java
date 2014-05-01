import java.rmi.*;

public class Server {
    static String Key;

	public static void main(String args[]) {
		System.setSecurityManager(new RMISecurityManager());
        if(args.length<1) {
            System.err.println("Not enough arguments");
            return;
        }
        else{
		    try {
                if(args.length>2) {
                    if(args[0].equals("-k") || args[0].equals("-K")) {
                        Key = args[1];
                        System.out.println("Admin key: " + Key);
                    }
                }
    			ServantRMI ModuleRMI = new ServantRMI("//127.0.0.1:" + Data.PORT + "/serverRMI", Key);
    			System.out.println("Servidor de EcoRMI listo.");
    		} catch (Exception e) {
    			System.out.println("Excepción: " + e.getMessage());
    			e.printStackTrace();
    		}
        }
	}
}
