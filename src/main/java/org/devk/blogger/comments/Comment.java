package org.devk.blogger.comments;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 
 * @author devinder kashyap
 *
 */
@Document(collection="comments")
public class Comment {

	@Id
	private String id;
	private String blogid;
	private String userid;
	private String comment;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBlogid() {
		return blogid;
	}
	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", blogid=" + blogid + ", userid=" + userid + ", comment=" + comment + "]";
	}
	
}
