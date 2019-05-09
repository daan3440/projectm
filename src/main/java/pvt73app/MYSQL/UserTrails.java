package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class UserTrails {
    

	@Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tid;
    private int uid;

	public int getTid(){
		return tid;
	}

	public void setTid(int tid){
		this.tid=tid;
	}
	
	public int getUid(){
		return uid;
	}
	
	public void setUid(int uid){
		this.uid=uid;
	}
}