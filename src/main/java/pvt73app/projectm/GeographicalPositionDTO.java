package pvt73app.projectm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GeographicalPositionDTO {
	@JsonProperty("X")
	private long x;
	@JsonProperty("Y")
	private long y;
	
	public GeographicalPositionDTO() {
	}
	
	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "GeographicalPosition {x=" + x + ", y=" + y + "}";
	}
}

