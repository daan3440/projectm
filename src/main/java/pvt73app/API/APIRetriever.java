package pvt73app.API;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pvt73app.API.TrailDTO;
import pvt73app.API.TrailLocationDTO;
import pvt73app.MYSQL.Trail;

public class APIRetriever {
	private static final String API_KEY = "7ea7ade21aae4f7d89073bb8047d07cf";
	private static final String ENDPOINT = "http://api.stockholm.se/ServiceGuideService/";
	
	private static final String VALUES = "Values";
	private static final String VALUE = "Value";
	private static final String DEFAULT_DECSRIPTION = "Beskrivning saknas";
	
	//URL för att hämta alla trails
	private static final String TRAILS_URL = ENDPOINT
			+ "ServiceUnitTypes/a4116a6a-af53-4672-b492-01d7adeae987/ServiceUnits/json?apikey=" + API_KEY;
	//URL för att hämta ett spår
	private static final String TRAIL_URL = ENDPOINT + "ServiceUnits/%s/Attributes/json?apikey=" + API_KEY;
	//URL för att hämta ett spårs stadsdel
	private static final String TRAIL_LOCATION = ENDPOINT + "ServiceUnits/%s/GeographicalAreas/json?apikey=" + API_KEY;

	private ObjectMapper mapper = new ObjectMapper();
	
	//Hämtar lista alla spår med attributer lagda.
	public List<TrailDTO> getTrails() {
		List<TrailDTO> trails = getTrailsFromApi();

		for (TrailDTO trail : trails) {
			List<TrailAttributeDTO> attributes = getTrailAttributesFromApi(trail.getId());
			trail.setLocation(getTrailLocationFromApi(trail.getId()));
			trail.createWGS84GeoLocation();

			for (TrailAttributeDTO attribute : attributes) {
				if (isAttributeDescription(attribute) && attribute instanceof TrailAttributeStringValueDTO) {
					TrailAttributeStringValueDTO trailStringAttribute = (TrailAttributeStringValueDTO) attribute;
					trail.setDescription(trailStringAttribute.getValue());
				} else if (isAttributeImage(attribute) && attribute instanceof TrailAttributeObjectValueDTO) {
					TrailAttributeObjectValueDTO objectAttribute = (TrailAttributeObjectValueDTO) attribute;
					trail.setImageId(objectAttribute.getValue().get("Id"));
				}
			}

			if (trail.getDescription() == null) {
				trail.setDescription(DEFAULT_DECSRIPTION);
			}
		}

		return trails;
	}

	//Tar in spåren från Stockholms API
	public List<TrailDTO> getTrailsFromApi() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<TrailDTO[]> responseEntity = restTemplate.getForEntity(TRAILS_URL, TrailDTO[].class);
		TrailDTO[] objects = responseEntity.getBody();
		return Arrays.asList(objects);
	}

	//Tar in ett spårs attribut från Stockholms API
	public List<TrailAttributeDTO> getTrailAttributesFromApi(String id) {
		List<TrailAttributeDTO> attributes = new ArrayList<>();
		String url = String.format(TRAIL_URL, id);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JsonNode[]> responseEntity = restTemplate.getForEntity(url, JsonNode[].class);
		JsonNode[] objects = responseEntity.getBody();

		for (JsonNode node : objects) {
			JsonNode value = node.get(VALUE);

			try {
				if (value != null) {
					if (value.isTextual()) {
						attributes.add(mapper.treeToValue(node, TrailAttributeStringValueDTO.class));
					} else if (value.isObject()) {
						attributes.add(mapper.treeToValue(node, TrailAttributeObjectValueDTO.class));
					}
				} else {
					value = node.get(VALUES);
					if (value != null && value.isArray()) {
						attributes.add(mapper.treeToValue(node, TrailAttributeValuesArrayDTO.class));
					}
				}
			} catch (Exception e) {

			}
		}

		return attributes;
	}

	private boolean isAttributeImage(TrailAttributeDTO attribute) {
		return attribute.getGroup().equals("Bilder och film") && attribute.getName().equals("Huvudbild");
	}

	private boolean isAttributeDescription(TrailAttributeDTO attribute) {
		return attribute.getGroup().equals("Beskrivning av enheten") && attribute.getId().equals("ShortDescription")
				&& attribute.getName().equals("Introduktion");
	}

	//Tar in ett spårs stadsdel från Stockholms API
	public String getTrailLocationFromApi(String trailId) {
		String locationUrl = String.format(TRAIL_LOCATION, trailId);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<TrailLocationDTO[]> responseEntity = restTemplate.getForEntity(locationUrl,
				TrailLocationDTO[].class);
		TrailLocationDTO[] locations = responseEntity.getBody();

		String locationName = "";

		for (TrailLocationDTO location : locations) {
			if (locations.length > 1) {
				if (location.getId() != 0) {
					locationName = location.getName();
				}
			} else {
				locationName = location.getName();
			}
		}

		return locationName;
	}

	//används enbart för test
	public String getIdUrl(String id) {
		return String.format(TRAIL_URL, id);
	}

}