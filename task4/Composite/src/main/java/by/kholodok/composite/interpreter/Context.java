package by.kholodok.composite.interpreter;

import java.util.ArrayDeque;

/**
 * Created by dmitrykholodok on 11/18/17
 */

public class Context {

    private ArrayDeque<Integer> contextValues = new ArrayDeque<>();

    public Integer pop() {
        return contextValues.pop();
    }

    public void push(Integer numberValue) {
        this.contextValues.push(numberValue);
    }

}
