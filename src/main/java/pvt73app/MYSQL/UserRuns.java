package pvt73app.MYSQL;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// This tells Hibernate to make a table out of this class
@Entity(name = "userruns")
public class UserRuns {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int uid;
	private int tid;
	private Date date;
	private double time;
	private int length;
	private String comment;

	public int getTid(){
		return tid;
	}

	
	public void setTid(int tid){
		this.tid=tid;
	}
	
	public int getUserId(){
		return uid;
	}

	public void setUserId(int uid){
		this.uid=uid;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setDate(Date date){
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