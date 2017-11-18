package by.kholodok.task1.creator;

import by.kholodok.task1.entity.Entity;

import java.util.List;

public interface Creator {
    List<Entity> create(List<String> lines);
    Entity create (String str);
}
