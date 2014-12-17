package stone.token;

public class StrToken extends Token {
    private String literal;

    public StrToken(int line, String str) {
        super(line);
        this.literal = str;
    }

    public boolean isString() {
        return true;
    }

    public String getText() {
        return this.literal;
    }

    public String toString() {
        return "< Str, \"" + literal + "\">";
    }
}
