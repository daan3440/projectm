package pvt73app.projectm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pvt73app.API.APIRetriever;
import pvt73app.API.TrailAttributeDTO;

public class APIRetrieverTest {
	private APIRetriever api = new APIRetriever();
	private static final String KARRTORPSSPÅRET_ID = "0191b0b3-1812-4ddd-a833-c69111551bad";
	private static final String CORRECT_URL = "http://api.stockholm.se/ServiceGuideService/ServiceUnits/0191b0b3-1812-4ddd-a833-c69111551bad/Attributes/json?apikey=7ea7ade21aae4f7d89073bb8047d07cf";
	private List<String> attributeGroupNames = new ArrayList<>();

	private void createAttributeGroupNamesList() {
		attributeGroupNames.add("Kontaktpersoner");
		attributeGroupNames.add("Beskrivning av enheten");
		attributeGroupNames.add("Relaterade dokument");
		attributeGroupNames.add("Bilder och film");
		attributeGroupNames.add("Enhetens kontaktuppgifter");
		attributeGroupNames.add("Relaterade länkar");
	}

//	@Test
//	public void getTrailAttributeTest() {
//		createAttributeGroupNamesList();
//		List<TrailAttributeDTO> attributes = api.getTrailAttributes(KARRTORPSSPÅRET_ID); 
//		
//		for(TrailAttributeDTO a : attributes) {
//			assertTrue(attributeGroupNames.contains(a.getGroup()));
//		}
//		
//		assertEquals(13, attributes.size());
//	}

//	@Test
//	public void getTrailsTest() {
//		List<TrailDTO> trails = api.getTrails(); 
//		
////		for(TrailDTO t : trails)
////			System.out.println(t);
//		
//		assertEquals(20, trails.size());
//	}

	@Test
	public void getStringIdTest() {
		assertEquals(CORRECT_URL, api.getIdUrl(KARRTORPSSPÅRET_ID));
	}

}
