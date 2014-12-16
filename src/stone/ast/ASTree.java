package stone.ast;

import java.util.Iterator;

import stone.evaluate.Environment;
import stone.evaluate.Evaluator;

public abstract class ASTree implements Iterable<ASTree> {
    public abstract ASTree child(int i);

    public abstract int numChildren();

    public abstract Iterator<ASTree> children();

    public abstract String location();

    public Iterator<ASTree> iterator() {
        return children();
    }

    public abstract Object eval(Environment env, Evaluator eval);
}
