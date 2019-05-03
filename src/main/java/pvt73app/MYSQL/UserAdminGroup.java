package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class UserAdminGroup {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int gid;

	public int getGid(){
		return gid;
	}

	public void setGid(int gid){
		this.gid=gid;
	}
}