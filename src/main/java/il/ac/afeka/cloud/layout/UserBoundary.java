package il.ac.afeka.cloud.layout;

import il.ac.afeka.cloud.data.UserEntity;

public class UserBoundary {
	private String email;

	public UserBoundary() {
		super();
	}

	public UserBoundary(String email) {
		super();
		this.email = email;
	}

	public UserBoundary(UserEntity entity) {
		super();
		if (entity.getEmail() != null)
			this.email = entity.getEmail();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserBoundary [email=" + email + "]";
	}

}
