package main;

import java.util.Scanner;

public class Messenger {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public String read() {
		return scanner.nextLine();
	}
	
	public void write(String output) {
		System.out.println(output);
	}
	
}
