package pvt73app.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import pvt73app.Coordinates.RT90Position;
import pvt73app.Coordinates.WGS84Position;
import pvt73app.API.GeographicalPositionDTO;


@JsonIgnoreProperties(ignoreUnknown=true)
public class TrailDTO {
	@JsonProperty("GeographicalPosition")
	private GeographicalPositionDTO geographicalPosition;
	@JsonProperty("Id")
	private String id;
	@JsonProperty("Name")
	private String name;
	private String description;
	private String location;
	private double geoLocationX;
	private double geoLocationY;
	private String imageId;
	
	public TrailDTO() {
	}

	public GeographicalPositionDTO getGeographicalPosition() {
		return geographicalPosition;
	}

	public void setGeographicalPosition(GeographicalPositionDTO geographicalPosition) {
		this.geographicalPosition = geographicalPosition;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void createWGS84GeoLocation() {
		if(geographicalPosition != null) {
			RT90Position rt90pos = new RT90Position(geographicalPosition.getX(), geographicalPosition.getY());
			WGS84Position wgs84pos = rt90pos.toWGS84();
			geoLocationX = wgs84pos.getLatitude();
			geoLocationY = wgs84pos.getLongitude();
		}
	}
	
	public double getGeoLocationX() {
		return geoLocationX;
	}

	public double getGeoLocationY() {
		return geoLocationY;
	}
	
	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	@Override
	public String toString() {
		return "TrailDTO [geographicalPosition=" + geographicalPosition + ", id=" + id + ", name=" + name
				+ ", description=tillfälligt bortagen" + ", location=" + location + ", geoLocationX=" + geoLocationX
				+ ", geoLocationY=" + geoLocationY + "imageId=" +imageId + "]";
	}
}
