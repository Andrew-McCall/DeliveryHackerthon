package am.data.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import am.common.DBUtils;
import am.data.entites.Address;

public class AddressDAO implements DAO<Address> {

	@Override
	public Address modelFromResultSet(ResultSet resultSet) throws SQLException {
		return new Address(resultSet.getLong("address_id"), resultSet.getString("address_line_one"),
				resultSet.getString("address_line_two"), resultSet.getString("city"), resultSet.getString("postcode"));
	}

	@Override
	public Address readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM addresses ORDER BY address_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Address> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM addresses");) {
			List<Address> addresses = new ArrayList<>();
			while (resultSet.next()) {
				addresses.add(modelFromResultSet(resultSet));
			}
			return addresses;
		} catch (SQLException e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Address read(Long address_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT * FROM addresses WHERE address_id = " + address_id.toString() + " LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Address create(Address address) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO addresses (address_line_one, address_line_two, city, postcode) VALUES (?, ?, ?, ?)");) {
			statement.setString(1, address.getAddress_line_one());
			statement.setString(2, address.getAddress_line_two());
			statement.setString(3, address.getCity());
			statement.setString(4, address.getPostcode());
			statement.execute();
			return readLatest();
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Boolean update(Address address) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE addresses SET address_line_one = ?, address_line_two = ?, city = ?, postcode = ? WHERE address_id = ? LIMIT 1");) {
			statement.setString(1, address.getAddress_line_one());
			statement.setString(2, address.getAddress_line_two());
			statement.setString(3, address.getCity());
			statement.setString(4, address.getPostcode());
			statement.setLong(5, address.getAddress_id());
			return (statement.executeUpdate() == 1);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public Boolean delete(Long address_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM addresses WHERE address_id = ? LIMIT 1");) {
			statement.setLong(1, address_id);
			return (statement.executeUpdate() == 1);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return false;
	}

}
