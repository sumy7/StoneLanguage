package stone.test;

import stone.ast.ASTree;
import stone.ast.NullStmnt;
import stone.evaluate.BasicEnv;
import stone.evaluate.BasicEvaluator;
import stone.evaluate.Environment;
import stone.evaluate.Evaluator;
import stone.exception.ParseException;
import stone.lexer.Lexer;
import stone.parser.BasicParser;
import stone.token.Token;

public class Runner {
    public static void main(String[] args) throws ParseException {
        run(new BasicParser(), new BasicEnv(), new BasicEvaluator());
    }

    public static void run(BasicParser bp, Environment env, Evaluator eval)
            throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());
        while (lexer.peek(0) != Token.EOF) {
            ASTree t = bp.parser(lexer);
            if (!(t instanceof NullStmnt)) {
                Object r = t.eval(env, eval);
                System.out.println("=>" + r);
            }
        }
    }
}
