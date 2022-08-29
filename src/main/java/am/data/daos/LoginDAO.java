package am.data.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import am.common.DBUtils;
import am.data.DAO;
import am.data.entites.Login;

public class LoginDAO implements DAO<Login> {

	@Override
	public Login modelFromResultSet(ResultSet resultSet) throws SQLException {
		return new Login(resultSet.getLong("login_id"), resultSet.getString("username"),
				resultSet.getString("password"), resultSet.getBoolean("isManager"));
	}

	@Override
	public Login readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM logins ORDER BY login_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Login> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM logins");) {
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
	public Login read(Long login_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM logins WHERE login_id = ?");) {
			statement.setLong(1, login_id);
			return modelFromResultSet(statement.executeQuery());
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Login create(Login login) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO logins (username, password, isManager) VALUES (?, ?, ?)");) {
			statement.setString(1, login.getUsername());
			statement.setString(2, login.getPassword());
			statement.setBoolean(3, login.getIsManager());
			statement.execute();
			return readLatest();
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Login update(Login login) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE logins SET username = ?, password = ?, isManager = ? WHERE login_id = ?");) {
			statement.setString(1, login.getUsername());
			statement.setString(2, login.getPassword());
			statement.setBoolean(3, login.getIsManager());
			statement.setLong(4, login.getLogin_id());
			return read(login.getLogin_id());
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Boolean delete(Long login_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM logins WHERE login_id = ?");) {
			statement.setLong(1, login_id);
			return (statement.executeUpdate() == 1);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return false;
	}

}
