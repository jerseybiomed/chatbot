package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Messenger {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public String read() {
		return scanner.nextLine();
	}
	
	public void write(String output) {
		System.out.println(output);
	}
	
	public void writeFromFile(String path) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
}
