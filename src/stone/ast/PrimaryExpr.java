package stone.ast;

import java.util.List;

import stone.evaluate.Environment;
import stone.evaluate.Evaluator;

public class PrimaryExpr extends ASTList {
    public PrimaryExpr(List<ASTree> c) {
        super(c);
    }

    public static ASTree create(List<ASTree> c) {
        return c.size() == 1 ? c.get(0) : new PrimaryExpr(c);
    }

    public ASTree operand() {
        return child(0);
    }

    public Postfix postfix(int nest) {
        return (Postfix) child(numChildren() - nest - 1);
    }

    public boolean hasPostfix(int nest) {
        return numChildren() - nest > 1;
    }

    @Override
    public Object eval(Environment env, Evaluator eval) {
        return eval.eval(env, this);
    }
}
