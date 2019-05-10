package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import pvt73app.API.TrailDTO;

// This tells Hibernate to make a table out of this class
@Entity(name = "trail")
public class Trail {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String trailid;
	private String trailname;
	private double geoLocationX;
	private double geoLocationY;
	private String description;
	private String location;
	private String image;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTrailID(){
		return trailid;
	}
	
	public void setTrailID(String tid){
		this.trailid=tid;
	}
	public String getTrailName(){
		return trailname;
	}

	public void setTrailName(String trailname){
		this.trailname=trailname;
	}

	public double getGeoLocationX(){
		return geoLocationX;
	}

	public void setGeoLocationX(double geoLocationX){
		this.geoLocationX=geoLocationX;
	}

	public double getGeoLocationY(){
		return geoLocationY;
	}

	public void setGeoLocationY(double geoLocationY){
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

	@Override
	public String toString() {
		return "Trail [id=" + trailid + ", trailname=" + trailname + ", geoLocationX=" + geoLocationX + ", geoLocationY="
				+ geoLocationY + ", description=" + description + ", location=" + location + ", image=" + image + "]";
	}
	
	
}