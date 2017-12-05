package by.kholodok.composite.interpreter;

/**
 * Created by dmitrykholodok on 11/18/17
 */

@FunctionalInterface
public interface AbstractMathExpression<Context> {
    void interpret(Context context);
}
