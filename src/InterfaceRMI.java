
public interface InterfaceRMI extends java.rmi.Remote
{
	public int nuevo(String designation, int maximum) throws java.rmi.RemoteException;
	public int quita(short code) throws java.rmi.RemoteException;
	public int inscribe(String name, String alias) throws java.rmi.RemoteException;
	public String plantilla() throws java.rmi.RemoteException;
	public int repertorio(byte minimum) throws java.rmi.RemoteException;
	public int juega(String alias, short code) throws java.rmi.RemoteException;
	public int termina(String alias, short code) throws java.rmi.RemoteException;
	public int lista(short code) throws java.rmi.RemoteException;
}
