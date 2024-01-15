package es.studium.EnvioRecepcion;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class Cliente
{
	public static void main(String[] args)
	{
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Introduce un mensaje: ");
		String mensajeIntroducido = scan.nextLine();
		scan.close();
		
		
		for(int x=0;x<=100;x++) {
			// Bytes del mensaje a enviar
			byte[] mensaje = mensajeIntroducido.getBytes();
			// Dirección IP del destino
			InetAddress direccionDestino;
			try
			{
				direccionDestino = InetAddress.getByName("192.168.0.17");
				// Puerto destino
				int puertoDestino = 5555;
				// Creación del paquete a enviar
				DatagramPacket packet = new DatagramPacket(mensaje, mensaje.length, direccionDestino, puertoDestino);
				// Creación de un zócalo temporal con el que realizar el envío
				DatagramSocket socket = new DatagramSocket();
				// Envío del mensaje
				socket.send(packet);
				// Cerrar el socket
				socket.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}