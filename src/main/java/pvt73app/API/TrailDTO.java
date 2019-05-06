package pvt73app.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TrailDTO {
	@JsonProperty("GeographicalPosition")
	private GeographicalPositionDTO geographicalPosition;
	@JsonProperty("Id")
	private String id;
	@JsonProperty("Name")
	private String name;
	private String description;
	
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
	
	@Override
	public String toString() {
		return "Trail {geographicalPosition=" + geographicalPosition + ", id=" + id + ", name=" + name + ", description =" + description + "}";
	}
	
}
