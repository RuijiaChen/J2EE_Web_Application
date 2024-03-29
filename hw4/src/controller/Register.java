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

import model.UserDAO;
import databean.UserBean;
import formbean.RegisterForm;

@WebServlet("/Register")
public class Register extends HttpServlet {

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
		if (session.getAttribute("user") != null) {
			response.sendRedirect("Home");
			return;
		}

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			request.setAttribute("users", userDAO.getUsers());

			RegisterForm form = new RegisterForm(request);
			request.setAttribute("form", form);
			errors.addAll(form.getValidationErrors());
			
			if (errors.size() != 0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
				dispatcher.forward(request, response);
				return;
			}

			UserBean user = new UserBean();
			user.setEmail(form.getEmail());
			user.setPassword(form.getPassword());
			user.setFirstName(form.getFirstName());
			user.setLastName(form.getLastName());

			userDAO.create(user);

			session.setAttribute("user", user);
			response.sendRedirect("Home");

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}