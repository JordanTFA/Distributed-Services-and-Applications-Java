package rmi;

import java.rmi.Naming;

public class AccountClient {

	public static void main(String[] args) {
		
		try{
			String host = System.getProperty("rmiregistery.host");
			String port = System.getProperty("rmiregistery.port");
			String url = "rmi://" + host + ":" + port + "/Account";
			Account acc = (Account) Naming.lookup(url);
			acc.deposit(new Double(args[0]).doubleValue());
		} catch( Exception e ){
			System.out.println("AccountClient: exception");
			e.printStackTrace();
		}

	}

}
