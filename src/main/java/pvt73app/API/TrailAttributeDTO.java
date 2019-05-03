package pvt73app.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TrailAttributeDTO {
	//private Description, är null på de som har kollats
	@JsonProperty("Group")
	private String group;
	//private GroupDescription, är null på de som har kollats
	@JsonProperty("Id")
	private String id;
	@JsonProperty("Name")
	private String name;
	
	public TrailAttributeDTO() {
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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

	@Override
	public String toString() {
		return "TrailAttributeDTO: {group=" + group + ", id=" + id + ", name=" + name + "}";
	}
	
	
}
