package pvt73app.MYSQL;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// This tells Hibernate to make a table out of this class
@Entity(name = "challengeattributes")
public class ChallengeAttributes {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Date time;
	private int count;
	private Date startdate;
	private Date enddate;
	private boolean complete;
	

	public ChallengeAttributes() {
	}

	public ChallengeAttributes(Date time, int count, Date startdate, Date enddate) {
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
	public Date getTime(){
		return time;
	}
	
	public void setTime(Date time){
		this.time= time;
	}

	public Date getStartdate(){
		return startdate;
	}

	public void setStartdate(Date startdate){
		this.startdate=startdate;
	}

	public Date getEnddate(){
		return enddate;
	}

	public void setEnddate(Date enddate){
		this.enddate=enddate;
	}
	public boolean getComplete(){
		return complete;
	}
	
	public void setComplete(boolean complete){
		this.complete=complete;
	}

	
}