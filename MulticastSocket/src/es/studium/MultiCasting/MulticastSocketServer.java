package es.studium.MultiCasting;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class MulticastSocketServer
{
	MulticastSocket socket;
	InetAddress multicastIP;
	int puerto;
	boolean continueRunning = true;
	public void init(String Strip, int portValue) throws SocketException, IOException
	{
		socket = new MulticastSocket(portValue);
		multicastIP = InetAddress.getByName(Strip);
		puerto = portValue;
	}
	public void runserver() throws IOException
	{
		DatagramPacket packet;
		byte[] sendingData;
		// El servidor hace envíos continuos mientras sea necesario
		while(continueRunning)
		{
			// Obtención de los datos a enviar. Supondremos la existencia de un
			// método específico para las diferentes aplicaciones
			sendingData = getNextData();
			// Creación del paquete para enviar los datos obtenidos
			packet = new DatagramPacket(sendingData, sendingData.length,
					multicastIP, puerto);
			// Envío de los datos
			socket.send(packet);
			// Miramos si es necesario terminar la transmisión
			continueRunning = !transmissionFinished();
		}
	}
	private boolean transmissionFinished()
	{
		// Operaciones necesarias para devolver datos coherentes
		return false;
	}
	private byte[] getNextData()
	{
		// Operaciones necesarias para devolver datos coherentes
		return null;
	}
	public void close()
	{
		if(socket!=null && !socket.isClosed())
		{
			socket.close();
		}
	}
}
