package am.data.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract interface DAO<T> {

	T modelFromResultSet(ResultSet resultSet) throws SQLException;

	T create(T t);

	List<T> readAll();

	T read(Integer id);

	T update(T t);

	Boolean delete(Integer id);

}
