package es.studium.ComunicacionObjetos2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import es.studium.ComunicacionObjetos.Persona;

public class Ejemplo1ServidorObjeto
{
	static final int PUERTO = 6000;
	public static void main(String[] arg)
	{
		try
		{
			ServerSocket servidor = new ServerSocket(PUERTO);
			System.out.println("Esperando al cliente...");
			Socket cliente = servidor.accept(); // Crea objeto
			// Flujo de salida
			ObjectOutputStream salida = new
					ObjectOutputStream(cliente.getOutputStream());
			Persona per=new Persona("Juan",20);//Se prepara el objeto
			salida.writeObject(per);//enviando el objeto
			System.out.println("Envío realizado al Cliente: "+per.getNombre()+"-"+per.getEdad());
			// Flujo de entrada
			ObjectInputStream entrada = new
					ObjectInputStream(cliente.getInputStream());
			Persona dato=(Persona) entrada.readObject();
			System.out.println("Datos recibidos del Cliente: "+dato.getNombre()+"-"+dato.getEdad());
			// Cerramos streams y sockets
			entrada.close();
			salida.close();
			cliente.close();
			servidor.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}