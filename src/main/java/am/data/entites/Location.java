package am.data.entites;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import am.data.daos.LocationDAO;

public class Location {

	public Location(String postcode) {
		this.postcode = postcode;
		this.findCoordinates();
	}

	public Location(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Location(Long location_id, String postcode, double longitude, double latitude) {
		this.location_id = location_id;
		this.postcode = postcode;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	private Long location_id;
	private String postcode;
	private double longitude;
	private double latitude;

	private final String BASE_API_URL = "https://api.promaptools.com/service/uk/postcode-lat-lng/get/?postcode=";
	private final String API_KEY = "&key=17o8dysaCDrgv1c";

	private void findCoordinates() {
		this.postcode = postcode.toUpperCase();
		Location cache = new LocationDAO().read(this.postcode);
		if (cache == null) {
			HttpClient client = HttpClient.newHttpClient();
			String urlPostcode = postcode.replace(" ", "%20");
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_API_URL + urlPostcode + API_KEY))
					.build();
			client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(this::parseJson)
					.join();
		} else {
			this.setLocation_id(cache.getLocation_id());
			this.setLongitude(cache.getLongitude());
			this.setLatitude(cache.getLatitude());
		}
	}

	private void parseJson(String json) {
		System.out.println(json);
		if (json.indexOf("1") == -1) {// Positive Status
			this.setLongitude(0.0);
			this.setLatitude(0.0);
			return;
		}

		try {
			double latitude = Double.parseDouble(jsonColumn("latitude", json));
			double longitude = Double.parseDouble(jsonColumn("longitude", json));
			this.setLatitude(latitude);
			this.setLongitude(longitude);
			new LocationDAO().create(this);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			this.setLongitude(0.0);
			this.setLatitude(0.0);
		}
		System.out.println(this);
	}

	private String jsonColumn(String column, String json) {
		column = "\"" + column + "\":\"";
		int i = json.indexOf(column);
		json = json.substring(i + column.length());
		return json.substring(0, json.indexOf("\""));
	}

	@Override
	public String toString() {
		return "Location [location_id=" + location_id + ", postcode=" + postcode + ", longitude=" + longitude
				+ ", latitude=" + latitude + "]";
	}

	public Long getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((location_id == null) ? 0 : location_id.hashCode());
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (location_id == null) {
			if (other.location_id != null)
				return false;
		} else if (!location_id.equals(other.location_id))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		return true;
	}
}
