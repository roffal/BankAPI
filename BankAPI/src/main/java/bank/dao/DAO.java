package bank.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DAO<T> {

    List<T> getAll();

    T getById(long id);

    void save(T t);

    void update(T t);

    void delete(T t);
}