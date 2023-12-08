package DAO;

import java.util.List;

public abstract class ManagerDAO<T, ID> {

    public abstract void insert(T entity);

    public abstract void update(T entity);

    public abstract void delete(ID id);

    public abstract T selectById(ID id);

    public abstract List<T> selectAll();

    protected abstract List<T> selectBySQL(String sql, Object... args);
}
