package stone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import stone.ast.ASTree;
import stone.ast.NullStmnt;
import stone.evaluate.BasicEvaluator;
import stone.evaluate.BuildInConst;
import stone.evaluate.Environment;
import stone.evaluate.Evaluator;
import stone.evaluate.Natives;
import stone.evaluate.NestedEnv;
import stone.exception.ParseException;
import stone.lexer.Lexer;
import stone.parser.ArrayParser;
import stone.parser.BasicParser;
import stone.token.Token;

public class Stone {
    private static BasicParser parser = new ArrayParser();
    private static Environment env = new BuildInConst()
            .environment(new Natives().environment(new NestedEnv()));
    private static Evaluator eval = new BasicEvaluator();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("java Stone <Source File>");
            return;
        }
        File file = new File(args[0]);
        if (file.isFile()) {
            try {
                FileReader reader = new FileReader(file);
                Lexer lexer = new Lexer(reader);
                while (lexer.peek(0) != Token.EOF) {
                    ASTree t = parser.parser(lexer);
                    if (!(t instanceof NullStmnt)) {
                        t.eval(env, eval);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Source File Error.");
        }
    }

}
