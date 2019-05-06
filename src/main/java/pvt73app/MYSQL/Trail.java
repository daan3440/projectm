package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class

public class Trail {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;
	private String trailname;
	private double geoLocationX;
	private double geoLocationY;
	private String description;
	private String location;
	private String image;

	public String getTrailID(){
		return id;
	}
	
	public void setTrailID(String tid){
		this.id=tid;
	}
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
	public String getLocation(){
		return location;
	}
	
	public void setLocation(String location){
		this.location=location;
	}
	public String getImage(){
		return image;
	}
	
	public void setImage(String image){
		this.image=image;
	}
}