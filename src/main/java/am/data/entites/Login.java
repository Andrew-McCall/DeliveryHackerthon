package am.data.entites;

public class Login {

	public Login(Long login_id, String username, String password, String name, Boolean isManager) {
		this.login_id = login_id;
		this.username = username;
		this.password = password; // Not Hashed Since its from the DB
		this.name = name;
		this.isManager = isManager;
	}

	public Login(String username, String password, String name, Boolean isManager) {
		this.username = username;
		setPassword(password);
		this.name = name;
		this.isManager = isManager;
	}

	public Login(String username, String password) {
		this.username = username;
		setPassword(password);
	}

	private Long login_id;

	private String username;
	private String password;
	private String name;
	private Boolean isManager;

	public void setPassword(String password) {
		this.password = String.valueOf(password.hashCode());
	}

	// Generated
	@Override
	public String toString() {
		return "Login [login_id=" + login_id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", isManager=" + isManager + "]";
	}

	public Long getLogin_id() {
		return login_id;
	}

	public void setLogin_id(Long login_id) {
		this.login_id = login_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isManager == null) ? 0 : isManager.hashCode());
		result = prime * result + ((login_id == null) ? 0 : login_id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Login other = (Login) obj;
		if (isManager == null) {
			if (other.isManager != null)
				return false;
		} else if (!isManager.equals(other.isManager))
			return false;
		if (login_id == null) {
			if (other.login_id != null)
				return false;
		} else if (!login_id.equals(other.login_id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
