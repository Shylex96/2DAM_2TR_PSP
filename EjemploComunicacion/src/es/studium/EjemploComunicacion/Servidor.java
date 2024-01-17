package es.studium.EjemploComunicacion;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor
{
	static final int PUERTO = 6000;
	
	public static void main(String[] arg)
	{
		try
		{
			ServerSocket servidor = new ServerSocket(PUERTO);
			Socket cliente = null;
			System.out.println("Esperando al cliente...");
			cliente = servidor.accept(); // Crea objeto
			//Flujo de entrada del cliente
			InputStream entrada = cliente.getInputStream();
			DataInputStream flujoEntrada = new DataInputStream(entrada);
			//El cliente me envia un mensaje
			System.out.println("Recibiendo del CLIENTE: \n\t"+flujoEntrada.readUTF());
					//Flujo de salida
					OutputStream salida = cliente.getOutputStream();
					DataOutputStream flujoSalida = new DataOutputStream(salida);
					flujoSalida.writeUTF("Saludos al Cliente desde el Servidor");
					// Cerramos streams y sockets
					entrada.close();
					flujoEntrada.close();
					salida.close();
					flujoSalida.close();
					cliente.close();
					servidor.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
