package rpc;

import java.net.InetAddress;

import org.acplt.oncrpc.OncRpcProtocols;

public class AccountClientRun {

	public static void main(String[] args){
		AccountClient client = null;
		
		try{
			client = new AccountClient(InetAddress.getByName(args[0]),
					OncRpcProtocols.ONCRPC_UDP);
			client.getClient().setTimeout(300*1000);
			System.out.println("Total  $" + client.balance_1());
			client.close();
		}
		catch( Exception e){
			e.printStackTrace(System.out);
		}
	}
}
