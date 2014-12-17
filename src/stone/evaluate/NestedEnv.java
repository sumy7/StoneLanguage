package stone.evaluate;

import java.util.HashMap;

public class NestedEnv implements Environment {
    protected HashMap<String, Object> values;
    protected Environment outer;

    public NestedEnv() {
        this(null);
    }

    public NestedEnv(Environment e) {
        values = new HashMap<String, Object>();
        outer = e;
    }

    @Override
    public void setOuter(Environment e) {
        outer = e;
    }

    @Override
    public void putNew(String name, Object value) {
        values.put(name, value);
    }

    @Override
    public void put(String name, Object value) {
        Environment e = where(name);
        if (e == null) {
            e = this;
        }
        e.putNew(name, value);
    }

    @Override
    public Environment where(String name) {
        if (values.get(name) != null) {
            return this;

        } else if (outer == null) {
            return null;
        } else {
            return outer.where(name);
        }
    }

    @Override
    public Object get(String name) {
        Object v = values.get(name);
        if (v == null && outer != null) {
            return outer.get(name);
        } else {
            return v;
        }
    }

}
