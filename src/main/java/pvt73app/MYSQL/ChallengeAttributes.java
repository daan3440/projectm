package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class ChallengeAttributes {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int count;
	private java.util.Date startdate;
	private java.util.Date enddate;

	public int getCount(){
		return count;
	}

	public void setCount(int count){
		this.count=count;
	}

	public java.util.Date getStartdate(){
		return startdate;
	}

	public void setStartdate(java.util.Date startdate){
		this.startdate=startdate;
	}

	public java.util.Date getEnddate(){
		return enddate;
	}

	public void setEnddate(java.util.Date enddate){
		this.enddate=enddate;
	}
}