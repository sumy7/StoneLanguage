package stone.evaluate;

import stone.ast.ASTLeaf;
import stone.ast.ASTList;
import stone.ast.BinaryExpr;
import stone.ast.BlockStmnt;
import stone.ast.IfStmnt;
import stone.ast.Name;
import stone.ast.NegativeExpr;
import stone.ast.NullStmnt;
import stone.ast.NumberLiteral;
import stone.ast.PrimaryExpr;
import stone.ast.StringLiteral;
import stone.ast.WhileStmnt;

public interface Evaluator {
    public Object eval(Environment env, ASTList ast);

    public Object eval(Environment env, ASTLeaf ast);

    public Object eval(Environment env, NumberLiteral ast);

    public Object eval(Environment env, StringLiteral ast);

    public Object eval(Environment env, Name ast);

    public Object eval(Environment env, NegativeExpr ast);

    public Object eval(Environment env, BinaryExpr ast);

    public Object eval(Environment env, BlockStmnt ast);

    public Object eval(Environment env, IfStmnt ast);

    public Object eval(Environment env, WhileStmnt ast);

    public Object eval(Environment env, NullStmnt ast);

    public Object eval(Environment env, PrimaryExpr ast);
}
