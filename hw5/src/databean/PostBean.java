package databean;

import java.util.Date;
import org.genericdao.PrimaryKey;


@PrimaryKey("postId")
public class PostBean  implements Comparable<PostBean>{

	private int postId;
	private String postEmail;
	private String content;
	private Date datetime;


	//getter

	public int getPostId(){
		return postId;
	}

	public String getPostEmail(){
		return postEmail;
	}

	public String getContent(){
		return content;
	}

	public Date getDatetime(){
		return datetime;
	}


	//setter

	public void setPostEmail(String email){
		postEmail = email;
	}

	public void setPostId(int postId){
		this.postId = postId;
	}

	public void setContent(String content){
		content.replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;").replace("&","&amp;");
		this.content = content;
	}

	public void setDatetime(Date date){
		datetime = date;
	}

	
	public int compareTo(PostBean user) {
		return user.getDatetime().compareTo(this.datetime);
	}

}
