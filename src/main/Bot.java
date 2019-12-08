package main;

public class Bot {
	
	private Messenger messenger;
	private Generator generator;
	private String infoPath;
	
	public Bot() {}
	
	public Bot(Messenger messenger, Generator generator, String path) {
		this.messenger = messenger;
		this.generator = generator;
		this.infoPath = path;
	}
	
	public void run(String[] args) {
		messenger.writeFromFile(infoPath);
		messenger.write("Делайте вашу ставку");
		int startCombination = 111;
		while(true) {
			String line = messenger.read();
			while (line.equals("/help")) {
				messenger.writeFromFile(infoPath);
				line = messenger.read();
			}
			int bet = Integer.parseInt(line);
			int combination = generator.getCombination(startCombination);
			messenger.write(Integer.toString(combination));
			messenger.write(Integer.toString((int) (bet * getCoffisient(combination))));
			messenger.write("Делайте вашу ставку");
		}
	}
	
	public double getCoffisient(int combination) {
		int firstReel = combination / 100;
		int secondReel = combination / 10 % 10;
		int thirdReel = combination % 10;
		if (firstReel == secondReel && secondReel == thirdReel)
			return 2;
		else if (firstReel == secondReel || secondReel == thirdReel || firstReel == thirdReel)
			return 1.5;
		return 0;
	}
	
}
