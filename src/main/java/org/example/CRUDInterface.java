package org.example;

import java.util.Optional;

public interface CRUDInterface<T>  {

    void create(T t);
    Optional<T> read(String id);
    void update(T t);
    void delete(String id);
}
