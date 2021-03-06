package com.java.project.checkinfront.mail;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import javax.crypto.Cipher;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import javax.crypto.spec.SecretKeySpec;


public class EncryptSecurity {
	
	 private static final String ALGO = "AES";
	    private static final byte[] keyValue =
	            new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

	    /**
	     * Encrypt a string with AES algorithm.
	     *
	     * @param data is a string
	     * @return the encrypted string
	     */
	    @SuppressWarnings("restriction")
		public static String encrypt(String data) throws Exception {
	        Key key = generateKey();
	        Cipher c = Cipher.getInstance(ALGO);
	        c.init(Cipher.ENCRYPT_MODE, key);
	        byte[] encVal = c.doFinal(data.getBytes());
	        return new BASE64Encoder().encode(encVal);
	    }

	    /**
	     * Decrypt a string with AES algorithm.
	     *
	     * @param encryptedData is a string
	     * @return the decrypted string
	     */
	    @SuppressWarnings("restriction")
		public static String decrypt(String encryptedData) throws Exception {
	        Key key = generateKey();
	        Cipher c = Cipher.getInstance(ALGO);
	        c.init(Cipher.DECRYPT_MODE, key);
	        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
	        byte[] decValue = c.doFinal(decordedValue);
	        return new String(decValue);
	    }

	    /**
	     * Generate a new encryption key.
	     */
	    private static Key generateKey() throws Exception {
	        return new SecretKeySpec(keyValue, ALGO);
	    }
	    
	    public static void main(String[] args) throws Exception {
			String mail = "makeuplifeadm@gmail.com";
			String password="tqeqnejzigcetgyg";
			//J3FPukD52orOWTfXdvMDlUUkkdXz2cs3QW7EK7rmy7M=
			//7WFF0dFj970VNFycLLPKQw==
			//makeuplifeadm@gmail.com
			//magc1782
			//tqeqnejzigcetgyg
			
			String encr1;
			String encr2;
			System.out.println("ENCRYPT");
			encr1 = EncryptSecurity.encrypt(mail);
			encr2 = EncryptSecurity.encrypt(password);
			System.out.println(encr1);
			System.out.println(encr2);
			System.out.println(EncryptSecurity.decrypt(encr1));
			System.out.println(EncryptSecurity.decrypt(encr2));
		}

}
