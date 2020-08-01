package my.project.time;

import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends my.project.time.TimeExprBaseVisitor<StatementResult> {
    private final Map<String, StatementResult> memory = new HashMap<>();

    @Override
    public StatementResult visitDate(my.project.time.TimeExprParser.DateContext ctx) {

        throw new UnsupportedOperationException();
    }

    @Override
    public StatementResult visitDatetime(my.project.time.TimeExprParser.DatetimeContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatementResult visitParens(my.project.time.TimeExprParser.ParensContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatementResult visitBlank(my.project.time.TimeExprParser.BlankContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatementResult visitAddSub(my.project.time.TimeExprParser.AddSubContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatementResult visitTime(my.project.time.TimeExprParser.TimeContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatementResult visitId(my.project.time.TimeExprParser.IdContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatementResult visitProg(my.project.time.TimeExprParser.ProgContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatementResult visitInt(my.project.time.TimeExprParser.IntContext ctx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatementResult visitPrintExpr(my.project.time.TimeExprParser.PrintExprContext ctx) {

        throw new UnsupportedOperationException();
    }

    @Override
    public StatementResult visitAssign(my.project.time.TimeExprParser.AssignContext ctx) {
        String id = ctx.ID().getText(); // id is left-hand side of '='
        final StatementResult value = visit(ctx.expr()); // compute value of expression on right
        memory.put(id, value); // store it in our memory return value;
        return value;
    }
}
