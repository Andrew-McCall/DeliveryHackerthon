package am.data.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import am.common.DBUtils;
import am.data.entites.Login;

public class LoginDAO implements DAO<Login> {

	@Override
	public List<Login> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Logins");) {
			List<Login> logins = new ArrayList<>();
			while (resultSet.next()) {
				logins.add(modelFromResultSet(resultSet));
			}
			return logins;
		} catch (SQLException e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Login read(Integer id) {
		return null;
	}

	@Override
	public Login create(Login login) {
		return null;
	}

	@Override
	public Login update(Login login) {
		return null;
	}

	@Override
	public Boolean delete(Integer id) {
		return false;
	}

	@Override
	public Login modelFromResultSet(ResultSet resultSet) throws SQLException {
		return null;
	}

}
