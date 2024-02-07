package es.studium.PruebaFTP;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class ClienteFTP3 {

	public static void main(String[] args)
	{
		FTPClient cliente = new FTPClient(); // Cliente
		String servidor = "localhost"; // Servidor
		String user = "esteban";
		String pasw = "Studium2023;";
		try
		{
			System.out.println("Conectandose a " + servidor);
			cliente.connect(servidor);
			boolean login = cliente.login(user, pasw);
			String direc = "/directorio";
			if (login)
			{
				System.out.println("Cambiando de directorio...");
				cliente.changeWorkingDirectory(direc);
				cliente.setFileType(FTP.BINARY_FILE_TYPE);
				System.out.println("Transfiriendo datos...");
				// Stream de entrada con el fichero a subir
				BufferedInputStream in = new BufferedInputStream(new
						FileInputStream("texto1.txt"));
				cliente.storeFile("texto1.txt", in);
				in = new BufferedInputStream(new FileInputStream("minion.jpg"));
				cliente.storeFile("minion.jpg", in);
				in.close(); // Cerrar flujo
				cliente.logout(); // Logout del usuario
				cliente.disconnect(); // Desconexión del servidor
				System.out.println("Desconectado...");
			}
		}
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}