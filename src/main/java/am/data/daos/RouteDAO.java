package am.data.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import am.common.DBUtils;
import am.data.entites.Route;

public class RouteDAO implements DAO<Route> {

	@Override
	public Route modelFromResultSet(ResultSet resultSet) throws SQLException {
		return new Route(resultSet.getLong("route_id"), resultSet.getInt("distance"), resultSet.getInt("stop_count"),
				resultSet.getLong("login_id"));
	}

	@Override
	public Route readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM routes ORDER BY route_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Route> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM routes");) {
			List<Route> routes = new ArrayList<>();
			while (resultSet.next()) {
				routes.add(modelFromResultSet(resultSet));
			}
			return routes;
		} catch (SQLException e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}

	public List<Route> readByDriverId(Long login_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM routes WHERE login_id = ?");) {
			statement.setLong(1, login_id);
			ResultSet resultSet = statement.executeQuery();
			List<Route> routes = new ArrayList<>();
			while (resultSet.next()) {
				routes.add(modelFromResultSet(resultSet));
			}
			return routes;
		} catch (SQLException e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Route read(Long route_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM routes WHERE route_id = " + route_id.toString() + " LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Route create(Route route) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO routes (distance, stop_count, login_id) VALUES (?, ?, ?)");) {
			statement.setInt(1, route.getDistance());
			statement.setInt(2, route.getStop_count());
			statement.setLong(3, route.getLogin_id());
			statement.execute();
			return readLatest();
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Boolean update(Route route) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE addresses SET distance = ?, stop_count = ?, login_id = ? WHERE route_id = ? LIMIT 1");) {
			statement.setInt(1, route.getDistance());
			statement.setInt(2, route.getStop_count());
			statement.setLong(3, route.getLogin_id());
			statement.setLong(5, route.getRoute_id());
			return (statement.executeUpdate() == 1);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public Boolean delete(Long route_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM routes WHERE route_id = ? LIMIT 1");) {
			statement.setLong(1, route_id);
			return (statement.executeUpdate() == 1);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return false;
	}

}
