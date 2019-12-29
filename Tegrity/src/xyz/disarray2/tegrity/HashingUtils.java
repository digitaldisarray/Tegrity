package xyz.disarray2.tegrity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtils {
	private static String getFileChecksum(MessageDigest digest, File file) {
		// Get file input stream for reading the file content
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);

			// Create byte array to read data in chunks
			byte[] byteArray = new byte[1024];
			int bytesCount = 0;

			// Read file data and update in message digest
			while ((bytesCount = fis.read(byteArray)) != -1) {
				digest.update(byteArray, 0, bytesCount);
			}

			fis.close();

			// Get the hash's bytes
			byte[] bytes = digest.digest();

			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			// return complete hash
			return sb.toString();
		} catch (IOException e) {
			return null;
		}

	}

	/**
	 * This function generates and returns the md5 hash of a given file.
	 * 
	 * @param file The file to generate an md5 hash from.
	 * @return Returns the md5 hash of the file in a String. If there is an error or
	 *         the file is missing, it will return null.
	 */
	public static String getMD5(File file) {
		if (!file.exists())
			return null;

		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			return getFileChecksum(md5, file);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * This function generates and returns the sha1 hash of a given file.
	 * 
	 * @param file The file to generate a sha1 hash from.
	 * @return Returns the sha1 hash of the file in a String. If there is an error
	 *         or the file is missing, it will return null.
	 */
	public static String getSHA1(File file) {
		if (!file.exists())
			return null;

		try {
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
			return getFileChecksum(sha1, file);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("Error adding file");
		}

		return null;
	}
}
