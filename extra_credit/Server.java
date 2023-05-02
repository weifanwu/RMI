import java.net.*;
import java.io.*;

public class Server {

    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator();
        try (ServerSocket server = new ServerSocket(10314)) {
            while (true) {
                Socket socket = server.accept();
                OutputStream output = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(output);
                oos.writeObject(calculator);
                output.close();
            }
        }
    }
}