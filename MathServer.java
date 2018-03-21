import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.lang.Math;

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
      if(a==0){
        System.out.println("The value of a is zero");
        return -1;
      }
      else if(b==0){
        System.out.println("The value of b is zero");
        return -1;
      }
System.out.println("Dividing " + a + " and " + b + " in the Server");

        return a/b;
    }

    public double square_root(double a) throws RemoteException {
      System.out.println("Square rooting " + a + " in the Server");
      return Math.sqrt(a);
    }

    public double square(double a) throws RemoteException {
      System .out.println("Squaring " + a + " in the Server");
      return (a*a);
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
