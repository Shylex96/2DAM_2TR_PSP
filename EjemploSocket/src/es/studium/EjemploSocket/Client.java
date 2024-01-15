package es.studium.EjemploSocket;
import java.net.InetAddress;
import java.net.Socket;
public class Client
{
	public static void main(String[] args) throws Exception
	{
		String host = "192.168.0.17";
		int puerto = 6000; // Puerto remoto al que conectar
		// Abrir Socket
		Socket cliente = new Socket(host, puerto); // Conectar
		InetAddress i = cliente.getInetAddress();
		System.out.println("Puerto local: " + cliente.getLocalPort());
		System.out.println("Puerto remoto: " + cliente.getPort());
		System.out.println("Host remoto: " + i.getHostName().toString());
		System.out.println("IP host remoto: " + i.getHostAddress().toString());
		cliente.close(); // Cierro el socket
	}
}