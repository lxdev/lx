package net.lx.common.md5;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class Encryption {

	private static final byte[] keybytes = { 0x31, 0x32, 0x33, 0x34, 0x35,
			0x36, 0x37, 0x38, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38 };
	private static final byte[] iv = { 0x38, 0x37, 0x36, 0x35, 0x34, 0x33,
			0x32, 0x31, 0x38, 0x37, 0x36, 0x35, 0x34, 0x33, 0x32, 0x31 };

	private static final Key key = new SecretKeySpec(keybytes, "AES");

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	/**
	 * 解密
	 * 
	 * @param content
	 * @return
	 */
	public static String decryptionString(byte[] b) {
		 Security.addProvider(new BouncyCastleProvider());
		 Key key = new SecretKeySpec(keybytes, "AES");
		try {
			Cipher out = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			out.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
			byte[] dec = out.doFinal(b);
			return new String(dec);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 加密
	 * 
	 * @param content
	 * @return
	 */
	public static byte[] encryptionString(String content) {
		 Security.addProvider(new BouncyCastleProvider());
		 Key key = new SecretKeySpec(keybytes, "AES");
		Cipher in = null;
		byte[] enc = null;
		try {
			in = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			in.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
			enc = in.doFinal(content.getBytes());
		} catch (Exception e) {
			return null;
		}
		// return new String(Hex.encode(enc));
		return enc;
	}

	/**
	 * 加密
	 * 
	 * @param contentString
	 * @return
	 */
	public static String encryption(String contentString) {
		byte[] b = encryptionString(contentString);
		return new String(Hex.encode(b)).toUpperCase();
	}

	/**
	 * 解密
	 * 
	 * @param contentString
	 * @return
	 */
	public static String decryption(String contentString) {
		return decryptionString(Hex.decode(contentString.toLowerCase()));
	}

	public static void main(String[] args) {
		String contentString = "1";
		
		System.out.println(encryption("qwerty"));
	}
}