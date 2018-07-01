
package servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.net.MalformedURLException;
import java.rmi.Naming;

import interfaces.InterfazRemota;
import remoto.Sistema;

public class Servidor {

	public Servidor() throws RemoteException {
		// TODO Auto-generated constructor stub
		inicializar();
	}

	private void inicializar() throws RemoteException {
		InterfazRemota or = new Sistema();
		try{
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//127.0.0.1/sistema", or);
			System.out.println("Servidor corriendo - Objeto Remoto publicado: sistema");
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

