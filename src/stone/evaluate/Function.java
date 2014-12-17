package stone.evaluate;

import stone.ast.BlockStmnt;
import stone.ast.ParameterList;

public class Function {
    protected ParameterList parameters;
    protected BlockStmnt body;
    protected Environment env;

    public Function(ParameterList prarmeters, BlockStmnt body, Environment env) {
        this.parameters = prarmeters;
        this.body = body;
        this.env = env;
    }

    public ParameterList prarmeters() {
        return this.parameters;
    }

    public BlockStmnt body() {
        return this.body;
    }

    public Environment makeEnv() {
        return new NestedEnv(env);
    }

    @Override
    public String toString() {
        return "<fun:" + hashCode() + ">";
    }
}
