package controller;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.PostBean;
import databean.CommentBean;
import databean.UserBean;
import formbean.PostForm;

import model.CommentDAO;
import model.Model;
import model.PostDAO;
import model.UserDAO;


public class DeleteCommentHomeAction extends Action {

	private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;

	public DeleteCommentHomeAction(Model model) {
		userDAO = model.getUserDAO();
		postDAO = model.getPostDAO();
		commentDAO = model.getCommentDAO();
	}

	@Override
	public String getName() {
		return "deleteCommentHome.do";
	}
		
	public String performGet(HttpServletRequest request) {
		return performPost(request);
	}

	public String performPost(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if (user == null) {
			// connection broken, back to login
			return "login.do";
		}

		List<String> errors = new ArrayList<>();
		request.setAttribute("errors", errors);

		try {
			String commentIdStr = request.getParameter("commentId");

            String userEmail = request.getParameter("userEmail");
            UserBean userSelected = null;
            if (userEmail != null) {
                userSelected = userDAO.read(userEmail);
                request.setAttribute("userSelected", userSelected);
            }

			if (commentIdStr != null) {
				int commentId = Integer.parseInt(commentIdStr);
				// use transaction on delete comment to avoid race condition
				Transaction.begin();
				CommentBean comments = commentDAO.read(commentId);

				if (comments != null) {
					commentDAO.delete(commentId);
    
                }
				Transaction.commit();
			}

			// put the content in text area back
			PostForm postForm = new PostForm(request);
			request.setAttribute("postForm", postForm);


			// put the updated posts back
			// if userSelected is null, it's from home page
			PostBean[] posts = postDAO
					.getPostsFromUser((userSelected != null) ? userSelected.getEmail() : user.getEmail());
			request.setAttribute("posts", posts);

			// put the users back
			UserBean[] users = userDAO.getUsers();
			request.setAttribute("users", users);

			// map for adding comments under each related post
			HashMap<Integer, CommentBean[]> postIdToCommentsMap = commentDAO.getComments(posts);
			request.setAttribute("postIdToCommentsMap", postIdToCommentsMap);

			// map email to user full name, easier for comment creation
			Map<String, String> emailToFullNameMap = userDAO.getNameByEmail(users);
			request.setAttribute("emailToFullNameMap", emailToFullNameMap);

			return getNextPage(request, errors);
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return getNextPage(request, errors);
		} catch (Exception e) {
			errors.add(e.getMessage());
			return getNextPage(request, errors);
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	/**
	 * forward to the correct page
	 */
	private String getNextPage(HttpServletRequest request, List<String> errors) {
		if (errors.size() > 0) {
			return "action-error-message.jsp";
		}
		return "Home.jsp";
	}

}