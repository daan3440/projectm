package pvt73app.projectm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pvt73app.API.APIRetriever;
import pvt73app.API.GeographicalPositionDTO;
import pvt73app.API.TrailAttributeDTO;
import pvt73app.API.TrailDTO;
import pvt73app.MYSQL.SQLController;
import pvt73app.MYSQL.Trail;
import pvt73app.MYSQL.TrailRepository;

public class APIRetrieverTest {
	private APIRetriever api = new APIRetriever();
	private static final String KARRTORPSSPÅRET_ID = "0191b0b3-1812-4ddd-a833-c69111551bad";
	private static final String CORRECT_URL = "http://api.stockholm.se/ServiceGuideService/ServiceUnits/0191b0b3-1812-4ddd-a833-c69111551bad/Attributes/json?apikey=7ea7ade21aae4f7d89073bb8047d07cf";
	private List<String> attributeGroupNames = new ArrayList<>();
	
	private Map<String, String> trailLocationNameById = new HashMap<>();
	private String[] trailIds = {
			"0191b0b3-1812-4ddd-a833-c69111551bad",
			"ddb08c8c-582e-4e23-860f-198db6b4d059",
			"e2d68bec-51f5-4e5d-9467-e4d39e2e041c",
			"edfc9958-f9da-4cbf-ab7c-7446fffdc448",
			"395561a3-f816-46fd-9c55-34afd436120e",
			"42413c3a-0093-4c3f-8e6a-4b8eac7471d1",
			"6c95f2fa-5b0c-420e-8e23-73d288bef0a5",
			"6b7e36c7-f1d0-4dac-9eb9-8bfead47fff8",
			"9ec4f2fc-ad89-4aec-9417-9ebea933b692",
			"c03cb5d2-f428-4963-899a-adb75ca7b2a9",
			"ccac297b-6e18-465c-945d-bb99a32d68ba",
			"122cdea2-e7cd-49ee-9c7d-bc1d3532f0cb",
			"6ea5cfa0-a9de-464f-ac5e-cff8f50c603e",
			"9f28076e-e2e9-4950-88de-da7144b04112",
			"5b4c3c93-7e3e-46ca-8153-ea7167f0ef27",
			"7716b505-e1b5-4ccf-aecb-eb6a3894b2d7",
			"b7f6266e-d153-4ab7-a632-c349fcf1eed0",
			"968eec16-a03b-4dc6-9edc-11538a1085b4",
			"8ddac468-da8a-4b3c-9051-0a1425533e9b",
			"d68206fd-e5ea-4aef-875a-d13c40949796"
	};

	private String[] trailLocations = {
			"Skarpnäck",
			"Skärholmen",
			"Skarpnäck",
			"Bromma",
			"Utanför Stockholm",
			"Hässelby-Vällingby",
			"Östermalm",
			"Bromma",
			"Östermalm",
			"Utanför Stockholm",
			"Östermalm",
			"Utanför Stockholm",
			"Skärholmen",
			"Farsta",
			"Östermalm",
			"Spånga-Tensta",
			"Hässelby-Vällingby",
			"Spånga-Tensta",
			"Älvsjö",
			"Enskede-Årsta-Vantör"
	};

	private void createTrailLocationByIdMap() {
		for(int i = 0; i<trailIds.length;i++) {
			trailLocationNameById.put(trailIds[i], trailLocations[i]);
		}
	}
	private void createAttributeGroupNamesList() {
		attributeGroupNames.add("Kontaktpersoner");
		attributeGroupNames.add("Beskrivning av enheten");
		attributeGroupNames.add("Relaterade dokument");
		attributeGroupNames.add("Bilder och film");
		attributeGroupNames.add("Enhetens kontaktuppgifter");
		attributeGroupNames.add("Relaterade länkar");
	}

	@Test
	public void getTrailAttributeTest() {
		createAttributeGroupNamesList();
		List<TrailAttributeDTO> attributes = api.getTrailAttributesFromApi(KARRTORPSSPÅRET_ID); 
		
		for(TrailAttributeDTO a : attributes) {
			assertTrue(attributeGroupNames.contains(a.getGroup()));
		}
		
		assertEquals(13, attributes.size());
	}

	@Test
	public void getTrailsFromApitest() {
		List<TrailDTO> trails = api.getTrailsFromApi();

		
		for(TrailDTO t : trails) {
			assertNull(t.getDescription());
		}

		assertEquals(20, trails.size());
	}
	
	@Test
	public void getTrailsWithGeoPosLocationDescriptionTest() {
		createTrailLocationByIdMap();
		List<TrailDTO> trails = api.getTrails();
		List<String> trailStrings = new ArrayList<>(); 
		
		for(TrailDTO t : trails) {
			assertNotNull(t.getDescription());
			assertEquals(trailLocationNameById.get(t.getId()), t.getLocation());
			trailStrings.add(t.toString());
		}
		
		
		for(String s : trailStrings) {
			System.out.println(s);
		}
		assertEquals(20, trails.size());
	}
	
	

	@Test
	public void getStringIdTest() {
		assertEquals(CORRECT_URL, api.getIdUrl(KARRTORPSSPÅRET_ID));
	}
	
	@Test
	public void getLocationTest() {
		assertEquals(trailIds.length, trailLocations.length);
		
		
		for(int i =0; i<trailIds.length;i++) {
			assertEquals(trailLocations[i], api.getTrailLocationFromApi(trailIds[i]));
		}
	}
	
	@Test
	public void getLocationFromTrailTest() {
		createTrailLocationByIdMap();
		List<TrailDTO> trails = api.getTrails();
		
		for(TrailDTO t: trails) {
			assertEquals(trailLocationNameById.get(t.getId()), t.getLocation());
		}
	}
	
	@Test
	public void geoLocationXYTest() {
		//Kärrtopsspårets position används: "X":6576068,"Y":1632168
		//WGS84 pos long 59,284853 lat 18,124188, från https://rl.se/rt90
		long karrtorpRT90X = 6576068;
		long karrtorpRT90Y = 1632168;
		double karrtorpWGS84X = 59.284853;
		double karrtorpWGS84Y = 18.124188;
		TrailDTO trail = new TrailDTO();
		GeographicalPositionDTO geoPos = new GeographicalPositionDTO();
		geoPos.setX(karrtorpRT90X);
		geoPos.setY(karrtorpRT90Y);
		trail.setGeographicalPosition(geoPos);
		trail.createWGS84GeoLocation();
		
		//0.00001d är felmarginalen på testen eftersom double inte kan testas om de matchar fullt ut.
		//Felmarginalen är tagen från Goobers koordinattest.
		assertEquals(karrtorpWGS84X, trail.getGeoLocationX(),0.00001d);
		assertEquals(karrtorpWGS84Y, trail.getGeoLocationY(),0.00001d);
	}

}