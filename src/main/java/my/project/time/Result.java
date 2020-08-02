package my.project.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.BiFunction;

public class Result {
    static final Result NONE = new Result(Type.NONE, -1);
    private final Type type;
    private final Object value;

    public Type getType() {
        return type;
    }

    public <T> T getValue(Class<T> cls) {
        return cls.cast(value);
    }

    public Result(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    private final static BiFunction<Result, Result, Result>[][][] TRANSITIONS;
    private static final BiFunction<Result, Result, Result> UNSUPPORTED = (l, r) -> {
        throw new UnsupportedOperationException();
    };

    static {
        TRANSITIONS = new BiFunction[][][]{
                {  //ADD
                        {  //  left DATETIME
                                (l, r) -> UNSUPPORTED,


                        }, //  left DATETIME
                        {}, //  left DATE
                        {}, //  left TIME
                        {}, //  left INTERVAL
                        {}, //  left ID
                        {}, //  left INT
                        {}, //  left NONE

                },
                { //SUB
                        { //left DATETIME
                                ( l,  r) -> new Result(Type.INTERVAL, Duration.between(((Result)l).getValue(LocalDateTime.class), ((Result)l).getValue(LocalDateTime.class))) //right DATETIME
                        }, //  left DATETIME
                        {}, //  left DATE
                        {}, //  left TIME
                        {}, //  left INTERVAL
                        {}, //  left ID
                        {}, //  left INT
                        {}, //  left NONE
                }
        };


    }


    public Result apply(int opCode, Result right) {
        return TRANSITIONS
                [opCode - 10]
                [type.ordinal()]
                [right.getType().ordinal()]
                .apply(this, right);
    }


    @Override
    public String toString() {
        return "Result{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
