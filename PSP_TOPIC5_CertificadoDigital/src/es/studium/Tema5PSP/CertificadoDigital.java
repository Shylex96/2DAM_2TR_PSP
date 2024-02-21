package es.studium.Tema5PSP;

import java.util.Scanner;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class CertificadoDigital {

	public static void main(String[] argv) throws Exception {
		System.setProperty("javax.net.ssl.keyStore", "ServerKeyStore.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		try {
			SSLServerSocketFactory sslFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			SSLServerSocket srvSocket = (SSLServerSocket) sslFactory.createServerSocket(4043);
			int numClient = 1;
			System.out.println("Escuchando...");
			while (true) {
				SSLSocket cliSocket = (SSLSocket) srvSocket.accept();
				Scanner reader = new Scanner(cliSocket.getInputStream());
				String texto = reader.nextLine();
				while (!texto.equals("<< FIN >>")) {
					System.out.println("[Cliente" + numClient + "]" + texto);
					System.out.flush();
					texto = reader.nextLine();
				}
				System.out.println("[Cliente" + numClient + "] Cerrando conexión ...");
				cliSocket.close();
				reader.close();
				numClient++;
			}
		} catch (Exception ex) {
			System.out.println("Error en las comunicaciones:" + ex);
		}
	}
}