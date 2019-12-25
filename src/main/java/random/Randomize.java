package random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Randomize {
    private int result = -1;

    public Randomize(int result) {
        this.result = result;
    }

    public Randomize() {}

    public int Next(String request) throws IOException {
        if (result == -1) {
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String random = input.readLine();
            input.close();
            return Integer.parseInt(random);
        }
        return result;
    }
}
