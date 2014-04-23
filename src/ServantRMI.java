import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ServantRMI extends UnicastRemoteObject implements InterfaceRMI{
	Methods Method;

	public ServantRMI(String name, String Key) throws RemoteException {
		super();
		try {
			Naming.rebind(name, this);
		} catch (Exception e) {
			System.out.println("Excepción: " + e.getMessage());
			e.printStackTrace();
		}
		Method = new Methods();
	}
	public Answer nuevo(String designation, int maximum) throws RemoteException {
		return Method.nuevo(designation, maximum);
	}
	public Answer quita(short code) throws RemoteException {
		return Method.quita(code);
	}
	public Answer inscribe(String name, String alias) throws RemoteException {
		return Method.inscribe(name, alias);
	}
	public Answer plantilla() throws RemoteException {
		return Method.plantilla();
	}
	public Answer repertorio(byte minimum) throws RemoteException {
		return Method.repertorio(minimum);
	}
	public Answer juega(String alias, short code) throws RemoteException {
		return Method.juega(alias, code);
	}
	public Answer termina(String alias, short code) throws RemoteException {
		return Method.termina(alias, code);
	}
	public Answer lista(short code) throws RemoteException {
		return Method.lista(code);
	}
}
