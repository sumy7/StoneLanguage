package stone.parser;

import static stone.parser.Parser.rule;
import stone.ast.Arguments;
import stone.ast.DefStmnt;
import stone.ast.ParameterList;

public class FunParser extends BasicParser {
    Parser param = rule().identifier(reserved);
    Parser params = rule(ParameterList.class).ast(param).repeat(
            rule().sep(",").ast(param));
    Parser paramList = rule().sep("(").maybe(params).sep(")");

    Parser def = rule(DefStmnt.class).sep("def").identifier(reserved)
            .ast(paramList).ast(block);
    Parser args = rule(Arguments.class).ast(expr).repeat(
            rule().sep(",").ast(expr));
    Parser postfix = rule().sep("(").maybe(args).sep(")");

    public FunParser() {
        reserved.add(")");
        primary.repeat(postfix);
        simple.option(args);
        program.insertChoice(def);
    }
}
