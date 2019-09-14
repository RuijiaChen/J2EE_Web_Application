package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import databean.UserBean;
import formbean.LoginForm;
import model.Model;
import model.UserDAO;

public class LoginAction extends Action {
    private UserDAO userDAO;

    public LoginAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "login.do";
    }

    public String performGet(HttpServletRequest request){
        // If user is ALREADY logged in, redirect to todolist.do
        if (request.getSession().getAttribute("user") != null) {
            return "home.do";
        }

        // Otherwise, just display the login page.
        try {
        	request.setAttribute("users", userDAO.getUsers());
        }
        catch(RollbackException e) {
        	e.printStackTrace();
        }
        request.setAttribute("form", new LoginForm(request));
        return "Login.jsp";
    }

    public String performPost(HttpServletRequest request) {
        // If user is ALREADY logged in, redirect to home.do
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "home.do";
        }

        List<String> errors = new ArrayList<>();
        request.setAttribute("errors",errors);

        try {
            request.setAttribute("users", userDAO.getUsers());

            LoginForm form = new LoginForm(request);
            request.setAttribute("form", form);

            errors.addAll(form.getValidationErrors());
           
            if ((errors != null) && (errors.size() != 0)) {
                return "Login.jsp";
            }

            // Look up the user
            UserBean user = userDAO.read(form.getEmail());
            if (user == null) {
                errors.add("Email not found");
                return "Login.jsp";
            }

            // Check the password
            if (!form.getPassword().equals(user.getPassword())) {
                errors.add("Incorrect password");
                return "Login.jsp";
            }

            // Attach (this copy of) the user bean to the session
            session.setAttribute("user", user);

            // If redirectTo is null, redirect to the "home" action
            return "home.do";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "action-error-message.jsp";
        }
    }
}
