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
import formbean.PostForm;
import model.CommentDAO;
import model.Model;
import model.PostDAO;
import model.UserDAO;

public class HomeAction extends Action {

	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;

	public HomeAction(Model model) {
		userDAO = model.getUserDAO();
		postDAO = model.getPostDAO();
		commentDAO = model.getCommentDAO();
	}

	@Override
	public String getName() {
		return "home.do";
	}

	@Override
	public String performGet(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if (user == null) {
			// connection broken, back to login
			return "login.do";
		}

		List<String> errors = new ArrayList<>();
		request.setAttribute("errors", errors);

		try {
			PostForm postForm = new PostForm(request);
			request.setAttribute("postForm", postForm);

			if ((postForm.getContent())!=null ){
				errors.addAll(postForm.getValidationErrors());
				// do this only if no errors
				if (errors.size() == 0) {
					PostBean post = new PostBean();
					post.setPostEmail(user.getEmail());
					post.setContent(postForm.getContent());
					post.setDatetime(new Date());
					postDAO.create(post);
				}
			}

			// check if there's an incoming new comment request
			CommentForm commentForm = new CommentForm(request);

			if ((commentForm.getContent()) != null) {
				String postIdStr = request.getParameter("postId");
				int postId = Integer.parseInt(postIdStr);
				CommentBean commentBean = new CommentBean();
				commentBean.setDatetime(new Date());
				commentBean.setContent(commentForm.getContent());
				commentBean.setCommentEmail(user.getEmail());
				commentBean.setPostId(postId);
				commentDAO.create(commentBean);
			}

			PostBean[] posts = postDAO.getPostsFromUser(user.getEmail());
			request.setAttribute("posts", posts);

			UserBean[] users = userDAO.getUsers();
			request.setAttribute("users", users);

			// map for adding comments under each related post
			HashMap<Integer, CommentBean[]> postIdToCommentsMap = commentDAO.getComments(posts);
			request.setAttribute("postIdToCommentsMap", postIdToCommentsMap);

			// map email to user full name, easier for comment creation
			Map<String, String> emailToFullNameMap = userDAO.getNameByEmail(users);
			request.setAttribute("emailToFullNameMap", emailToFullNameMap);

			String userEmail = request.getParameter("userEmail");
			if (userEmail != null) {
				UserBean userSelected = userDAO.read(userEmail);
				request.setAttribute("userSelected", userSelected);
			}

			return "Home.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "Home.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "Home.jsp";
		}

	}

	@Override
	public String performPost(HttpServletRequest request) {
		return performGet(request);
	}
}
