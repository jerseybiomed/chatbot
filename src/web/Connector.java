package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connector {
	
	public int GetRandomNumber(String request) throws IOException {
		URL url = new URL(request);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String random = input.readLine();
		input.close();
		return Integer.parseInt(random);
	}

}
