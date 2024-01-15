package es.studium.EnvioRecepcion;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class Servidor
{
	public static void main(String[] args)
	{
		// Simular un entorno Cliente-Servidor
		while(true)
		{
			try
			{
				// Puerto de escucha para el servicio que estamos implementando
				int puertoEscucha = 5555;
				// Vector de bytes en el que recibió el mensaje con una capacidad de 256 bytes
				byte[] mensaje = new byte[256];
				// Creación del paquete en el que recibir los datos de 256 bytes como máximo
				DatagramPacket packet = new DatagramPacket(mensaje, mensaje.length);
				// Creación de un zócalo que escuche el puerto pasado por parámetro
				DatagramSocket socket = new DatagramSocket(puertoEscucha);
				// Recepción de un paquete
				socket.receive(packet);
				String cadena = new String(mensaje);
				System.out.println(cadena);
				socket.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}