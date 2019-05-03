package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class Group {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String groupname;

	public String getGroupname(){
		return groupname;
	}

	public void setGroupname(String groupname){
		this.groupname=groupname;
	}

	public Integer getID() {
		return id;
	}
}