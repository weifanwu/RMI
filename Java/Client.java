import java.io.*;
import java.net.Socket;
public class Client {

    /**
     * This method name and parameters must remain as-is
     */
    public static int add(int lhs, int rhs) {
        int result = -1;
        Question add_operation = new Question(lhs, rhs, "", "add");
        try (Socket client = new Socket(server, PORT);
             OutputStream out = client.getOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(out);
        ) {

            oos.writeObject(add_operation);
            oos.flush();
            InputStream input = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(input);
            Answer answer = (Answer) ois.readObject();
            result = answer.getAnswer();
        } catch(Exception error) {
            error.printStackTrace();
        }
        return result;
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static int divide(int num, int denom) {
        int result = -1;
        Question div_operation = new Question(num, denom, "", "div");
        Object object = null;
        try (Socket client = new Socket(server, PORT);
             OutputStream out = client.getOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(out);
        ) {
            oos.writeObject(div_operation);
            oos.flush();
            InputStream input = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(input);
            object =  ois.readObject();
        } catch(Exception errException) {
            errException.printStackTrace();
        }
        if (object instanceof Exception) {
            throw (ArithmeticException) object;
        } else {
            Answer answer = (Answer) object;
            result = answer.getAnswer();
        }
        
        return result;    
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static String echo(String message) {
        String result = "";
        Question echo = new Question(0, 0, message, "echo");
        try (Socket client = new Socket(server, PORT);
             OutputStream out = client.getOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(out);
        ) {
            oos.writeObject(echo);
            oos.flush();
            InputStream input = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(input);
            Answer answer = (Answer) ois.readObject();
            result = answer.echo();
        } catch(Exception error) {
            error.printStackTrace();
        }
        return result;        
    }

    // Do not modify any code below this line
    // --------------------------------------
    static String server = "localhost";
    public static final int PORT = 10314;

    public static void main(String... args) {
        // All of the code below this line must be uncommented
        // to be successfully graded.
        // System.out.print("Testing... ");

        if (add(2, 4) == 6)
            System.out.print(".");
        else
            System.out.print("X");

        try {
            divide(1, 0);
            System.out.print("X");
        } catch (ArithmeticException x) {
            System.out.print(".");
        }

        if (echo("Hello").equals("You said Hello!"))
            System.out.print(".");
        else
            System.out.print("X");
        
        System.out.println(" Finished");
    }
}