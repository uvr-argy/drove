package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Subproceso {
	public static Process lanzar(String nombre, String ... args) {
		
		String classpath = System.getProperty("java.class.path");
		String cmd = "java -cp " + classpath + " " + nombre + " ";
		for (int i = 0; i < args.length; i++) {
			cmd += args[i];
			cmd += " ";
		}

		ProcessBuilder p = new ProcessBuilder(cmd.split(" ", -1));

		p.redirectErrorStream(true);
		try {
			return p.start();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String leerResultado(Process p) {
		String str = "";
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {				
				str += (line + "\n");
			}
			return str;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static void esperar(Process p) {
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			return;
		}
	}
}
