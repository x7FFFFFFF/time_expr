package my.project.time;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends my.project.time.TimeExprBaseVisitor<Result> {
    private final Map<String, Result> memory = new HashMap<>();

    @Override
    public Result visitDate(my.project.time.TimeExprParser.DateContext ctx) {
        final String text = ctx.DATE().getText();
        return Type.DATE.parse(text);
    }

    @Override
    public Result visitDatetime(my.project.time.TimeExprParser.DatetimeContext ctx) {
        final String text = ctx.DATETIME().getText();
        return Type.DATETIME.parse(text);
    }

    @Override
    public Result visitTime(my.project.time.TimeExprParser.TimeContext ctx) {
        final String text = ctx.TIME().getText();
        return Type.TIME.parse(text);
    }

    @Override
    public Result visitInterval(my.project.time.TimeExprParser.IntervalContext ctx) {
        final String text = ctx.INTERVAL().getText();
        return Type.INTERVAL.parse(text);
    }

    @Override
    public Result visitParens(my.project.time.TimeExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    /*@Override
    public Result visitBlank(my.project.time.TimeExprParser.BlankContext ctx) {
        throw new UnsupportedOperationException();
    }*/

    @Override
    public Result visitAddSub(my.project.time.TimeExprParser.AddSubContext ctx) {
        Result left = visit(ctx.expr(0));
        Result right = visit(ctx.expr(1));
        final int opType = ctx.op.getType(); //TimeExprParser.ADD, TimeExprParser.SUB
    }


    @Override
    public Result visitId(my.project.time.TimeExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) return memory.get(id);
        throw new IllegalArgumentException("Variable not defined: " + id);
    }

   /* @Override
    public Result visitProg(my.project.time.TimeExprParser.ProgContext ctx) {
        throw new UnsupportedOperationException();
    }*/

    @Override
    public Result visitInt(my.project.time.TimeExprParser.IntContext ctx) {
        return new Result(Type.INT, Integer.valueOf(ctx.INT().getText()));
    }

    @Override
    public Result visitPrintExpr(my.project.time.TimeExprParser.PrintExprContext ctx) {
        Result value = visit(ctx.expr());
        System.out.println(value);
        return Result.NONE;
    }

    @Override
    public Result visitAssign(my.project.time.TimeExprParser.AssignContext ctx) {
        String id = ctx.ID().getText(); // id is left-hand side of '='
        final Result value = visit(ctx.expr()); // compute value of expression on right
        memory.put(id, value); // store it in our memory return value;
        return value;
    }
}
