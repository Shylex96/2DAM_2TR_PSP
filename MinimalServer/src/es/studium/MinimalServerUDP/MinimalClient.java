package es.studium.MinimalServerUDP;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class MinimalClient
{
	MinimalClient(){}
	void go()
	{
		DatagramSocket datagramSocket;
		DatagramPacket inDatagramPacket;
		DatagramPacket outDatagramPacket;
		InetAddress direccionServidor;
		int puertoServidor = 8000;
		byte[] mensaje = new byte[10];
		String mensajeCadena = "";
		try
		{
			datagramSocket = new DatagramSocket();
			direccionServidor = InetAddress.getLocalHost();
			outDatagramPacket = new DatagramPacket(mensaje, 1, direccionServidor,
					puertoServidor);
			datagramSocket.send(outDatagramPacket);
			inDatagramPacket = new DatagramPacket(mensaje, mensaje.length);
			datagramSocket.receive(inDatagramPacket);
			mensajeCadena = new String(inDatagramPacket.getData(), 0,
					inDatagramPacket.getLength());
			System.out.println(mensajeCadena);
			datagramSocket.close();
		}
		catch (Exception e) {}
	}
	public static void main(String[] args)
	{
		MinimalClient client = new MinimalClient();
		client.go();
	}
}