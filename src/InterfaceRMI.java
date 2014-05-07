
public interface InterfaceRMI extends java.rmi.Remote
{
	public AnswerNuevo nuevo(String designation, int maximum, String key_client) throws java.rmi.RemoteException;
	public AnswerInt quita(short code, String key_client) throws java.rmi.RemoteException;
	public AnswerInt inscribe(String name, String alias) throws java.rmi.RemoteException;
	public AnswerPlantilla plantilla(String key_client) throws java.rmi.RemoteException;
	public AnswerRepertorio repertorio(byte minimum) throws java.rmi.RemoteException;
	public AnswerInt juega(String alias, short code) throws java.rmi.RemoteException;
	public AnswerInt termina(String alias, short code) throws java.rmi.RemoteException;
	public AnswerLista lista(short code) throws java.rmi.RemoteException;
}
