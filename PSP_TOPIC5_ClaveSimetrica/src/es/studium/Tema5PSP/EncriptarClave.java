package es.studium.Tema5PSP;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncriptarClave {
	
	public static SecretKey passwordKeyGeneration(String texto, int keySize) {
		SecretKey Skey = null;
		if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
			try {
				byte data[] = texto.getBytes("UTF-8");
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte hash[] = md.digest(data);
				byte key[] = Arrays.copyOf(hash, keySize / 8);
				Skey = new SecretKeySpec(key, "AES");
			} catch (Exception ex) {
				System.err.println("Error generando la clave:" + ex);
			}
		}
		return Skey;
	}

	public static void main(String[] args) {
		String encodedKey = Base64.getEncoder().encodeToString(passwordKeyGeneration("123545", 256).getEncoded());
		System.out.println(encodedKey);	
	}
}