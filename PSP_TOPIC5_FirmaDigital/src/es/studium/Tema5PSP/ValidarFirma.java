package es.studium.Tema5PSP;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class ValidarFirma {

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

	public static byte[] signData(byte[] dato, PrivateKey priv) {
		byte[] firma = null;
		try {
			Signature signer = Signature.getInstance("SHA1withRSA");
			signer.initSign(priv);
			signer.update(dato);
			firma = signer.sign();
		} catch (Exception ex) {
			System.err.println("Error firmando los datos:" + ex);
		}
		return firma;
	}

	public static boolean validateSignature(byte[] dato, byte[] firma, PublicKey pub) {
		boolean isValid = false;
		try {
			Signature signer = Signature.getInstance("SHA1withRSA");
			signer.initVerify(pub);
			signer.update(dato);
			isValid = signer.verify(firma);
		} catch (Exception ex) {
			System.err.println("Error validando los datos:" + ex);
		}
		return isValid;
	}

	public static void main(String[] args) {
		KeyPair llaves = randomGenerate(512);
		String source = "Studium2017;";
		byte[] dato;
		try {
			dato = source.getBytes("UTF-8");
			System.out.println("Dato: " +dato);
			System.out.println("Encriptación: " +Base64.getEncoder().encodeToString(signData(dato, llaves.getPrivate())));
			System.out.println("Validado: " +validateSignature(dato, signData(dato, llaves.getPrivate()), llaves.getPublic()));
		} catch (UnsupportedEncodingException ex) {
			System.err.println("Error: " + ex.getMessage());
		}
	}
}