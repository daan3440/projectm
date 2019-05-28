package pvt73app.MYSQL;

import java.sql.Date;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// This tells Hibernate to make a table out of this class
@Entity(name = "challengeattributes")
public class ChallengeAttributes implements FeedElement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private BigInteger time;
	private int count;
	private LocalDateTime startdate;
	private LocalDateTime enddate;
	private boolean complete;
	

	public ChallengeAttributes() {
	}

	public ChallengeAttributes(BigInteger time, int count, LocalDateTime startdate, LocalDateTime enddate) {
		if(time != null)
			this.time = time;
		//Notera -1 för inte definierad
		if(count != -1)
			this.count= count;
		this.startdate = startdate;
		this.enddate = enddate;
		this.complete = false;
	}

	public int getId(){
		return id;
	}

	public int getCount(){
		return count;
	}

	public void setCount(int count){
		this.count=count;
	}
	public BigInteger getTime(){
		return time;
	}
	
	public void setTime(BigInteger time){
		this.time= time;
	}

	public LocalDateTime getStartdate(){
		return startdate;
	}

	public void setStartdate(LocalDateTime startdate){
		this.startdate=startdate;
	}

	public LocalDateTime getEnddate(){
		return enddate;
	}

	public void setEnddate(LocalDateTime enddate){
		this.enddate=enddate;
	}
	public boolean getComplete(){
		return complete;
	}
	
	public void setComplete(boolean complete){
		this.complete=complete;
	}

	@Override
	public LocalDateTime getDate() {
		return getStartdate();
	}

	
}