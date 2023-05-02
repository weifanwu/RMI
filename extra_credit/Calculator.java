import java.io.Serializable;

public class Calculator implements Serializable {
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