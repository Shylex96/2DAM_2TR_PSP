package es.studium.TestInetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {

	public static void main(String[] args)
	{
		try
		{
			System.out.println("-> Direccion IP actual de LocalHost");
			InetAddress address = InetAddress.getLocalHost();
			pruebaMetodos(address);
			System.out.println("-> Direccion IP de una URL, por nombre");
			address = InetAddress.getByName("www.google.es");
			pruebaMetodos(address);
			// Array de tipo inetAddress con todas las direcciones IP asignadas
			// a google.es
			System.out.println("-> Direcciones IPs para:" + address.getHostName());
			InetAddress[] addressArray = InetAddress.getAllByName(address.getHostName());
			for (int i = 0; i < addressArray.length; i++)
			{
				System.out.println(addressArray[i].toString());
			}

			// Probar Loopback de dos url
			InetAddress[] direcciones = new InetAddress[2];
			direcciones[0] = InetAddress.getLoopbackAddress();
			direcciones[1] = InetAddress.getByName("www.grupostudium.com");
			for(InetAddress direccion : direcciones)
			{
				if(direccion.isLoopbackAddress())
				{
					System.out.println(direccion.getHostName() + " tiene una dirección de loopback");
				}
				else
				{
					System.out.println(direccion.getHostName() + " no tiene una dirección de loopback");
				}
			}
		}
		catch (UnknownHostException e)
		{
			System.out.println("Debes estar conectado");
			System.out.println(e);
		}
	}
	private static void pruebaMetodos(InetAddress dir)
	{
		System.out.println("\t getHostName:" + dir.getHostName());
		System.out.println("\t getHostAddress:" + dir.getHostAddress());
		System.out.println("\t toString:" + dir.toString());
		System.out.println("\t getCanonicalHostName:"+ dir.getCanonicalHostName());
	}
}
