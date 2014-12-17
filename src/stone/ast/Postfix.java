package stone.ast;

import java.util.List;

import stone.evaluate.Environment;
import stone.evaluate.Evaluator;

public abstract class Postfix extends ASTList {

    public Postfix(List<ASTree> c) {
        super(c);
    }

    public abstract Object eval(Environment env, Evaluator eval, Object value);

    @Override
    public Object eval(Environment env, Evaluator eval) {
        return eval.eval(env, this);
    }
}
