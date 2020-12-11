package bank.dao;

import java.util.List;

public interface DAO<E> {
    List<E> getAll();

    E getById(Long id);

    void update(E entity);

    void delete(Long id);

    void add(E entity);

}
