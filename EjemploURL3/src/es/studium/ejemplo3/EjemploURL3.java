package es.studium.ejemplo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
public class EjemploURL3
{
	public static void main(String[] args)
	{
		try
		{
			URL url = new URL("http://192.168.0.17/PSP/procesa.php");
			URLConnection conexion = url.openConnection();
			conexion.setDoOutput(true);
			String cadena = "nombre=Armando&apellidos=Bronca Segura";
			// ESCRIBIR EN LA URL
			PrintWriter output = new PrintWriter(conexion.getOutputStream());
			output.write(cadena);
			output.flush();
			output.close();
			// LEER DE LA URL
			BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			String linea;
			while ((linea = reader.readLine()) != null)
			{
				System.out.println(linea);
			}
			reader.close();
		}
		catch (MalformedURLException e)
		{
			System.out.println(e);
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
	}
}