package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class UserTrails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tid;

	public int getTid(){
		return tid;
	}

	public void setTid(int tid){
		this.tid=tid;
	}
}