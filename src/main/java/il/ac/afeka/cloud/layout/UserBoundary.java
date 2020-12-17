package il.ac.afeka.cloud.layout;

public class UserBoundary {
	private String email;

	public UserBoundary() {
		super();
	}

	public UserBoundary(String email) {
		super();
		this.email = email;
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
