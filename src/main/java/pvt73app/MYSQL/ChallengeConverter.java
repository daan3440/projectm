package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class ChallengeConverter {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int caid;
	private int cid;

	public int getCaid(){
		return caid;
	}

	public void setCaid(int caid){
		this.caid=caid;
	}

	public int getCid(){
		return cid;
	}

	public void setCid(int cid){
		this.cid=cid;
	}
}