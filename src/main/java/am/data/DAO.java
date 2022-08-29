package am.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract interface DAO<T> {

	T modelFromResultSet(ResultSet resultSet) throws SQLException;

	T readLatest();

	T create(T t);

	List<T> readAll();

	T read(Long id);

	Boolean update(T t);

	Boolean delete(Long id);

}
