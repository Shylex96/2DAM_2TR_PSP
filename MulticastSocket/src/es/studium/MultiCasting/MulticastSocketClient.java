package es.studium.MultiCasting;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class MulticastSocketClient
{
	MulticastSocket socket;
	InetAddress multicastIP;
	int puerto;
	public void init(String Strip, int portValue) throws SocketException, IOException
	{
		multicastIP = InetAddress.getByName(Strip);
		puerto = portValue;
		socket = new MulticastSocket(puerto);
	}
	public void runClient() throws IOException
	{
		DatagramPacket packet;
		byte[] receivedData = new byte[256];
		boolean continueRunning = true;
		// Activamos la suscripción
		socket.joinGroup(multicastIP);
		// El cliente atiende el puerto hasta que decide finalizar
		while(continueRunning)
		{
			// Creación del paquete para recibir los datos
			packet = new DatagramPacket(receivedData, 256);
			// Espera de los datos, máximo 5 segundos
			socket.setSoTimeout(5000);
			try
			{
				// Espera de los datos
				socket.receive(packet);
				// Procesamiento de los datos recibidos y obtención de la respuesta
				continueRunning = getData(packet.getData(),
						packet.getLength());
			}
			catch(SocketTimeoutException e)
			{
				// Se ha excedido el tiempo de espera y hay que saber qué hacer
				continueRunning = timeoutExceeded();
			}
		}
		// se cancela la suscripción
		socket.leaveGroup(multicastIP);
	}
	private boolean timeoutExceeded()
	{
		// Operaciones necesarias para devolver datos coherentes
		return false;
	}
	private boolean getData(byte[] data, int length)
	{
		// Operaciones necesarias para devolver datos coherentes
		return false;
	}
	public void close()
	{
		if(socket!=null && !socket.isClosed())
		{
			socket.close();
		}
	}
}