package ipc;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastPeer {

	public static void main(String[] args) {
		
		MulticastSocket s = null;
		try{
			InetAddress group = InetAddress.getByName(args[1]);
			s = new MulticastSocket(6789);
			s.joinGroup(group);
			byte[] m = args[0].getBytes();
			DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6789);
			s.send(messageOut);
			
			for(int i = 0; i < 10; i++){
				byte[] buffer = new byte[1000];
				DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
				s.receive(messageIn);
				System.out.println("Received: " + new String(messageIn.getData(), 0, 
						messageIn.getLength()));
			}
			
			s.leaveGroup(group);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			if(s != null){
				s.close();
			}
		}

	}

}
