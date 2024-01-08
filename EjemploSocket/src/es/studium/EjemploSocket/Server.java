package es.studium.EjemploSocket;

import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	public static void main(String[] args) throws Exception
	{
		int puerto = 6000; // Puerto de escucha
		ServerSocket servidor = new ServerSocket(puerto);
		System.out.println("Escuchando en " + servidor.getLocalPort());
		Socket cliente1 = servidor.accept(); // Esperando a un cliente
		// Realizar acciones con cliente1
		// ...
		Socket cliente2 = servidor.accept(); // Esperando a otro cliente
		// Realizar acciones con cliente2
		// ...
		servidor.close(); // Cierro el socket
	}
}

