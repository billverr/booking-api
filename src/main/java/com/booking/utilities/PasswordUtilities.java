package com.booking.utilities;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class PasswordUtilities {

	public class hashUtil {
		private MessageDigest md;

		public hashUtil(String algorithm) {
			try {
				md = MessageDigest.getInstance(algorithm);
			} catch (NoSuchAlgorithmException e) {
			}
		}

		public synchronized String digest(String input) {
			byte[] b = null;
			try {
				b = md.digest(input.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
			}
			String hex = "";
			int msb = 0;
			int lsb = 0;
			for (int i = 0; i < b.length; i++) {
				msb = (b[i] & 0x000000FF) / 16;
				lsb = (b[i] & 0x000000FF) % 16;
				hex = hex + Integer.toHexString(msb) + Integer.toHexString(lsb);
			}
			return hex;
		}
	}

	public class hmac2Util extends macUtil {
		public hmac2Util() {
			super("HmacSHA256");
		}
	}

	public class macUtil {
		private Mac mac;
		private String algorithm;

		public macUtil(String algorithm) {
			try {
				mac = Mac.getInstance(algorithm);
			} catch (NoSuchAlgorithmException e) {
			}
			this.algorithm = algorithm;
		}

		public synchronized byte[] digest(String data) {
			return digest(data, null);
		}

		public synchronized byte[] digest(String data, String key) {
			if (null != mac) {
				if (null != key) {
					try {
						mac.init(new SecretKeySpec(key.getBytes("utf-8"), algorithm));
					} catch (InvalidKeyException e) {
					} catch (UnsupportedEncodingException e) {
					}
				}
				try {
					return mac.doFinal(data.getBytes("utf-8"));
				} catch (IllegalStateException e) {
				} catch (UnsupportedEncodingException e) {
				}
			}
			return null;
		}

		public void initKey(String key) {
			if (null != mac) {
				try {
					mac.init(new SecretKeySpec(key.getBytes("utf-8"), algorithm));
				} catch (InvalidKeyException e) {
				} catch (UnsupportedEncodingException e) {
				}
			}
		}
	}

	public class md5Util extends hashUtil {
		public md5Util() {
			super("MD5");
		}
	}

	public class sha2Util extends hashUtil {
		public sha2Util() {
			super("SHA-256");
		}
	}

	private static final Logger LOG = Logger.getLogger(PasswordUtilities.class.getName());

	protected final static PasswordUtilities x = new PasswordUtilities();

	protected static Mac hmac;

	public PasswordUtilities() {
		try {
			hmac = Mac.getInstance("HmacSHA256");
		} catch (NoSuchAlgorithmException e) {
		}
	}

	public static String addSalt(String sa, String sb, String password) {
		return sa + password + sb;
	}

	public static String decryptAESwithCBCNoPadding(byte[] encrypted, byte[] initVector, byte[] key) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector);
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(encrypted);
			return new String(original);
		} catch (Exception e) {
			LOG.severe(e.getMessage() + "::" + e);
		}
		return null;
	}

	/**
	 * @param password
	 * @return
	 */
	public static String encodePassword(String sa, String sb, String password) {
		return getSHA2Utility().digest(addSalt(sa, sb, password));
	}

	public final static hmac2Util getHMAC2Utility() {
		return x.new hmac2Util();
	}

	public final static md5Util getMD5Utility() {
		return x.new md5Util();
	}

	public final static sha2Util getSHA2Utility() {
		return x.new sha2Util();
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static synchronized byte[] hmac(String data, String key) {
		if (null != hmac) {
			try {
				hmac.init(new SecretKeySpec(key.getBytes("utf-8"), "HmacSHA256"));
			} catch (InvalidKeyException e) {

			} catch (UnsupportedEncodingException e) {

			}
			try {
				return hmac.doFinal(data.getBytes("utf-8"));
			} catch (IllegalStateException e) {

			} catch (UnsupportedEncodingException e) {

			}
		}
		return null;
	}

	public static byte[] md5(byte[] input) {
		byte[] output = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			output = md.digest(input);
		} catch (NoSuchAlgorithmException e) {
			LOG.severe(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
		}
		return output;
	}

	public static byte[] md5(String input) {
		return md5(input.getBytes());
	}

	/**
	 * [A-Za-z0-9+/=]<br>
	 *
	 * @param input
	 * @return
	 */
	public static String md5Base64(byte[] input) {
		String hexOutput = null;
		byte[] output = md5(input);
		if (null != output) {
			hexOutput = DatatypeConverter.printBase64Binary(output).toUpperCase();
		}
		return hexOutput;
	}

	/**
	 * [A-Za-z0-9+/=]<br>
	 *
	 * @param input
	 * @return
	 */
	public static String md5Base64(String input) {
		return md5Base64(input.getBytes());
	}

	/**
	 * [A-Za-z0-9-_=]<br>
	 * Url-safe ("+ replaced by "-" and "/" replaced by "_") <br>
	 *
	 * @param input
	 * @return
	 */
	public static String md5Base64UrlSafe(byte[] input) {
		return md5Base64UrlSafe(input, true);
	}

	/**
	 * [A-Za-z0-9-_=]<br>
	 * Url-safe ("+ replaced by "-" and "/" replaced by "_") <br>
	 *
	 * @param input
	 * @return
	 */
	public static String md5Base64UrlSafe(String input) {
		return md5Base64UrlSafe(input.getBytes(), true);
	}

	/**
	 * [A-Za-z0-9-_]<br>
	 * Url-safe ("+ replaced by "-" and "/" replaced by "_") <br>
	 * No padding (no "=" character)
	 *
	 * @param input
	 * @return
	 */
	public static String md5Base64UrlSafeWithoutPadding(String input) {
		return md5Base64UrlSafe(input.getBytes(), false);
	}

	public static String md5Hex(byte[] input) {
		String hexOutput = null;
		byte[] output = md5(input);
		if (null != output) {
			hexOutput = DatatypeConverter.printHexBinary(output).toUpperCase();
		}
		return hexOutput;
	}

	public static String md5Hex(String input) {
		return md5Hex(input.getBytes());
	}

	/**
	 * [A-Za-z0-9-_]<br>
	 * Url-safe ("+ replaced by "-" and "/" replaced by "_") <br>
	 * "=" character may be included if isWithPadding is true
	 *
	 * @param input
	 * @return
	 */
	private static String md5Base64UrlSafe(byte[] input, boolean isWithPadding) {
		String hexOutput = null;
		byte[] output = md5(input);
		if (null != output) {
			Encoder encoder;
			if (isWithPadding) {
				encoder = Base64.getUrlEncoder();
			} else {
				encoder = Base64.getUrlEncoder().withoutPadding();
			}
			hexOutput = encoder.encodeToString(output);
		}
		return hexOutput;
	}

}
