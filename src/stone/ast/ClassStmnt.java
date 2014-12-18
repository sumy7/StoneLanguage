package stone.ast;

import java.util.List;

import stone.evaluate.Environment;
import stone.evaluate.Evaluator;

public class ClassStmnt extends ASTList {
    public ClassStmnt(List<ASTree> c) {
        super(c);
    }

    public String name() {
        return ((ASTLeaf) child(0)).token().getText();
    }

    public String superClass() {
        if (numChildren() < 3) {
            return null;
        } else {
            return ((ASTLeaf) child(1)).token().getText();
        }
    }

    public ClassBody body() {
        return (ClassBody) child(numChildren() - 1);
    }

    @Override
    public String toString() {
        String parent = superClass();
        if (parent == null) {
            parent = "*";
        }
        return "(class " + name() + " " + parent + " " + body() + ")";
    }

    @Override
    public Object eval(Environment env, Evaluator eval) {
        return eval.eval(env, this);
    }
}
