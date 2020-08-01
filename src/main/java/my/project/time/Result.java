package my.project.time;

public class Result {
    static final Result NONE = new Result(Type.NONE, -1);
    private final Type type;
    private final Object value;

    public Type getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public Result(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Result{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
