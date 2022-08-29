package am.data.entites;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {

	private Long login_id;
	private String username;
	private String password;
	private Boolean isManager;

}
