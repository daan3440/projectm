package pvt73app.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TrailAttributeStringValueDTO extends TrailAttributeDTO {
	
	@JsonProperty("Value")
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TrailAttributeStringValueDTO [value=" + value + ", getGroup()=" + getGroup() + ", getId()=" + getId()
				+ ", getName()=" + getName() + "]";
	}
	
}
