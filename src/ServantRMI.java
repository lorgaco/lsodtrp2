import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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
	public AnswerNuevo nuevo(String designation, int maximum, String key_client) throws RemoteException {
        AnswerNuevo out = new AnswerNuevo();
        if(key_client.equals(key_server)){
            out.server_error = Data.OK;
            out.answer = Method.nuevo(designation, maximum);
            return out;
        }
        else {
            out.server_error = Data.AUTENTICATION_FAILED;
            out.answer = new sNuevo();
            return out;
        }
	}
	public AnswerInt quita(short code, String key_client) throws RemoteException {
        AnswerInt out = new AnswerInt();
        if(key_client.equals(key_server)){
            out.server_error = Data.OK;
            out.answer = Method.quita(code);
            return out;
        }
        else {
            out.server_error = Data.AUTENTICATION_FAILED;
            out.answer = 0;
            return out;
        }
	}
	public AnswerInt inscribe(String name, String alias) throws RemoteException {
        AnswerInt out = new AnswerInt();
        out.server_error = Data.OK;
        out.answer = Method.inscribe(name, alias);
        return out;
	}
	public AnswerPlantilla plantilla(String key_client) throws RemoteException {
        AnswerPlantilla out = new AnswerPlantilla();
        if(key_client.equals(key_server)){
            out.server_error = Data.OK;
            out.answer = Method.plantilla();
            return out;
        }
        else {
            out.server_error = Data.AUTENTICATION_FAILED;
            out.answer = new ArrayList<Jugador>();
            return out;
        }
	}
	public AnswerRepertorio repertorio(byte minimum) throws RemoteException {
        AnswerRepertorio out = new AnswerRepertorio();
        out.server_error = Data.OK;
        out.answer = Method.repertorio(minimum);
        return out;
	}
	public AnswerInt juega(String alias, short code) throws RemoteException {
        AnswerInt out = new AnswerInt();
        out.server_error = Data.OK;
        out.answer = Method.juega(alias, code);
        return out;
	}
	public AnswerInt termina(String alias, short code) throws RemoteException {
        AnswerInt out = new AnswerInt();
        out.server_error = Data.OK;
        out.answer = Method.termina(alias, code);
        return out;
	}
	public AnswerLista lista(short code) throws RemoteException {
        AnswerLista out = new AnswerLista();
        out.server_error = Data.OK;
        out.answer = Method.lista(code);
        return out;
	}
}
