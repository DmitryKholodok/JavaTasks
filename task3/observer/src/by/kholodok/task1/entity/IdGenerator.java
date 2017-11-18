package by.kholodok.task1.entity;

/**
 * Created by dmitrykholodok on 11/15/17
 */

public class IdGenerator {

    private static long  id = 0;
    private IdGenerator() {}

    public static long getId() {
        return id++;
    }
}
