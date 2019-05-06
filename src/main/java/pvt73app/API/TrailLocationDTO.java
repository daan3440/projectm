package pvt73app.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TrailLocationDTO {
	@JsonProperty("FriendlyId")
	private String friendlyId;
	@JsonProperty("Id")
	private int id;
	@JsonProperty("Name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getFriendlyId() {
		return friendlyId;
	}

	public void setFriendlyId(String friendlyId) {
		this.friendlyId = friendlyId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TrailLocationDTO [friendlyId=" + friendlyId + ", id=" + id + ", name=" + name + "]";
	}
	
}
