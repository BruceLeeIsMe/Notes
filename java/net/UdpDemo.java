import java.io.*;
import java.net.*;
class UdpSend{
	
	public static void main(String []args) throws Exception{
	
		DatagramSocket ds=new DatagramSocket(8888);
		
		String data="nihao zaijian";
		byte []buf=data.getBytes();
		DatagramPacket dp=
		new DatagramPacket(buf,buf.length,InetAddress.getByName("169.254.189.123"),10000);
		System.out.print(""+InetAddress.getLocalHost());
		ds.send(dp);
		ds.close();
		
	}
	
}
class UdpRece{
	public static void main(String []args) throws Exception{
		DatagramSocket ds=new DatagramSocket(10000);
		//while(true){
			byte buf[]=new byte[1024];
			DatagramPacket dp=new DatagramPacket(buf,buf.length);
			ds.receive(dp);
			String data=new String(dp.getData(),0,dp.getLength());
			System.out.print(dp.getAddress()+"::"+data+"::"+dp.getPort());
			ds.close();
		//}
	}
	
}