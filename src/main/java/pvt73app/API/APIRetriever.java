package pvt73app.API;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIRetriever {
	private static final String API_KEY = "7ea7ade21aae4f7d89073bb8047d07cf";
	private static final String ENDPOINT = "http://api.stockholm.se/ServiceGuideService/";
	private static final String TRAILS_URL = ENDPOINT + "ServiceUnitTypes/a4116a6a-af53-4672-b492-01d7adeae987/ServiceUnits/json?apikey="+API_KEY;
	private static final String TRAIL_URL = ENDPOINT + "ServiceUnits/%s/Attributes/json?apikey=" + API_KEY; 

	private ObjectMapper mapper = new ObjectMapper();

	public List<TrailDTO> getTrails() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<TrailDTO[]> responseEntity = restTemplate.getForEntity(TRAILS_URL, TrailDTO[].class);
		TrailDTO[] objects = responseEntity.getBody();
		return Arrays.asList(objects);
	}

	public List<TrailAttributeDTO> getTrailAttributes(String id) {
		List<TrailAttributeDTO> attributes = new ArrayList<>();
		String url = String.format(TRAIL_URL, id);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JsonNode[]> responseEntity = restTemplate.getForEntity(url, JsonNode[].class);
		JsonNode[] objects = responseEntity.getBody();

		for (JsonNode node : objects) {
			JsonNode value = node.get("Value");

			try {
				if (value != null) {
					if (value.isTextual()) {
						attributes.add(mapper.treeToValue(node, TrailAttributeStringValueDTO.class));
					} else if (value.isObject()) {
						attributes.add(mapper.treeToValue(node, TrailAttributeObjectValueDTO.class));
					}
				} else {
					value = node.get("Values");
					if (value != null && value.isArray()) {
						attributes.add(mapper.treeToValue(node, TrailAttributeValuesArrayDTO.class));
					}
				}
			} catch (Exception e) {

			}
		}

		return attributes;
	}

	public String getIdUrl(String id) {
		return String.format(TRAIL_URL, id);
	}

}
