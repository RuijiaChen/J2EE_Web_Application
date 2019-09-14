package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;


public class Model {
	private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;
	 
	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
			
			userDAO  = new UserDAO(pool, "ruijiac_user");
			postDAO = new PostDAO(pool, "ruijiac_post");
			commentDAO = new CommentDAO(pool,"ruijiac_comment");
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public PostDAO getPostDAO()  { return postDAO; }
	public UserDAO getUserDAO()  { return userDAO; }
	public CommentDAO getCommentDAO()  { return commentDAO; }
	 
}
