package stone.ast;

import java.util.List;

import stone.evaluate.Environment;
import stone.evaluate.Evaluator;

public class ClassBody extends ASTList {

    public ClassBody(List<ASTree> c) {
        super(c);
    }

    @Override
    public Object eval(Environment env, Evaluator eval) {
        return eval.eval(env, this);
    }
}
