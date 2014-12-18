package stone.ast;

import java.util.List;

import stone.evaluate.Environment;
import stone.evaluate.Evaluator;

public class ArrayRef extends Postfix {

    public ArrayRef(List<ASTree> c) {
        super(c);
    }

    public ASTree index() {
        return child(0);
    }

    @Override
    public String toString() {
        return "[" + index() + "]";
    }

    @Override
    public Object eval(Environment env, Evaluator eval, Object value) {
        return eval.eval(env, this, value);
    }

}
