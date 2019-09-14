package databean;

import java.util.Date;
import org.genericdao.PrimaryKey;


@PrimaryKey("commentId")
public class CommentBean implements Comparable<CommentBean>{

	private int commentId;
	private String commentEmail;
	private int postId;
	private String content;
	private Date datetime;
	private String commentName;

	//getter
	public String getCommentEmail(){
		return commentEmail;
	}

	public int getCommentId(){
		return commentId;
	}

	public int getPostId(){
		return postId;
	}

	public String getContent(){
		return content;
	}

	public Date getDatetime(){
		return datetime;
	}

	public String getCommentName(){
		return commentName;
	}

	//setter


	public void setCommentId(int comtdId){
		commentId = comtdId;
	}

	public void setCommentEmail(String email){
		commentEmail = email;
	}

	public void setPostId(int postId){
		this.postId = postId;
	}

	public void setContent(String content){
		this.content = content;
	}

	public void setDatetime(Date date){
		datetime = date;
	}

	public void setCommentName(String name){
		commentName = name;
	}
	
	public int compareTo(CommentBean user) {
		return user.getDatetime().compareTo(this.datetime);
	}

}
