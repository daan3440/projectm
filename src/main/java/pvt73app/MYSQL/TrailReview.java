package pvt73app.MYSQL;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class TrailReview {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tid;
    private int uid;
	private String review;
	private int rating;
	private Date date;
	private String title;

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

	public String getReview(){
		return review;
	}

	public void setReview(String review){
		this.review=review;
	}

	public int getRating(){
		return rating;
	}

	public void setRating(int rating){
		this.rating=rating;
	}

	public java.util.Date getDate(){
		return date;
	}

	public void setDate(Date date){
		this.date=date;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title=title;
	}
}