package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import databean.UserBean;
import formbean.RegisterForm;
import model.Model;
import model.UserDAO;


public class RegisterAction extends Action {

    private UserDAO userDAO;

    public RegisterAction(Model model) {
        userDAO = model.getUserDAO();
    }

	@Override
    public String getName() {
        return "register.do";
    }
    
    public String performGet(HttpServletRequest request) {
    	return performPost(request);

    }

    public String performPost(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
        	return "home.do";
        }

        List<String> errors = new ArrayList<>();
        request.setAttribute("errors",errors);

        try {
            UserBean[] users = userDAO.getUsers();
            session.setAttribute("users", users);

            RegisterForm form = new RegisterForm(request);
            request.setAttribute("form", form);
            
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "Register.jsp";
            }
            UserBean newUser = new UserBean();
            newUser.setEmail(form.getEmail());
            newUser.setPassword(form.getPassword());
            newUser.setFirstName(form.getFirstName());
            newUser.setLastName(form.getLastName());

            try {
                userDAO.create(newUser);
                session.setAttribute("user", newUser);
                return ("home.do");
            } catch (DuplicateKeyException e) {
                errors.add("user with this name already exists");
                return "Register.jsp";
            }
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "Login.jsp";
        }
    }

}

