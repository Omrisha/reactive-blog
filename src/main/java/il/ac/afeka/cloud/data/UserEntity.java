package il.ac.afeka.cloud.data;

import org.springframework.data.annotation.Id;

public class UserEntity {
	private String email;

	public UserEntity() {
		super();
	}

	public UserEntity(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Id
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserEntity [email=" + email + "]";
	}
	
}
