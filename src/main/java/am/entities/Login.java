package am.entities;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Login {

	private String username;
	private String password;

}
