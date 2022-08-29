package am.data.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Login {

	private Long login_id;
	@NonNull
	private String username;
	@NonNull
	private String password;
	@NonNull
	private Boolean isManager;

}
