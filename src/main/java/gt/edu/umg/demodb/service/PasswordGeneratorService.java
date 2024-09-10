package gt.edu.umg.demodb.service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import gt.edu.umg.demodb.utils.KeyDictionary;

@Service
public class PasswordGeneratorService {
	
	private static SecureRandom random = new SecureRandom();

	public String generatePassword(int len, KeyDictionary keyDictionary) {
		String dic = keyDictionary.getValue();
		String result = "";
		for (int i = 0; i < len; i++) {
			int index = random.nextInt(dic.length());
			result += dic.charAt(index);
		}
		return result;
	}

}
