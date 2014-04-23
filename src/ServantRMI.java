import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ServantRMI extends UnicastRemoteObject implements InterfaceRMI{
	Methods Method;
    String key_server;

	public ServantRMI(String name, String Key) throws RemoteException {
		super();
		try {
			Naming.rebind(name, this);
		} catch (Exception e) {
			System.out.println("Excepción: " + e.getMessage());
			e.printStackTrace();
		}
		Method = new Methods();
        key_server = Key;
	}
	public Answer nuevo(String designation, int maximum, String key_client) throws RemoteException {
        Answer answer = new Answer();
        if(key_client.equals(key_server))
            answer = Method.nuevo(designation, maximum);
        else
            answer.setError(Data.AUTENTICATION_FAILED);
		return answer;
	}
	public Answer quita(short code, String key_client) throws RemoteException {
        Answer answer = new Answer();
        if(key_client.equals(key_server))
            answer = Method.quita(code);
        else
            answer.setError(Data.AUTENTICATION_FAILED);
		return answer;
	}
	public Answer inscribe(String name, String alias) throws RemoteException {
		return Method.inscribe(name, alias);
	}
	public Answer plantilla(String key_client) throws RemoteException {
        Answer answer = new Answer();
        if(key_client.equals(key_server))
            answer = Method.plantilla();
        else
            answer.setError(Data.AUTENTICATION_FAILED);
		return answer;
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
