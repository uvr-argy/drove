package main;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Wrapper {
	public static String codificar(String str) {
		String encoded = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
		return encoded;
	}
	
	public static String decodificar(String str) {
		byte[] decodedBytes = Base64.getDecoder().decode(str);
		String decoded = new String(decodedBytes, StandardCharsets.UTF_8);
		return decoded;
	}
}
