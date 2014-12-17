package stone.ast;

import java.util.List;

import stone.evaluate.Environment;
import stone.evaluate.Evaluator;

public class Arguments extends Postfix {

    public Arguments(List<ASTree> c) {
        super(c);
    }

    public int size() {
        return numChildren();
    }

    @Override
    public Object eval(Environment env, Evaluator eval) {
        return eval.eval(env, this);
    }

    @Override
    public Object eval(Environment env, Evaluator eval, Object value) {
        return eval.eval(env, this, value);
    }

}
