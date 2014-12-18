package stone.evaluate;

public class BuildInConst {

    public Environment environment(Environment env) {
        appendEnv(env);
        return env;
    }

    protected void appendEnv(Environment env) {
        env.put("VERSION", "0.3");
    }
}
