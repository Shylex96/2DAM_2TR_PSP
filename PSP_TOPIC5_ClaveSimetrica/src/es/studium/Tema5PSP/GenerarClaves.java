package es.studium.Tema5PSP;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class GenerarClaves {

	public static SecretKey keygenKeyGeneration(int keySize) {
		SecretKey Skey = null;
		if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
			try {
				KeyGenerator kgen = KeyGenerator.getInstance("AES");
				kgen.init(keySize);
				Skey = kgen.generateKey();
			} catch (NoSuchAlgorithmException ex) {
				System.err.println("Generador no disponible.");
			}
		}
		return Skey;
	}

	public static void main(String[] args) {
		String encodedKey = Base64.getEncoder().encodeToString(keygenKeyGeneration(256).getEncoded());
		System.out.println(encodedKey);
	}
}