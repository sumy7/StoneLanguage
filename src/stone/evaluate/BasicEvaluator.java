package stone.evaluate;

import stone.ast.ASTLeaf;
import stone.ast.ASTList;
import stone.ast.ASTree;
import stone.ast.Arguments;
import stone.ast.BinaryExpr;
import stone.ast.BlockStmnt;
import stone.ast.DefStmnt;
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
import stone.exception.StoneException;

public class BasicEvaluator implements Evaluator {

    @Override
    public Object eval(Environment env, ASTList ast) {
        throw new StoneException("cannot eval: " + ast.toString(), ast);
    }

    @Override
    public Object eval(Environment env, ASTLeaf ast) {
        throw new StoneException("cannot eval: " + ast.toString(), ast);
    }

    @Override
    public Object eval(Environment env, NumberLiteral ast) {
        return ast.value();
    }

    @Override
    public Object eval(Environment env, StringLiteral ast) {
        return ast.value();
    }

    @Override
    public Object eval(Environment env, Name ast) {
        Object value = env.get(ast.name());
        if (value == null) {
            throw new StoneException("undefined name: " + ast.name(), ast);

        } else {
            return value;
        }
    }

    @Override
    public Object eval(Environment env, NegativeExpr ast) {
        Object v = ast.operand().eval(env, this);
        if (v instanceof Integer) {
            return new Integer(-((Integer) v)).intValue();
        } else {
            throw new StoneException("bad type for -", ast);
        }
    }

    public static final int TRUE = 1;
    public static final int FALSE = 0;

    @Override
    public Object eval(Environment env, BinaryExpr ast) {
        String op = ast.operator();
        if ("=".equals(op)) {
            Object right = ast.right().eval(env, this);
            ASTree l = ast.left();
            if (l instanceof Name) {
                env.put(((Name) l).name(), right);
                return right;
            } else {
                throw new StoneException("bad assignment", ast);
            }
        } else {
            Object left = ast.left().eval(env, this);
            Object right = ast.right().eval(env, this);
            if (left instanceof Integer && right instanceof Integer) {
                int a = ((Integer) left).intValue();
                int b = ((Integer) right).intValue();
                if (op.equals("+")) {
                    return a + b;
                }
                if (op.equals("-")) {
                    return a - b;
                }
                if (op.equals("*")) {
                    return a * b;
                }
                if (op.equals("/")) {
                    return a / b;
                }
                if (op.equals("%")) {
                    return a % b;
                }
                if (op.equals("==")) {
                    return a == b ? TRUE : FALSE;
                }
                if (op.equals(">")) {
                    return a > b ? TRUE : FALSE;
                }
                if (op.equals("<")) {
                    return a < b ? TRUE : FALSE;
                }
                throw new StoneException("bad operator", ast);
            } else {
                if (op.equals("+")) {
                    return String.valueOf(left) + String.valueOf(right);
                } else if (op.equals("==")) {
                    if (left == null) {
                        return right == null ? TRUE : FALSE;
                    } else {
                        return left.equals(right) ? TRUE : FALSE;
                    }
                } else {
                    throw new StoneException("bad type", ast);
                }
            }

        }
    }

    @Override
    public Object eval(Environment env, BlockStmnt ast) {
        Object result = 0;
        for (ASTree t : ast) {
            if (!(t instanceof NullStmnt)) {
                result = t.eval(env, this);
            }
        }
        return result;
    }

    @Override
    public Object eval(Environment env, IfStmnt ast) {
        Object c = ast.condition().eval(env, this);
        if (c instanceof Integer && ((Integer) c).intValue() != FALSE) {
            return ast.thenBlock().eval(env, this);
        } else {
            ASTree b = ast.elseBlock();
            if (b == null) {
                return 0;
            } else {
                return b.eval(env, this);
            }
        }
    }

    @Override
    public Object eval(Environment env, WhileStmnt ast) {
        Object result = 0;
        for (;;) {
            Object c = ast.condition().eval(env, this);
            if (c instanceof Integer && ((Integer) c).intValue() == FALSE) {
                return result;
            } else {
                result = ast.body().eval(env, this);
            }
        }
    }

    @Override
    public Object eval(Environment env, NullStmnt ast) {
        throw new StoneException("cannot eval: " + ast.toString(), ast);
    }

    @Override
    public Object eval(Environment env, PrimaryExpr ast) {
        Object res = ast.operand().eval(env, this);
        int n = ast.numChildren();
        for (int i = 1; i < n; i++) {
            res = ast.postfix(i).eval(env, this, res);
        }
        return res;
    }

    @Override
    public Object eval(Environment env, DefStmnt ast) {
        env.putNew(ast.name(), new Function(ast.parameters(), ast.body(), env));
        return ast.name();
    }

    @Override
    public Object eval(Environment env, ParameterList ast) {
        throw new StoneException("cannot eval in this method.");
    }

    @Override
    public Object eval(Environment env, Postfix ast) {
        throw new StoneException("cannot eval: " + ast.toString(), ast);
    }

    @Override
    public Object eval(Environment env, Arguments ast) {
        throw new StoneException("cannot eval in this method.");
    }

    @Override
    public void eval(Environment env, ParameterList ast, int index, Object value) {
        env.putNew(ast.name(index), value);
    }

    @Override
    public Object eval(Environment env, Arguments ast, Object value) {
        if (value instanceof NativeFunction) {
            NativeFunction func = (NativeFunction) value;
            int nparams = func.numOfParameters();
            if (ast.size() != nparams) {
                throw new StoneException("bad number of arguments", ast);
            }
            Object[] args = new Object[nparams];
            int num = 0;
            for (ASTree a : ast) {
                args[num++] = a.eval(env, this);
            }
            return func.invoke(args, ast);
        }

        if (!(value instanceof Function)) {
            throw new StoneException("bad function", ast);
        }
        Function func = (Function) value;
        ParameterList params = func.prarmeters();
        if (ast.size() != params.size()) {
            throw new StoneException("bad number of arguments", ast);
        }
        Environment newEnv = func.makeEnv();
        int num = 0;
        for (ASTree a : ast) {
            params.eval(newEnv, this, num++, a.eval(env, this));
        }
        return func.body().eval(newEnv, this);
    }

    @Override
    public Object eval(Environment env, Fun ast) {
        return new Function(ast.parameters(), ast.body(), env);
    }

}
