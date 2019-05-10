package pvt73app.MYSQL;

import java.util.Comparator;
import pvt73app.Coordinates.DistanceGPSCoordinates;

public class TrailsGeoLocationComparator implements Comparator<Trail> {
	private double lat, lon;
	
	public TrailsGeoLocationComparator(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
		
	@Override
	public int compare(Trail trail1, Trail trail2) {
		return (int) (DistanceGPSCoordinates.gpsDistance(trail1.getGeoLocationX(), trail1.getGeoLocationY(), lat, lon)-
				DistanceGPSCoordinates.gpsDistance(trail2.getGeoLocationX(), trail2.getGeoLocationY(), lat, lon));
	}

}
