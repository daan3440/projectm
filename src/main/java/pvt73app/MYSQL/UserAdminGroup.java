package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// This tells Hibernate to make a table out of this class
@Entity(name = "useradmingroup")
public class UserAdminGroup {
    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int uid;
	private int gid;

	
	public int getUid(){
		return uid;
	}
	
	public void setUid(int uid){
		this.uid=uid;
	}
	public int getGid(){
		return gid;
	}
	
	public void setGid(int gid){
		this.gid=gid;
	}
}