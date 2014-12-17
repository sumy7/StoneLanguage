package stone.ast;

import java.util.List;

import stone.evaluate.Environment;
import stone.evaluate.Evaluator;

public class NullStmnt extends ASTList {
    public NullStmnt(List<ASTree> c) {
        super(c);
    }
    
    @Override
    public Object eval(Environment env, Evaluator eval) {
        return eval.eval(env, this);
    }
}
