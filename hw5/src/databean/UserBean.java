package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("email")
public class UserBean implements Comparable<UserBean> {

	private String email;
	private String password;
	private String firstName;
	private String lastName;

	// Getter


	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}


	// Setter


	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int compareTo(UserBean user) {
		int compareRes = this.getFirstName().compareTo(user.getFirstName());
		if (compareRes != 0) {
			return compareRes;
		} 
		else {
			return this.getLastName().compareTo(user.getLastName());
		}
	}

}
