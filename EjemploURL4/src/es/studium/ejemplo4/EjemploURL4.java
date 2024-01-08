package es.studium.ejemplo4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EjemploURL4
{
	public static void main(String[] args)
	{
		String cadena;
		try
		{
			URL url = new URL("http://192.168.0.17/PSP/index.html");
			URLConnection conexion = url.openConnection();
			System.out.println("Direccion [getURL()]:" + conexion.getURL());
			Date fecha = new Date(conexion.getLastModified());
			System.out.println("Fecha ultima modificacion [getLastModified()]: "+ fecha);
			System.out.println("Tipo de Contenido [getContentType()]: "+
					conexion.getContentType());
			System.out.println("============================================== ");
			System.out.println("TODOS LOS CAMPOS DE CABECERA CON getHeaderFields(): ");
			Map<String, List<String>> camposcabecera = conexion.getHeaderFields();
			Iterator<Map.Entry<String, List<String>>> it =
					camposcabecera.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, List<String>> map = it.next();
				System.out.println(map.getKey() + " : " + map.getValue());
			}
			System.out.println("================================================ ");
			System.out.println("CAMPOS 1 Y 4 DE CABECERA:");
			System.out.println("getHeaderField(1)=> " + conexion.getHeaderField(1));
			System.out.println("getHeaderField(4)=> " + conexion.getHeaderField(4));
			System.out.println("=================================================== ");
			System.out.println("CONTENIDO DE [url.getFile()]:" + url.getFile());
			BufferedReader pagina = new BufferedReader(new InputStreamReader(
					url.openStream()));
			while ((cadena = pagina.readLine()) != null) {
				System.out.println(cadena);
			}
		}catch (MalformedURLException e)
		{
			System.out.println(e);
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
	}
}