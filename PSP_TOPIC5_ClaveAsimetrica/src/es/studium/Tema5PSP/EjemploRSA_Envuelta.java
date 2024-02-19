package es.studium.Tema5PSP;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EjemploRSA_Envuelta {

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

	public static byte[][] encryptWrappedData(byte[] dato, PublicKey pub) {
		byte[][] encWrappedData = new byte[2][];
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128);
			SecretKey Skey = kgen.generateKey();
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, Skey);
			byte[] encMsg = cipher.doFinal(dato);
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.WRAP_MODE, pub);
			byte[] encKey = cipher.wrap(Skey);
			encWrappedData[0] = encMsg;
			encWrappedData[1] = encKey;
		} catch (Exception ex) {
			System.err.println("Ha ocurrido un error cifrando:" + ex);
		}
		return encWrappedData;
	}

	public static void main(String[] args) {
		KeyPair llaves = randomGenerate(512);
		String source = "Studium2017;";
		byte[] dato;
		try {
			dato = source.getBytes("UTF-8");
			System.out.println(Base64.getEncoder().encodeToString(encryptWrappedData(dato, llaves.getPublic())[1]));
		} catch (UnsupportedEncodingException ex) {
			System.err.println("Error: " + ex.getMessage());
		}
	}
}