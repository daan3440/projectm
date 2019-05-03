package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class Trails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String trailname;
	private double geoLocationX;
	private double geoLocationY;
	private String description;

	public String getTrailname(){
		return trailname;
	}

	public void setTrailname(String trailname){
		this.trailname=trailname;
	}

	public double getGeolocationx(){
		return geoLocationX;
	}

	public void setGeolocationx(double geoLocationX){
		this.geoLocationX=geoLocationX;
	}

	public double getGeolocationy(){
		return geoLocationY;
	}

	public void setGeolocationy(double geoLocationY){
		this.geoLocationY=geoLocationY;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description=description;
	}
}