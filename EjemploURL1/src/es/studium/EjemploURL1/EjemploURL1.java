package es.studium.EjemploURL1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class EjemploURL1 {
	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL("https://campustudium.com");
			InputStream inputString = url.openStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(inputString));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}