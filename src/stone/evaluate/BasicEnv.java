package stone.evaluate;

import java.util.HashMap;

import stone.exception.StoneException;

public class BasicEnv implements Environment {

    protected HashMap<String, Object> values;

    public BasicEnv() {
        values = new HashMap<String, Object>();
        values.put("version", "0.2");
    }

    @Override
    public void put(String name, Object value) {
        values.put(name, value);
    }

    @Override
    public Object get(String name) {
        return values.get(name);
    }

    @Override
    public void putNew(String name, Object value) {
        put(name, value);
    }

    @Override
    public Environment where(String name) {
        if (values.get(name) != null) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public void setOuter(Environment e) {
        throw new StoneException("Outer Environment can't set outer");
    }

}
