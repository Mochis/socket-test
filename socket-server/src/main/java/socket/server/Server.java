package socket.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static Logger log = LogManager.getLogger(Server.class);
    public static final int PORT = 9999;

    public static void main(String[] args) throws Exception {
        ServerSocket sock = null;
        Socket clientConnection;
        boolean a = true;
//        try {
            sock = new ServerSocket(PORT);
            clientConnection = sock.accept();
//            try (
                BufferedReader input = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientConnection.getOutputStream()));
//            ) {
                StringBuilder response = new StringBuilder();
//                if(a == true){
//                    throw new Exception();
//                }
                String line;
                while(!(line = input.readLine()).equals("")) {
                    response.append(line);
                }
                log.info(response.toString());
                output.write("HTTP/1.1 200 OK\r\n");
                output.write("Content-Type: text/html\r\n");
                output.write("Content-Length: 80\r\n\r\n");
                output.write("<html><head></head><body><h1>Hello</h1></body></html>");
                output.flush();
                input.readLine();
//            } catch (IOException e){
//                log.error(e);
//            }
//        } catch (Exception e) {
//            log.error(e);
//        } finally {
//            try {
//                sock.close();
//            } catch (Exception e) {
//                log.error("Error closing socket", e);
//            }
//        }
    }
}
