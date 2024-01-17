package es.studium.Eco;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EcoCliente
{
	public static void main(String[] args)
	{
		Scanner teclado = new Scanner(System.in);
		String texto = null;
		Socket cliente = null;
		DataInputStream dataInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		PrintStream salida = null;
		System.out.println("Introducir un texto para enviar:");
		texto = teclado.nextLine();
		teclado.close();
		// Abrir conexión
		try
		{
			cliente = new Socket("", 9999);
			dataInputStream = new DataInputStream(cliente.getInputStream());
			inputStreamReader = new InputStreamReader(dataInputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			salida = new PrintStream(cliente.getOutputStream());
			salida.println(texto);
			System.out.println("Recibimos:" + bufferedReader.readLine());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
