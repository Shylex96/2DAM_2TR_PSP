package es.studium.PruebaFTP;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class PruebaFTP
{
	public static void main(String[] args)
	{
		try
		{
			FTPClient cliente = new FTPClient();
			String servFTP = "ftp.rediris.es"; // Servidor FTP
			System.out.println("Nos conectamos a: " + servFTP);
			cliente.connect(servFTP);
			// Respuesta del servidor FTP
			System.out.print(cliente.getReplyString());
			// Código de respuesta
			int respuesta = cliente.getReplyCode();
			// Comprobación del código de respuesta
			if (!FTPReply.isPositiveCompletion(respuesta))
			{
				cliente.disconnect();
				System.out.println("Conexión rechazada: " + respuesta);
				System.exit(0);
			}
			else
			{
				// Desconexión del servidor FTP
				cliente.disconnect();
				System.out.println("Conexión finalizada.");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
