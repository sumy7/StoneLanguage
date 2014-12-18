package stone.ast;

import java.util.List;

import stone.evaluate.Environment;
import stone.evaluate.Evaluator;

public class Dot extends Postfix {
    public Dot(List<ASTree> c) {
        super(c);
    }

    public String name() {
        return ((ASTLeaf) child(0)).token().getText();
    }

    @Override
    public String toString() {
        return "." + name();
    }

    @Override
    public Object eval(Environment env, Evaluator eval, Object value) {
        return eval.eval(env, this, value);
    }
}
