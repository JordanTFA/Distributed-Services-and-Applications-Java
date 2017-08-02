package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AccountImp1 extends UnicastRemoteObject implements Account{
	
	private double sum;
	
	public AccountImp1() throws RemoteException{
		sum = 0.0;
	}
	
	public double balance() throws RemoteException{
		return sum;
	}
	
	public void deposit(double amount) throws RemoteException{
		sum += amount;
	}

}
