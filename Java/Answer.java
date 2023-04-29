import java.io.Serializable;

public class Answer implements Serializable {
    int answer;
    String echo;
    public Answer(int answer, String echo) {
        this.answer = answer;
        this.echo = echo;
    }
    public int getAnswer() {
        return this.answer;
    }
    public String echo() {
        return this.echo;
    }
}