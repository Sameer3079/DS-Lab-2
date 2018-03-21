import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
 
public class MathServer extends UnicastRemoteObject implements MathService{
 
    public MathServer() throws RemoteException{
        super();
    }
 
 
    public int add(int a, int b) throws RemoteException {
        System.out.println("Adding " + a + " and " + b + " in the Server");
        return a+b;
    }
 
 
    public int subtract(int a, int b) throws RemoteException {
        System.out.println("Substracting " + a + " and " + b + " in the Server");
	   return a-b;
    }
 
 
    public int multiply(int a, int b) throws RemoteException {
System.out.println("Multiplying " + a + " and " + b + " in the Server");
        return a*b;
    }
 
 
    public int divide(int a, int b) throws RemoteException {
System.out.println("Dividing " + a + " and " + b + " in the Server");

        return a/b; //check for division with zero here!
    }
 
    public static void main(String[] args){
        if (System.getSecurityManager() == null)
            System.setSecurityManager ( new RMISecurityManager() );
        try{
            LocateRegistry.createRegistry(2099); 
            MathServer svr = new MathServer();
            Naming.bind ("MathService", svr);
            System.out.println ("Service started....");
        }
        catch(RemoteException re){
            System.err.println(re.getMessage());
        }
        catch(AlreadyBoundException abe){
            System.err.println(abe.getMessage());
        }
        catch(MalformedURLException mue){
            System.err.println(mue.getMessage());
        }
    }
}