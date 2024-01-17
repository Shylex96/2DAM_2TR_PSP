package es.studium.EjemploComunicacion;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class Cliente
{
	static final String HOST = "localhost";
	static final int PUERTO = 6000;
	
	public static void main(String[] arg)
	{
		try
		{
			System.out.println("Iniciando programa cliente..");
			Socket cliente = new Socket(HOST, PUERTO);
			// Creo el flujo de salida al servidor
			DataOutputStream flujoSalida = new
					DataOutputStream(cliente.getOutputStream());
			// Envío un saludo al servidor
			flujoSalida.writeUTF("Saludos al Servidor desde el Cliente");
			// Creo el flujo de entrada desde servidor
			InputStream entrada = cliente.getInputStream();
			DataInputStream flujoEntrada = new DataInputStream(entrada);
			// El servidor me envia un mensaje
			System.out.println("Recibiendo del SERVIDOR...\n\t"+flujoEntrada.readUTF());
			// Cerramos streams y sockets
			flujoSalida.close();
			entrada.close();
			flujoEntrada.close();
			cliente.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
