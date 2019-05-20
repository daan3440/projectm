package pvt73app.MYSQL;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

import java.time.LocalDateTime;

// This tells Hibernate to make a table out of this class
@Entity(name = "trailreview")
@IdClass(TrailReviewID.class)
public class TrailReview implements FeedElement{
	@Id
	@Column(name = "tid")
	private int tid; // TODO: Make tid and uid into a composit key to make it work in the future
	@Id
	@Column(name = "uid")
	private int uid;
	//private TrailReviewID id;
	private String review;
	private int rating;
	private LocalDateTime date;
	private String title;

	public int getTid(){
		return tid;
	}

	public void setTid(int tid){
		this.tid = tid;
	}
	public int getUid(){
		return uid;
	}
	
	public void setUid(int uid){
		this.uid = uid;
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

	public LocalDateTime getDate(){
		return date;
	}

	public void setDate(LocalDateTime cdate){
		this.date=cdate;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title=title;
	}
}