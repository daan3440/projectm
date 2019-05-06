package pvt73app.MYSQL;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class UserRuns {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int uid;
	private String tid;
	private Timestamp date;
	private double time;
	private int length;
	private String comment;

	public String getTid(){
		return tid;
	}

	
	public void setTid(String tid){
		this.tid=tid;
	}
	
	public int getUserId(){
		return uid;
	}

	public void setUserId(int uid){
		this.uid=uid;
	}
	
	public Timestamp getDate(){
		return date;
	}
	
	public void setDate(Timestamp date){
		this.date=date;
	}
	
	public double getTime(){
		return time;
	}
	
	public void setTime(double time){
		this.time=time;
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