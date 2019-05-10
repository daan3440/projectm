package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "challengeconnector")
public class ChallengeConnector {
    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int caid;
	private int cid;
	private int tid;

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
	public int getTid(){
		return tid;
	}
	
	public void setTid(int tid){
		this.tid=tid;
	}
}