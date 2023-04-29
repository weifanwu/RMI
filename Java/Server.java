import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
            while(true) {
                try (ServerSocket server = new ServerSocket(10314)) {
                    Socket clientSocket = server.accept();
                    InputStream input = clientSocket.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(input);
                    Question question = (Question) ois.readObject();
                    if (question.type.equals("add")) {
                        int answer = add(question.getFirst(), question.getSecond());
                        Answer result = new Answer(answer, "");
                        OutputStream output = clientSocket.getOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(output);
                        oos.writeObject(result);
                        oos.flush();
                        output.close();
                        oos.close();
                    } else if (question.type.equals("div")) {
                        OutputStream output = clientSocket.getOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(output);
                        try {
                            int answer = divide(question.getFirst(), question.getSecond());
                            Answer result = new Answer(answer, "");
                            oos.writeObject(result);
                            oos.flush();
                            oos.close();
                            output.close();
                        } catch(Exception error) {
                            oos.writeObject(error);
                        }

                    } else {
                        String answer = echo(question.getEcho());
                        Answer result = new Answer(0, answer);
                        OutputStream output = clientSocket.getOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(output);
                        oos.writeObject(result);
                        oos.flush();
                        output.close();
                        oos.close();
                    }
                } catch(Exception error) {
                    System.out.print(error);
                }
            }
    }
    
    // Do not modify any code below tihs line
    // --------------------------------------
    public static String echo(String message) { 
        return "You said " + message + "!";
    }
    public static int add(int lhs, int rhs) {
        return lhs + rhs;
    }
    public static int divide(int num, int denom) {
        if (denom == 0)
            throw new ArithmeticException();

        return num / denom;
    }
}