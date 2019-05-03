package pvt73app.API;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TrailAttributeValuesArrayDTO extends TrailAttributeDTO {

	
	@JsonProperty("Values")
	private List<Map<String,String>> values;

	public List<Map<String, String>> getValues() {
		return values;
	}

	public void setValues(List<Map<String, String>> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "TrailAttributeValuesArrayDTO [values=" + values + ", getGroup()=" + getGroup() + ", getId()=" + getId()
				+ ", getName()=" + getName() + "]";
	}
	
}
