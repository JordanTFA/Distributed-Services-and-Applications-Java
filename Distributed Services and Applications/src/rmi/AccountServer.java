package rmi;

import java.rmi.Naming;

public class AccountServer {

	public static void main(String[] args) {

		try{
			AccountImp1 acc = new AccountImp1();
			String url = "rmi://localhost:1099/Account";
			Naming.rebind(url, acc);
			System.out.println("Account ready");
		} catch(Exception e){
			System.out.println("AccountServer: exception:");
			e.printStackTrace();
		}

	}

}
