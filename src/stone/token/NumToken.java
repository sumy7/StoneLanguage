package stone.token;

public class NumToken extends Token {
    private int value;

    public NumToken(int line, int v) {
        super(line);
        this.value = v;
    }

    public boolean isNumber() {
        return true;
    }

    public String getText() {
        return Integer.toString(value);
    }

    public int getNumber() {
        return value;
    }

    public String toString() {
        return "< Num, " + value + ">";
    }
}
