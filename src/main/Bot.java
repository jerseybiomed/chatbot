package main;

public class Bot {
	
	private Messenger messenger;
	private Generator generator;
	private Turn loseTurn = new Turn("Я сдаюсь, ты выйграл");
	private String infoPath;
	
	public Bot(Messenger messenger, Generator generator, String path) {
		this.messenger = messenger;
		this.generator = generator;
		this.infoPath = path;
	}
	
	public void run(String[] args) {
		messenger.writeFromFile(infoPath);
		messenger.write("Начинаем, первый город:");
		String answer = ("н");
		while(true) {
			Turn nextTurn = generator.nextTurn(answer);
			messenger.write(nextTurn.getCity());
			if (nextTurn.getCity() != loseTurn.getCity()) {
				answer = messenger.read();
				while (answer.equals("\\help") 
						|| answer.equals("help") 
						|| Character.toLowerCase(answer.charAt(0)) != nextTurn.getNextLetter()
						|| !generator.checkAnswer(answer)) {
					if (answer.equals("\\help")) {
						messenger.writeFromFile(infoPath);
						messenger.write("Я жду от тебя города");
					} else if (answer.equals("help")) {
						Turn helpTurn = generator.nextTurn(nextTurn.getCity());
						messenger.write("Скажи город: " + helpTurn.getCity());
					} else if (!generator.checkAnswer(answer)) {
						messenger.write("Я не знаю такого города, назови другой");
					} else {
						messenger.write("Нужен город на букву " + Character.toUpperCase(nextTurn.getNextLetter()));
					}
					answer = messenger.read();
				}
			}
			else break;
		}
	}
	
}
