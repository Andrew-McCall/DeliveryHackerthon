package am.data.entites;

import lombok.Data;

@Data
public class Login {

	private Long login_id;
	private String username;
	private String password;
	private Boolean isManager;

}
