package bank.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface DAO<E> {
    public List<E> getAll();

    public E getEntityById(Long id);

    public void update(E entity);

    public void delete(Long id);

    public void create(E entity);

}
