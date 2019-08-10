package socket.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Client {

    public static Logger log = LogManager.getLogger(Client.class);
    public static final String HOST = "http://localhost";
    public static final int PORT = 9999;

    public static void main(String[] args) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(HOST + ":" + PORT);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(15 * 1000);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            output.write("hola");
            output.flush();
            try (
               BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()))
            ) {
                StringBuilder response = new StringBuilder();
                String line;
                while((line = input.readLine()) != null) {
                    response.append(line);
                }
                log.info(response.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
