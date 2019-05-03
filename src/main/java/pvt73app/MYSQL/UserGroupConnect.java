package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class UserGroupConnect {
	@Id
	private Integer uid;
	private Integer gid;

	public int getGid(){
		return gid;
	}

	public void setGid(int gid){
		this.gid=gid;
	}
	
	public int getUid(){
		return uid;
	}
	
	public void setUid(int uid){
		this.uid=uid;
	}
}