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
	public int nuevo(String designation, int maximum) throws RemoteException {
		return Methods.nuevo(designation, maximum);
	}
	public int quita(short code) throws RemoteException {
		return Methods.quita(code);
	}
	public int inscribe(String name, String alias) throws RemoteException {
		return Methods.inscribe(name, alias);
	}
	public String plantilla() throws RemoteException {
		return Methods.plantilla();
	}
	public int repertorio(byte minimum) throws RemoteException {
		return Methods.repertorio(minimum);
	}
	public int juega(String alias, short code) throws RemoteException {
		return Methods.juega(alias, code);
	}
	public int termina(String alias, short code) throws RemoteException {
		return Methods.termina(alias, code);
	}
	public int lista(short code) throws RemoteException {
		return Methods.lista(code);
	}
}
