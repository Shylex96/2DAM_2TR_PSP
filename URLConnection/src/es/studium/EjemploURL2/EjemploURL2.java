package es.studium.EjemploURL2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class EjemploURL2 {

	public static void main(String[] args)
	{
		URL url = null;
		URLConnection urlCon = null;
		try
		{
			url = new URL("https://campustudium.com");
			urlCon=url.openConnection();
			InputStream inputString = urlCon.getInputStream();
			BufferedReader in = new BufferedReader(new
					InputStreamReader(inputString));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
			{
				System.out.println(inputLine);
			}
			in.close();
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
