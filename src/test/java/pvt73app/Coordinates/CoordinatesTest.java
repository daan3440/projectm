package pvt73app.Coordinates;

import static org.junit.Assert.*;
import pvt73app.Coordinates.DistanceGPSCoordinates;

import org.junit.Test;

public class CoordinatesTest {

	@Test
	public void gpsDistanceTest() {
		double x = 59.412767;
		double y = 17.932498;
		
		double x2 = 59.407282;
		double y2 = 17.923574;
		assertEquals(791,(int) DistanceGPSCoordinates.gpsDistance(x,y,x2,y2));
	}

}
