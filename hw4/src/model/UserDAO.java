package model;

import java.util.Arrays;

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
}
