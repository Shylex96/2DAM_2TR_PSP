package es.studium.ComunicacionObjetos2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import es.studium.ComunicacionObjetos.Persona;

public class Ejemplo1ClienteObjecto
{
	static final String HOST = "localhost";
	static final int PUERTO = 6000;
	
	public static void main(String[] arg)
	{
		try
		{
			System.out.println("Iniciando programa cliente..");
			Socket cliente = new Socket(HOST, PUERTO);
			// Creo el flujo de entrada desde servidor
			ObjectInputStream entrada = new
					ObjectInputStream(cliente.getInputStream());
			// Recibe un objeto
			Persona dato=(Persona) entrada.readObject();
			System.out.println("Datos recibidos del Servidor: "+dato.getNombre()+"-"+dato.getEdad());
			// Modifico el objeto
			dato.setNombre("Juan Ramos");
			dato.setEdad(22);
			// Flujo de salida
			ObjectOutputStream salida = new
					ObjectOutputStream(cliente.getOutputStream());
			salida.writeObject(dato);// Enviando el objeto
			System.out.println("Envío realizado al Servidor: "+dato.getNombre()+"-"+dato.getEdad());
			// Cerramos streams y sockets
			entrada.close();
			cliente.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
