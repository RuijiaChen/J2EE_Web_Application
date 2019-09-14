package formbean;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class CommentForm {

	private String content;
	private String action;

	public CommentForm(HttpServletRequest request) {
		content = request.getParameter("comment");
	}

	public String getContent() {
		return content;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<>();

		if (content.length() == 0)
			errors.add("Comment content required");

		if (errors.size() > 0)
			return errors;

		if (content.length() > 100)
			errors.add("Please comment no more than 100 characters");

		return errors;
	}

}