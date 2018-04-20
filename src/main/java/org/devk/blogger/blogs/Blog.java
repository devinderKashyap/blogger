package org.devk.blogger.blogs;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 
 * @author devinder kashyap
 *
 */
@Document(collection="blog")
public class Blog {
	@Id
	private String id;
	private String title;
	private String desc;
	private String userid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "blog [id=" + id + ", title=" + title + ", desc=" + desc + ", userid=" + userid + "]";
	}
	
	
}
