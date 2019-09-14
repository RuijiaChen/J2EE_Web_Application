package model;


import java.util.Arrays;
import java.util.HashMap;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.CommentBean;
import databean.PostBean;


public class CommentDAO extends GenericDAO<CommentBean> {
	public CommentDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(CommentBean.class, tableName, cp);
    }
	
	public CommentBean[] getCommentsByPostId(int postId) throws RollbackException {

		CommentBean[] comments = match(MatchArg.equals("postId", postId));
		Arrays.sort(comments);
		return comments;
	}

	public HashMap<Integer, CommentBean[]> getComments(PostBean[] posts) throws RollbackException {
		HashMap<Integer, CommentBean[]> postIdToCommentsMap = new HashMap<>();
		for (PostBean post : posts) {
			int postId = post.getPostId();

			CommentBean[] comments = getCommentsByPostId(postId);
			postIdToCommentsMap.put(postId, comments);
		}
		return postIdToCommentsMap;
	}
}
