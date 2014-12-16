package stone.test;

import stone.ast.ASTree;
import stone.exception.ParseException;
import stone.lexer.Lexer;
import stone.parser.BasicParser;
import stone.token.Token;

public class ParserRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        BasicParser bp = new BasicParser();
        while (l.peek(0) != Token.EOF) {
            ASTree ast = bp.parser(l);
            System.out.println("=>" + ast.toString());
        }
    }
}
