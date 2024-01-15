package es.studium.MinimalServerUDP;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class MinimalServer
{
	MinimalServer(){}
	byte[] getTime()
	{
		Date fecha = new Date();
		return fecha.toString().getBytes();
	}
	void go()
	{
		DatagramSocket datagramSocket;
		DatagramPacket inDatagramPacket;
		DatagramPacket outDatagramPacket;
		InetAddress direccionCliente;
		int puertoCliente;
		byte[] mensaje = new byte[10];
		byte[] time;
		try
		{
			datagramSocket = new DatagramSocket(8000);
			System.out.println("Servidor UDP activo en puerto 8000");
			System.out.println("Esperando conexión de cliente...");
			while(true)
			{
				System.out.println("Recibiendo conexión...");
				inDatagramPacket = new DatagramPacket(mensaje,
						mensaje.length);
				datagramSocket.receive(inDatagramPacket);
				direccionCliente = inDatagramPacket.getAddress();
				puertoCliente = inDatagramPacket.getPort();
				time = getTime();
				outDatagramPacket = new DatagramPacket(time, time.length,
						direccionCliente, puertoCliente);
				System.out.println("Enviando datos a "+direccionCliente+":"+puertoCliente);
				datagramSocket.send(outDatagramPacket);
			}
		}
		catch (Exception e) {}
	}
	public static void main(String[] args) 
	{
		MinimalServer udpServer = new MinimalServer();
		udpServer.go();
	}
}