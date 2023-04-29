import java.io.Serializable;

public class Question implements Serializable {
    int operant_one;
    int operant_two;
    String message;
    String type;
    public Question(int operant_one, int operant_two, String message, String type) {
        this.operant_one = operant_one;
        this.operant_two = operant_two;
        this.message = message;
        this.type = type;
    }
    public int getFirst() {
        return this.operant_one;
    }
    public int getSecond() {
        return this.operant_two;
    }
    public String getType() {
        return this.type;
    }
    public String getEcho() {
        return this.message;
    }
}
