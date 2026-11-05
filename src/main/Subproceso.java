package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Subproceso {
	public static Process lanzar(String nombre, String ... args) {
		System.out.println(System.getProperty("user.dir"));
		String cmd = "java -cp " + nombre + " ";
		for (int i = 0; i < args.length; i++) {
			cmd += args[i];
			cmd += " ";
		}
		System.out.println(cmd);
		ProcessBuilder p = new ProcessBuilder(cmd.split(" ", -1));
		p.directory(new File("./"));
		try {
			return p.start();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String leerResultado(Process p) {
		//System.out.println("Mostrando resultado:");
		String str = "";
		try {
			Runtime r = Runtime.getRuntime();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			p.waitFor();
			String line = "";
			
			while ((line = br.readLine()) != null) {
				str += line;
				str += "\n";
			}
			br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			line = "";
			while ((line = br.readLine()) != null) {
				System.err.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "";
		}
		return str;
	}
}
