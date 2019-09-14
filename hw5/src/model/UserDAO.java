package model;

import java.util.Arrays;
import java.util.HashMap;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;

import databean.UserBean;

public class UserDAO extends GenericDAO<UserBean> {
	public UserDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(UserBean.class, tableName, pool);
	}

	public UserBean[] getUsers() throws RollbackException {
		UserBean[] users = match();
		Arrays.sort(users);
		return users;
	}
	
	public HashMap<String, String> getNameByEmail(UserBean[] users) {
		HashMap<String, String> map = new HashMap<>();
		
		for (UserBean user : users) {
			map.put(user.getEmail(),
					user.getFirstName() + " " + user.getLastName());
		}
		return map;
	}
}
