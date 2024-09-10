package gt.edu.umg.demodb.service;

import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import gt.edu.umg.demodb.utils.AppProperty;

@Service
public class EncryptService {

	String seed;
	String urlSite;

	public EncryptService(AppProperty properties) {
		this.seed = properties.getSeedEncrypt();
		this.urlSite = properties.getUrlSite();
	}

	public String encrypt(String text) {
		String result = "";
		try {
			byte[] key = seed.getBytes("UTF-8");
			key = Arrays.copyOf(key, 16);
			SecretKeySpec aesKey = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(text.getBytes());
			byte[] tmpByte = Base64.getEncoder().encode(encrypted);
			result = new String(tmpByte);
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	public String decrypt(String text) {
		String result = "";
		try {
			byte[] key = seed.getBytes("UTF-8");
			key = Arrays.copyOf(key, 16);
			SecretKeySpec aesKey = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			byte[] tmpBClave = Base64.getDecoder().decode(text);
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] decrypted = cipher.doFinal(tmpBClave);
			result = new String(decrypted);
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	public String getUrlSite() {
		return urlSite;
	}

}
