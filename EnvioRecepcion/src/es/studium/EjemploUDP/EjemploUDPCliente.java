package es.studium.EjemploUDP;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class EjemploUDPCliente
{
	static int puerto = 9876;
	public static void main(String[] args)
	{
		Scanner teclado = new Scanner(System.in);
		System.out.println("Escriba un mensaje");
		String mensaje = teclado.nextLine();
		// Ip a la que envio
		InetAddress destino;
		try
		{
			// InetAddress.getByName("192.168.0.17");
			// InetAddress.getLocalHost();
			destino = InetAddress.getLocalHost();
			// Socket para enviar y recibir
			DatagramSocket socket = new DatagramSocket();
			// Mensaje
			byte[] enviados = new byte[1024];
			enviados = mensaje.getBytes();
			// Construyo el datagrama para enviar
			DatagramPacket envio = new DatagramPacket(enviados,
					enviados.length, destino, puerto);
			// Hacemos el envio
			System.out.println("Cliente enviando datos....");
			socket.send(envio);
			// Recibimos el datagrama del servidor
			byte[] recibidos = new byte[1024];
			DatagramPacket recibo = new DatagramPacket(recibidos,
					recibidos.length);
			System.out.println("Cliente esperando respuesta....");
			socket.receive(recibo);
			// Visualizando info del datagrama recibido
			System.out.println("Puerto origen: " + recibo.getPort());
			System.out.println("IP origen: " + recibo.getAddress());
			System.out.println("Mensaje: " + new String(recibo.getData()));
			socket.close();
			teclado.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
