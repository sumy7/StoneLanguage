package stone.ast;

import java.util.ArrayList;
import java.util.Iterator;

import stone.evaluate.Environment;
import stone.evaluate.Evaluator;
import stone.token.Token;

public class ASTLeaf extends ASTree {

    private static ArrayList<ASTree> empty = new ArrayList<ASTree>();
    protected Token token;

    public ASTLeaf(Token t) {
        this.token = t;
    }

    @Override
    public ASTree child(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public Iterator<ASTree> children() {
        return empty.iterator();
    }

    @Override
    public String location() {
        return "at line " + token.getLineNumber();
    }

    public Token token() {
        return token;
    }

    @Override
    public String toString() {
        return token.getText();
    }

    @Override
    public Object eval(Environment env, Evaluator eval) {
        return eval.eval(env, this);
    }

}
