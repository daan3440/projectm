package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

// This tells Hibernate to make a table out of this class
@Entity(name = "usertrails")
@IdClass(UserTrailsID.class)
public class UserTrails {

	//    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int tid;
	@Id
    private int uid;
    private boolean favourite;

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
	
	public boolean getFavourite(){
		return favourite;
	}
	
	public void setFavourite(boolean favourite){
		this.favourite=favourite;
	}
}