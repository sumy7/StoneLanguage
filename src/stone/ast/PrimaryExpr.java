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
        // System.out.println(this.toString());
        // System.out.println(numChildren());
        // System.out.println(child(0));
        // System.out.println(child(1));
        // System.out.println(nest);
        return (Postfix) child(nest);
    }

    public boolean hasPostfix(int nest) {
        return numChildren() - nest > 1;
    }

    @Override
    public Object eval(Environment env, Evaluator eval) {
        return eval.eval(env, this);
    }
}
