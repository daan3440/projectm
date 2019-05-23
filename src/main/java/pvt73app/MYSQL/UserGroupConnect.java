package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

// This tells Hibernate to make a table out of this class
@Entity(name = "usergroupconnect")
@IdClass(UserGroupConnectID.class)
public class UserGroupConnect {
	@Id
	private Integer uid;
	@Id
	private Integer gid;

	public int getGid(){
		return uid;
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