package es.studium.Eco;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
public class EcoServidor
{
	public static void main(String[] args)
	{
		ServerSocket servidor = null;
		DataInputStream dataInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		PrintStream salida = null;
		Socket cliente = null;
		String texto = "";
		try
		{
			// Abrir conexión de escucha por encima de 1023
			servidor = new ServerSocket(9999);
			System.out.println("Escuchando ...");
			// Crear socket para recibir al cliente
			cliente = servidor.accept();
			dataInputStream = new DataInputStream(cliente.getInputStream());
			inputStreamReader = new InputStreamReader(dataInputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			salida = new PrintStream(cliente.getOutputStream());
			// Al recibir, devolvemos
			while(true)
			{
				texto = bufferedReader.readLine();
				salida.println(texto);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}