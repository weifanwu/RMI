
import java.net.*;
import java.io.*;
import java.lang.reflect.Method;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket client = new Socket("localhost", 10314);
        InputStream input = client.getInputStream();
        ObjectInputStream ors = new ObjectInputStream(input);
        Object obj = ors.readObject();
        Method addMethod = Calculator.class.getMethod("divide", int.class, int.class);
        int answer = (int) addMethod.invoke(obj, 12, 6);
        System.out.println("This is the answer: " + answer);
        client.close();
    }
}