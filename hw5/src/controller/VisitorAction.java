package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import databean.CommentBean;
import databean.PostBean;
import databean.UserBean;
import formbean.CommentForm;
import model.CommentDAO;
import model.Model;
import model.PostDAO;
import model.UserDAO;

public class VisitorAction extends Action {

	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;

	public VisitorAction(Model model) {
		userDAO = model.getUserDAO();
		postDAO = model.getPostDAO();
		commentDAO = model.getCommentDAO();
	}

	@Override
	public String getName() {
		return "visit.do";
	}


	public String performGet(HttpServletRequest request) {
		List<String> errors = new ArrayList<>();
		request.setAttribute("errors", errors);
		try {
			String email = request.getParameter("userEmail");
			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute("user");

			UserBean userSelected = null;
			if (email != null) {
				userSelected = userDAO.read(email);
				request.setAttribute("userSelected", userSelected);
			}

			CommentForm commentForm = new CommentForm(request);

			if (user != null && (commentForm.getContent()!=null)) {
				String postIdStr = request.getParameter("postId");
				int postId = Integer.parseInt(postIdStr);
				CommentBean commentBean = new CommentBean();
				commentBean.setDatetime(new Date());
				commentBean.setContent(commentForm.getContent());
				commentBean.setCommentEmail(user.getEmail());
				commentBean.setPostId(postId);
				commentDAO.create(commentBean);
			}

			// show the posts of the user selected
			PostBean[] posts = postDAO.getPostsFromUser(userSelected.getEmail());
			request.setAttribute("posts", posts);

			UserBean[] users = userDAO.getUsers();
			request.setAttribute("users", users);

			// map for adding comments under each related post
			HashMap<Integer, CommentBean[]> postIdToCommentsMap = commentDAO.getComments(posts);
			request.setAttribute("postIdToCommentsMap", postIdToCommentsMap);

			// map email to user full name, easier for comment creation
			Map<String, String> emailToFullNameMap = userDAO.getNameByEmail(users);
			request.setAttribute("emailToFullNameMap", emailToFullNameMap);

			return "Visitor.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "Visitor.jsp";
		}
	}

	public String performPost(HttpServletRequest request) {
		return performGet(request);
	}
}