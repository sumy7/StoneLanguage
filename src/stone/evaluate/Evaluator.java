package stone.evaluate;

import stone.ast.ASTLeaf;
import stone.ast.ASTList;
import stone.ast.Arguments;
import stone.ast.ArrayLiteral;
import stone.ast.ArrayRef;
import stone.ast.BinaryExpr;
import stone.ast.BlockStmnt;
import stone.ast.ClassBody;
import stone.ast.ClassStmnt;
import stone.ast.DefStmnt;
import stone.ast.Dot;
import stone.ast.Fun;
import stone.ast.IfStmnt;
import stone.ast.Name;
import stone.ast.NegativeExpr;
import stone.ast.NullStmnt;
import stone.ast.NumberLiteral;
import stone.ast.ParameterList;
import stone.ast.Postfix;
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

    public Object eval(Environment env, DefStmnt ast);

    public void eval(Environment env, ParameterList ast, int index, Object value);

    public Object eval(Environment env, ParameterList ast);

    public Object eval(Environment env, Postfix ast);

    public Object eval(Environment env, Arguments ast);

    public Object eval(Environment env, Arguments ast, Object value);

    public Object eval(Environment env, Fun ast);

    public Object eval(Environment env, ClassBody ast);

    public Object eval(Environment env, ClassStmnt ast);

    public Object eval(Environment env, Dot ast, Object value);

    public Object eval(Environment env, ArrayLiteral ast);

    public Object eval(Environment env, ArrayRef ast, Object value);
}
