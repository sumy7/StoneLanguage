package stone.parser;

import stone.ast.Fun;
import static stone.parser.Parser.rule;

public class ClosureParser extends FunParser {
    public ClosureParser() {
        primary.insertChoice(rule(Fun.class).sep("fun").ast(paramList)
                .ast(block));
    }
}
