package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import databean.UserBean;
import formbean.LoginForm;
import model.UserDAO;

@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;

	public void init() throws ServletException {
		ServletContext context = getServletContext();
		String jdbcDriverName = context.getInitParameter("jdbcDriverName");
		String jdbcURL = context.getInitParameter("jdbcURL");

		try {
			ConnectionPool pool = new ConnectionPool(jdbcDriverName, jdbcURL);
			pool.setDebugOutput(System.out); 

			userDAO = new UserDAO(pool, "ruijiac_user");
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> errors = new ArrayList<>();
		request.setAttribute("errors", errors);

		try {
			request.setAttribute("users", userDAO.getUsers());

			LoginForm form = new LoginForm(request);
			request.setAttribute("form", form);
			
			if ("GET".equals(request.getMethod())) {
				RequestDispatcher d = request.getRequestDispatcher("Login.jsp");
				d.forward(request, response);
				return;
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
				dispatcher.forward(request, response);
				return;
			}

			UserBean user = userDAO.read(form.getEmail());

			if (user == null) {
				errors.add("Email not found");
				RequestDispatcher d = request.getRequestDispatcher("Login.jsp");
				d.forward(request, response);
				return;
			}

			if (!form.getPassword().equals(user.getPassword())) {
				errors.add("Incorrect password");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
				dispatcher.forward(request, response);
				return;
			}

			// login success
			session.setAttribute("user", user);
			response.sendRedirect("Home");

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}