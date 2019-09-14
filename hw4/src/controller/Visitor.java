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

@WebServlet("/Visitor")
public class Visitor extends HttpServlet {

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

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        List<String> errors = new ArrayList<>();
        request.setAttribute("errors", errors);
        
    	try {
	        request.setAttribute("users", userDAO.getUsers());
    	}
	    catch(RollbackException e){
	    	errors.add(e.getMessage());
	    }
        RequestDispatcher dispatcher = request.getRequestDispatcher("Visitor.jsp");
        dispatcher.forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}