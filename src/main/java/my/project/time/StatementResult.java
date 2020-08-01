package my.project.time;

public class StatementResult {
    private final String type;
    private final Object value;

    public String getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public StatementResult(String type, Object value) {
        this.type = type;
        this.value = value;
    }
}
