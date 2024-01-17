package es.studium.ComunicacionHilos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args)
	{
		int puerto = 6000; // Puerto
		try
		{
			ServerSocket servidor = new ServerSocket(puerto);
			System.out.println("Servidor iniciado...escuchando por el puerto " + servidor.getLocalPort());
			while(true)
			{
				Socket cliente = servidor.accept();
				// Esperando a un cliente
				HiloServidor hilo = new HiloServidor(cliente);
				hilo.start();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}