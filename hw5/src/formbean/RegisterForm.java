package formbean;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class RegisterForm {
	private String email;
	private String password;
	private String confirm;
	private String firstName;
	private String lastName;
	private String button;

	public RegisterForm(HttpServletRequest request) {
		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		email = request.getParameter("email");
		password = request.getParameter("password");
		confirm = request.getParameter("confirm");
		button = request.getParameter("button");
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getconfirm() {
		return confirm;
	}

	public String getButton() {
		return button;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<>();

		if (firstName == null || firstName.length() == 0){
			errors.add("First name is required");
		}

		if (lastName == null || lastName.length() == 0){
			errors.add("Last name is required");
		}

		if (email == null || email.length() == 0){
			errors.add("Email is required");
		}

		if (password == null || password.length() == 0){
			errors.add("Password is required");
		}

		if (confirm == null || confirm.length() == 0){
			errors.add("Confirm Password is required");
		}

		if (button == null){
			errors.add("Button is required");
		}

		
		if (errors.size() > 0) {
			return errors;
		}

		if (!button.equals("Register"))
			errors.add("Invalid button");

		if (!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
			errors.add("Invalid email format");
		}

		if (!confirm.equals(password)){
			errors.add("Passwords input twice are not the same");
		}

		return errors;
	}

}