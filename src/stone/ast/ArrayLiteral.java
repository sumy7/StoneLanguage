package stone.ast;

import java.util.List;

import stone.evaluate.Environment;
import stone.evaluate.Evaluator;

public class ArrayLiteral extends ASTList {

    public ArrayLiteral(List<ASTree> list) {
        super(list);
    }

    public int size() {
        return numChildren();
    }

    @Override
    public Object eval(Environment env, Evaluator eval) {
        return eval.eval(env, this);
    }
}
