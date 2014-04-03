import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ServantRMI extends UnicastRemoteObject implements InterfaceRMI{
	Methods Methods;

	public ServantRMI(String name) throws RemoteException {
		super();
		try {
			Naming.rebind(name, this);
		} catch (Exception e) {
			System.out.println("Excepción: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public Answer nuevo(String designation, int maximum) throws RemoteException {
		return Methods.nuevo(designation, maximum);
	}
	public Answer quita(short code) throws RemoteException {
		return Methods.quita(code);
	}
	public Answer inscribe(String name, String alias) throws RemoteException {
		return Methods.inscribe(name, alias);
	}
	public Answer plantilla() throws RemoteException {
		return Methods.plantilla();
	}
	public Answer repertorio(byte minimum) throws RemoteException {
		return Methods.repertorio(minimum);
	}
	public Answer juega(String alias, short code) throws RemoteException {
		return Methods.juega(alias, code);
	}
	public Answer termina(String alias, short code) throws RemoteException {
		return Methods.termina(alias, code);
	}
	public Answer lista(short code) throws RemoteException {
		return Methods.lista(code);
	}
}
