package es.studium.ComunicacionHilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente
{
	static final String HOST = "localhost";
	static final int PUERTO = 6000;
	public static void main(String[] arg)
	{
		Scanner sc=new Scanner(System.in);
		try
		{
			System.out.println("Iniciando programa cliente..");
			Socket cliente = new Socket(HOST, PUERTO);
			// Creo el flujo de salida al servidor
			DataOutputStream flujoSalida = new
					DataOutputStream(cliente.getOutputStream());
			// Creo el flujo de entrada desde servidor
			InputStream entrada = cliente.getInputStream();
			DataInputStream flujoEntrada = new
					DataInputStream(entrada);
			String cadena=null;
			do
			{
				System.out.println("Escriba un mensaje..");
				cadena=sc.nextLine();
				flujoSalida.writeUTF(cadena);
				String eco=flujoEntrada.readUTF();
				System.out.println("=>Eco:"+eco);
			}while(!cadena.equals("*"));
			System.out.println("Fin programa cliente..");
			// Cerramos streams y sockets
			entrada.close();
			flujoEntrada.close();
			flujoSalida.close();
			cliente.close();
			sc.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
