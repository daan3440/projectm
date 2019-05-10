package pvt73app.Coordinates;

public class DistanceGPSCoordinates {

	// https://stackoverflow.com/questions/837872/calculate-distance-in-meters-when-you-know-longitude-and-latitude-in-java
	// Espen Herseth Halvorsen 2009/05/08.
	// hämtad 2019/05/10
	public static float gpsDistance(double pos1X, double pos1Y, double pos2X, double pos2Y) {
		double earthRadius = 6371000; //meters
		double dLat = Math.toRadians(pos1X - pos2X);
		double dLon = Math.toRadians(pos1Y - pos2Y);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(pos2X))
				* Math.cos(Math.toRadians(pos1X)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		float dist = (float) (earthRadius * c);

		return dist;
	}

}
