package stone.parser;

import static stone.parser.Parser.rule;
import stone.ast.ArrayLiteral;
import stone.ast.ArrayRef;

public class ArrayParser extends FunParser {
    Parser elements = rule(ArrayLiteral.class).ast(expr).repeat(
            rule().sep(",").ast(expr));

    public ArrayParser() {
        reserved.add("]");
        primary.insertChoice(rule().sep("[").maybe(elements).sep("]"));
        postfix.insertChoice(rule(ArrayRef.class).sep("[").ast(expr).sep("]"));
    }
}
