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
import am.data.entites.Routestop;

public class RoutestopDAO implements DAO<Routestop> {

	@Override
	public Routestop modelFromResultSet(ResultSet resultSet) throws SQLException {
		return new Routestop(resultSet.getLong("routestop_id"), resultSet.getLong("route_id"),
				resultSet.getInt("stop_number"), resultSet.getLong("order_id"));
	}

	@Override
	public Routestop readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM routestops ORDER BY routestop_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Routestop> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM routestops");) {
			List<Routestop> routestops = new ArrayList<>();
			while (resultSet.next()) {
				routestops.add(modelFromResultSet(resultSet));
			}
			return routestops;
		} catch (SQLException e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Routestop read(Long routestop_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT * FROM routestops WHERE routestop_id = " + routestop_id.toString() + " LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Routestop create(Routestop routestop) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO routestops (route_id, stop_number, order_id) VALUES (?, ?, ?)");) {
			statement.setLong(1, routestop.getRoute_id());
			statement.setInt(2, routestop.getStop_number());
			statement.setLong(3, routestop.getOrder_id());
			statement.execute();
			return readLatest();
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Boolean update(Routestop routestop) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE routestops SET route_id = ?, stop_number = ?, order_id = ? WHERE routestop_id = ? LIMIT 1");) {
			statement.setLong(1, routestop.getRoute_id());
			statement.setInt(2, routestop.getStop_number());
			statement.setLong(3, routestop.getOrder_id());
			statement.setLong(4, routestop.getRoutestop_id());
			return (statement.executeUpdate() == 1);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public Boolean delete(Long routestop_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM routestops WHERE routestop_id = ? LIMIT 1");) {
			statement.setLong(1, routestop_id);
			return (statement.executeUpdate() == 1);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return false;
	}

}
