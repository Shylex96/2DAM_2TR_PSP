package es.studium.ComunicacionObjetos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class EjemploUDPClienteObjeto
{
	static int puerto = 9876;
	public static void main(String[] args)
	{
		try
		{
			// Socket para enviar y recibir
			DatagramSocket socket = new DatagramSocket();
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ObjectOutputStream salida = new ObjectOutputStream(bs);
			Persona per = new Persona("Esteban", 27);// Se prepara el objeto
			salida.writeObject(per);// Enviando el objeto
			byte[] bytes = bs.toByteArray();
			// Construyo el datagrama para enviar
			// InetAddress.getByName("192.168.0.17");
			// InetAddress.getLocalHost()
			DatagramPacket envio = new DatagramPacket(bytes,
					bytes.length, InetAddress.getByName("192.168.0.17"), puerto);
			// Hacemos el envio
			socket.send(envio);
			System.out.println("Envío realizado al Servidor: " +
					per.getNombre() + "-" + per.getEdad());
			salida.close();
			socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
