package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Account extends Remote {
	
	double balance() throws RemoteException;
	void deposit(double amount) throws RemoteException;

}
