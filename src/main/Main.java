package main;

public class Main {

	public static void main(String[] args) {
		String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		Messenger messenger = new Messenger();
		Generator generator = new Generator(path + "main\\Cities.txt");
		Bot bot = new Bot(messenger, generator);
		bot.run(args);
	}

}
