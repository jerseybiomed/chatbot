package console;

import java.util.Scanner;

public class Messenger {
	private static Scanner scanner = new Scanner(System.in);
	
	public String[] read() {
		return scanner.nextLine().split(" ");
	}
	
	public void write(String output) {
		System.out.println(output);
	}
	
	public void write(double output) {
		System.out.println(output);
	}
}
