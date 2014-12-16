package stone.parser;

import static stone.parser.Parser.rule;

import java.util.HashSet;

import stone.ast.ASTree;
import stone.ast.BinaryExpr;
import stone.ast.BlockStmnt;
import stone.ast.IfStmnt;
import stone.ast.Name;
import stone.ast.NegativeExpr;
import stone.ast.NullStmnt;
import stone.ast.NumberLiteral;
import stone.ast.PrimaryExpr;
import stone.ast.StringLiteral;
import stone.ast.WhileStmnt;
import stone.exception.ParseException;
import stone.lexer.Lexer;
import stone.parser.Parser.Operators;
import stone.token.Token;

public class BasicParser {
    HashSet<String> reserved = new HashSet<String>();
    Operators operators = new Operators();
    Parser expr0 = rule();

    Parser primary = rule(PrimaryExpr.class).or(
            rule().sep("(").ast(expr0).sep(")"),
            rule().number(NumberLiteral.class),
            rule().identifier(Name.class, reserved),
            rule().string(StringLiteral.class));

    Parser factor = rule().or(rule(NegativeExpr.class).sep("-").ast(primary),
            primary);

    Parser expr = expr0.expression(BinaryExpr.class, factor, operators);

    Parser statement0 = rule();

    Parser block = rule(BlockStmnt.class).sep("{").option(statement0)
            .repeat(rule().sep(";", Token.EOL).option(statement0)).sep("}");

    Parser simple = rule(PrimaryExpr.class).ast(expr);

    Parser statment = statement0.or(rule(IfStmnt.class).sep("if").ast(expr)
            .ast(block).option(rule().sep("else").ast(block)),
            rule(WhileStmnt.class).sep("while").ast(expr).ast(block), simple);

    Parser program = rule().or(statment, rule(NullStmnt.class)).sep(";",
            Token.EOL);

    public BasicParser() {
        reserved.add(";");
        reserved.add("}");
        reserved.add(Token.EOL);

        operators.add("=", 1, Operators.RIGHT);
        operators.add("==", 2, Operators.LEFT);
        operators.add(">", 2, Operators.LEFT);
        operators.add("<", 2, Operators.LEFT);
        operators.add("+", 3, Operators.LEFT);
        operators.add("-", 3, Operators.LEFT);
        operators.add("*", 4, Operators.LEFT);
        operators.add("/", 4, Operators.LEFT);
        operators.add("%", 4, Operators.LEFT);
    }

    public ASTree parser(Lexer lexer) throws ParseException {
        return program.parse(lexer);
    }
}
