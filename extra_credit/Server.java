import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        Calculator calculator = new CalculatorImpl();
        while (true) {
            try (ServerSocket server = new ServerSocket(10314)) {
                Socket socket = server.accept();
                OutputStream output = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(output);
                oos.writeObject(calculator);
                output.close();
            }
        }
    }
    
    public static class CalculatorImpl implements Calculator, Serializable {
        public String echo(String message) {
            return "You said " + message + "!";
        }
        public int add(int lhs, int rhs) {
            return lhs + rhs;
        }
        public int divide(int num, int denom) {
            if (denom == 0)
                throw new ArithmeticException();

            return num / denom;
        }
    }
}