package pvt73app.API;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TrailAttributeObjectValueDTO extends TrailAttributeDTO {
	@JsonProperty("Value")
	private Map<String, String> value;

	public Map<String, String> getValue() {
		return value;
	}

	public void setValue(Map<String, String> value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TrailAttributeObjectValueDTO [value=" + value + ", getGroup()=" + getGroup() + ", getId()=" + getId()
				+ ", getName()=" + getName() + "]";
	}
	
}
