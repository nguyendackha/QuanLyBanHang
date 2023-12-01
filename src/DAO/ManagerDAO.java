package DAO;

import Helper.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ManagerDAO<T, K> {

    protected abstract void insert(T entity);

    protected abstract void update(T entity);

    protected abstract void delete(K id);

    protected abstract T selectById(K id);

    protected abstract List<T> selectAll();

    protected abstract List<T> selectBySQL(String sql, Object... args);

    // Các phương thức chung khác nếu cần

}
