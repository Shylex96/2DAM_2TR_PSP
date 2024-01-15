package es.studium.EjemploUDP;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class EjemploUDPServidor
{
	static int puerto = 9876;
	public static void main(String[] args)
	{
		try
		{
			// Socket para enviar y recibir
			DatagramSocket socket = new DatagramSocket(puerto);
			byte[] enviados = new byte[1024];
			byte[] recibidos = new byte[1024];
			String cadena = "";
			while(!cadena.trim().equals("*"))
			{
				System.out.println("Servidor esperando datagrama..");
				//Recibo datagrama
				recibidos = new byte[1024];
				DatagramPacket recibo = new
						DatagramPacket(recibidos,
								recibidos.length);
				socket.receive(recibo);
				cadena = new String(recibo.getData());
				//Visualizando info del datagrama recibido
				System.out.println("Puerto origen: " + recibo.getPort());
				System.out.println("IP origen: " + recibo.getAddress());
				System.out.println("Mensaje: " + new
						String(recibo.getData()));
				//Convertimos la cadena a mayúsculas
				String convertida = cadena.trim().toUpperCase();
				enviados = convertida.getBytes();
				//Construyo el datagrama para enviar
				DatagramPacket envio = new
						DatagramPacket(enviados,
								enviados.length, recibo.getAddress(),
								recibo.getPort());
				//Hacemos el envio
				socket.send(envio);
				System.out.println("----------------------");
			}
			socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}