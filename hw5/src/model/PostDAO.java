package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.MatchArg;

import databean.PostBean;

public class PostDAO extends GenericDAO<PostBean> {
	public PostDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PostBean.class, tableName, cp);
	}

	public PostBean[] getPostsFromUser(String email) throws RollbackException {

		PostBean[] posts = match(MatchArg.equals("postEmail", email));
		Arrays.sort(posts);
		return posts;
	}
}