package es.studium.Tema5PSP;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;

public class EjemploRSA_Directo {

	public static byte[] encryptData(byte[] dato, PublicKey pub) {
		byte[] EncryptedData = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "SunJCE");
			cipher.init(Cipher.ENCRYPT_MODE, pub);
			EncryptedData = cipher.doFinal(dato);
		} catch (Exception ex) {
			System.err.println("Error cifrando:" + ex);
		}
		return EncryptedData;
	}

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
		String source = "Studium2017;";
		byte[] dato;
		try {
			dato = source.getBytes("UTF-8");
			System.out.println(Base64.getEncoder().encodeToString(encryptData(dato, llaves.getPublic())));
		} catch (UnsupportedEncodingException ex) {
			System.err.println("Error: " + ex.getMessage());
		}
	}
}