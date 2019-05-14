package pvt73app.projectm;

public class GeoLocText {
	private String geoLoc;
	private boolean bool;
	
	public GeoLocText(String text, boolean bool) {
		this.geoLoc=text;
		this.bool = bool;
	}

	public String getGeoLoc() {
		return geoLoc;
	}

	public void setGeoLoc(String geoLoc) {
		this.geoLoc = geoLoc;
	}

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}
	
	
	
}
