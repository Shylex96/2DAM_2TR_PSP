package es.studium.Tema5PSP;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

public class EjemploRSA {
	public static KeyPair randomGenerate(int len) {
		KeyPair keys = null;
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			keygen.initialize(len);
			keys = keygen.genKeyPair();
		} catch (Exception ex) {
			System.err.println("Generador no disponible." + ex.getMessage());
		}
		return keys;
	}

	public static void main(String[] args) {
		KeyPair llaves = randomGenerate(512);
		System.out.println("Clave privada: " + Base64.getEncoder().encodeToString(llaves.getPrivate().getEncoded()));
		System.out.println("Clave pública: " + Base64.getEncoder().encodeToString(llaves.getPublic().getEncoded()));
	}
}