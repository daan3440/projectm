package pvt73app.MYSQL;

import java.sql.Date;
import java.time.LocalDateTime;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// This tells Hibernate to make a table out of this class
@Entity(name = "userruns")
public class UserRuns implements FeedElement{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int uid;
	private int tid;
	private LocalDateTime date;
	private BigInteger time;
	private int length;
	private String comment;

	public int getId(){
		return id;
	}
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
	
	public LocalDateTime getDate(){
		return date;
	}
	
	public void setDate(LocalDateTime cdate){
		this.date=cdate;
	}
	
	public BigInteger getTime(){
		return time;
	}
	
	public void setTime(BigInteger time){
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