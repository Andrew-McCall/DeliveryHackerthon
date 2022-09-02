package am.data.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import am.common.DBUtils;
import am.data.entites.Order;

public class OrderDAO implements DAO<Order> {

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		return new Order(resultSet.getLong("order_id"), resultSet.getString("description"),
				resultSet.getLong("address_id"));
	}

	@Override
	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Order read(Long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orders WHERE order_id = " + order_id.toString() + " LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders (description, address_id) VALUES (?, ?)");) {
			statement.setString(1, order.getDescription());
			statement.setLong(2, order.getAddress_id());
			statement.execute();
			return readLatest();
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Boolean update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE orders SET description = ?, address_id = ? WHERE order_id = ? LIMIT 1");) {
			statement.setString(1, order.getDescription());
			statement.setLong(2, order.getAddress_id());
			statement.setLong(3, order.getOrder_id());
			return (statement.executeUpdate() == 1);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public Boolean delete(Long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM orders WHERE order_id = ? LIMIT 1");) {
			statement.setLong(1, order_id);
			return (statement.executeUpdate() == 1);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return false;
	}

}
