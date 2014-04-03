
public interface InterfaceRMI extends java.rmi.Remote
{
	public Answer nuevo(String designation, int maximum) throws java.rmi.RemoteException;
	public Answer quita(short code) throws java.rmi.RemoteException;
	public Answer inscribe(String name, String alias) throws java.rmi.RemoteException;
	public Answer plantilla() throws java.rmi.RemoteException;
	public Answer repertorio(byte minimum) throws java.rmi.RemoteException;
	public Answer juega(String alias, short code) throws java.rmi.RemoteException;
	public Answer termina(String alias, short code) throws java.rmi.RemoteException;
	public Answer lista(short code) throws java.rmi.RemoteException;
}
