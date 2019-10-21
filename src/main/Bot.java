package main;

public class Bot {
	
	private Messenger messenger;
	private Generator generator;
	private Turn loseTurn = new Turn("Я сдаюсь, ты выйграл");
	
	public Bot(Messenger messenger, Generator generator) {
		this.messenger = messenger;
		this.generator = generator;
	}
	
	public void run(String[] args) {
		messenger.write("Давай играть!");
		String answer = ("у");
		while(true) {
			Turn nextTurn = generator.nextTurn(answer);
			messenger.write(nextTurn.getCity());
			if (nextTurn.getCity() != loseTurn.getCity())
				answer = messenger.read();
			else break;
		}
	}
	
}
