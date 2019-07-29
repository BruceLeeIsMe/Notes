import java.io.*;
import java.net.*;
class UdpSend implements Runnable{
	public void run(){	
	try{
		DatagramSocket ds=new DatagramSocket(10000);
		while(true){
			BufferedReader bfr=new BufferedReader(new InputStreamReader(System.in));
			String data=bfr.readLine();
			byte buf[]=data.getBytes();
			DatagramPacket dp=new 
			DatagramPacket(buf,buf.length,InetAddress.getByName("10.4.172.255"),10001);
			ds.send(dp);
		}
	}
	catch (Exception e){
		throw new RuntimeException();
	}
	}
	
}
class UdpRece implements Runnable {
	public void run(){
	try{
		DatagramSocket ds= new DatagramSocket(10001);
		while(true){
			byte buf[]=new byte[1024];
			DatagramPacket dp= new DatagramPacket(buf,buf.length);
			ds.receive(dp);
			String data= new String(buf,0,dp.getLength());
			System.out.print(dp.getAddress()+"::"+data+"::"+dp.getPort());
		}
		
	}
	catch (Exception e){
		throw new RuntimeException();
	}
	}
	
}
class MulThread{
	public static void main(String []args) throws Exception{
		//System.out.print(InetAddress.getLocalHost().getHostAddress());
		new Thread(new UdpSend()).start();
		new Thread(new UdpRece()).start();
	}
	
}