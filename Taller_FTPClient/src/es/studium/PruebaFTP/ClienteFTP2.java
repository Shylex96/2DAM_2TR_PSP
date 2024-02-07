package es.studium.PruebaFTP;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
public class ClienteFTP2
{
	public static void main(String[] args)
	{
		try
		{
			FTPClient cliente = new FTPClient();
			String servFTP = "localhost"; // Servidor FTP
			System.out.println("Nos conectamos a: " + servFTP);
			String usuario = "esteban";
			String clave = "Studium2023;";
			cliente.connect(servFTP);
			boolean login = cliente.login(usuario, clave);
			if (login)
			{
				System.out.println("Login correcto...");
			}
			else
			{
				System.out.println("Login Incorrecto...");
				cliente.disconnect();
				System.exit(1);
			}
			System.out.println("Directorio actual: " + cliente.printWorkingDirectory());
			FTPFile[] files = cliente.listFiles();
			System.out.println("Ficheros en el directorio actual:" + files.length);
			// Array para visualizar el tipo de fichero
			String tipos[] = {"Fichero","Directorio","Enlace simbólico"};
			for (int i = 0; i < files.length; i++)
			{
				System.out.println("\t"+files[i].getName()+"=>"+tipos[files[i].getType()]);
			}
			boolean logout = cliente.logout();
			if (logout)
			{
				System.out.println("Logout del servidor FTP...");
			}
			else
			{
				System.out.println("Error al hacer Logout...");
			}
			cliente.disconnect();
			System.out.println("Desconectado...");
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}