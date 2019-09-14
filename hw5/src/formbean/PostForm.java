package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PostForm {

	private String content;
	
	public PostForm(HttpServletRequest request) {
		content = request.getParameter("post");
	}
	
	public String getContent() {
		return content;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<>();

		if (content.length() == 0)
			errors.add("Please write something before your post");

		if (content.length() > 100)
			errors.add("The post content is too long");
		return errors;
	}
	
}
