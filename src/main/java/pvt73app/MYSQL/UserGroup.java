package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// This tells Hibernate to make a table out of this class
@Entity(name = "usergroup")
public class UserGroup {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String groupname;

	public String getGroupName(){
		return groupname;
	}

	public void setGroupName(String groupname){
		this.groupname=groupname;
	}

	public Integer getID() {
		return id;
	}
}