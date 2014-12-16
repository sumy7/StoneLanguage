package stone.test;

import stone.exception.ParseException;
import stone.lexer.Lexer;
import stone.token.Token;

public class LexerRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        for (Token t; (t = l.read()) != Token.EOF;) {
            System.out.println(t.toString());
        }
    }
}
