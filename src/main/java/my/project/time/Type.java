package my.project.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.BiFunction;

public enum Type implements IType {
    DATETIME {
        @Override
        public Result parse(String text) {
            return new Result(this, LocalDateTime.parse(text));
        }
    },
    DATE {
        @Override
        public Result parse(String text) {
            return new Result(this, LocalDate.parse(text));
        }
    },
    TIME {
        @Override
        public Result parse(String text) {
            return new Result(this, LocalTime.parse(text));
        }
    },
    INTERVAL {
        @Override
        public Result parse(String text) {
            return new Result(this, Duration.parse(text));
        }
    },
    ID {
        @Override
        public Result parse(String text) {
            return new Result(this, text);
        }
    },
    INT {
        @Override
        public Result parse(String text) {
            return new Result(this, Integer.valueOf(text));
        }
    },
    NONE {
        @Override
        public Result parse(String text) {
            return Result.NONE;
        }
    };


}
