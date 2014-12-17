package stone.evaluate;

import java.util.HashMap;

public class BasicEnv implements Environment {

    protected HashMap<String, Object> values;

    public BasicEnv() {
        values = new HashMap<String, Object>();
        values.put("version", "0.1");
    }

    @Override
    public void put(String name, Object value) {
        values.put(name, value);
    }

    @Override
    public Object get(String name) {
        return values.get(name);
    }

}
