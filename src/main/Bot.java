package main;

public class Bot {
	
	private Messenger messenger;
	private Generator generator;
	private String infoPath;
	
	public Bot(Messenger messenger, Generator generator, String path) {
		this.messenger = messenger;
		this.generator = generator;
		this.infoPath = path;
	}
	
	public void run(String[] args) {
		messenger.writeFromFile(infoPath);
		while(true) {
			
		}
	}
	
	public double getCoffisient(int combination) {
		
	}
	
}
