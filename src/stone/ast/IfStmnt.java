package stone.ast;

import java.util.List;

import stone.evaluate.Environment;
import stone.evaluate.Evaluator;

public class IfStmnt extends ASTList {
    public IfStmnt(List<ASTree> c) {
        super(c);
    }

    public ASTree condition() {
        return child(0);
    }

    public ASTree thenBlock() {
        return child(1);
    }

    public ASTree elseBlock() {
        return numChildren() > 2 ? child(2) : null;
    }

    @Override
    public String toString() {
        return "(if " + condition() + " " + thenBlock() + " else" + elseBlock()
                + ")";
    }
    
    @Override
    public Object eval(Environment env, Evaluator eval) {
        return eval.eval(env, this);
    }
}
