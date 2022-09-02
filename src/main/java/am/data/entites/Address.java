package am.data.entites;

public class Address {

	public Address(String address_line_one, String address_line_two, String city, String postcode) {
		this.address_line_one = address_line_one;
		this.address_line_two = address_line_two;
		this.city = city;
		this.postcode = postcode;
	}

	public Address(Long address_id, String address_line_one, String address_line_two, String city, String postcode) {
		this.address_id = address_id;
		this.address_line_one = address_line_one;
		this.address_line_two = address_line_two;
		this.city = city;
		this.postcode = postcode;
	}

	private Long address_id;

	private String address_line_one;
	private String address_line_two;
	private String city;
	private String postcode;

	public Location getLocation() {
		return new Location(postcode);
	}

	@Override
	public String toString() {
		return "Address [address_id=" + address_id + ", address_line_one=" + address_line_one + ", address_line_two="
				+ address_line_two + ", city=" + city + ", postcode=" + postcode + "]";
	}

	public Long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}

	public String getAddress_line_one() {
		return address_line_one;
	}

	public void setAddress_line_one(String address_line_one) {
		this.address_line_one = address_line_one;
	}

	public String getAddress_line_two() {
		return address_line_two;
	}

	public void setAddress_line_two(String address_line_two) {
		this.address_line_two = address_line_two;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address_id == null) ? 0 : address_id.hashCode());
		result = prime * result + ((address_line_one == null) ? 0 : address_line_one.hashCode());
		result = prime * result + ((address_line_two == null) ? 0 : address_line_two.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
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
		Address other = (Address) obj;
		if (address_id == null) {
			if (other.address_id != null)
				return false;
		} else if (!address_id.equals(other.address_id))
			return false;
		if (address_line_one == null) {
			if (other.address_line_one != null)
				return false;
		} else if (!address_line_one.equals(other.address_line_one))
			return false;
		if (address_line_two == null) {
			if (other.address_line_two != null)
				return false;
		} else if (!address_line_two.equals(other.address_line_two))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		return true;
	}

}
