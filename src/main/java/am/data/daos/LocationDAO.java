package am.data.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import am.common.DBUtils;
import am.data.entites.Location;

public class LocationDAO implements DAO<Location> {

	@Override
	public Location modelFromResultSet(ResultSet resultSet) throws SQLException {
		return new Location(resultSet.getLong("location_id"), resultSet.getString("postcode"),
				resultSet.getDouble("longitude"), resultSet.getDouble("latitude"));
	}

	@Override
	public Location read(Long location_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM locations WHERE location_id = ? LIMIT 1");) {
			statement.setLong(1, location_id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Location read(String postcode) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM locations WHERE postcode = ? LIMIT 1");) {
			statement.setString(1, postcode);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Location readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM locations ORDER BY location_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Location create(Location location) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO locations (postcode, longitude, latitude) VALUES (?, ?, ?)");) {
			statement.setString(1, location.getPostcode());
			statement.setDouble(2, location.getLongitude());
			statement.setDouble(3, location.getLatitude());
			statement.execute();
			return readLatest();
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Boolean delete(Long location_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM locations WHERE location_id = ? LIMIT 1");) {
			statement.setLong(1, location_id);
			return (statement.executeUpdate() == 1);
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public List<Location> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM locations");) {
			List<Location> locations = new ArrayList<Location>();
			while (resultSet.next()) {
				locations.add(modelFromResultSet(resultSet));
			}
			return locations;
		} catch (Exception e) {
			System.out.checkError();
			System.out.println(e.getMessage());
		}
		return new ArrayList<Location>();
	}

	@Override
	public Boolean update(Location t) {
		// TODO Auto-generated method stub
		return null;
	}

}
