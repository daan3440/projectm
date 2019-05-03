package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class UserRuns {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tid;
	private int length;
	private String comment;

	public int getTid(){
		return tid;
	}

	public void setTid(int tid){
		this.tid=tid;
	}

	public int getLength(){
		return length;
	}

	public void setLength(int length){
		this.length=length;
	}

	public String getComment(){
		return comment;
	}

	public void setComment(String comment){
		this.comment=comment;
	}
}