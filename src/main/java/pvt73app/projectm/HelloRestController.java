package pvt73app.projectm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pvt73app.FTP.FTPController;

@RestController
public class HelloRestController {

//	@RequestMapping("/error")
//	public String error() {
//		return "Något är fel din pajas.";
//	}

//	@RequestMapping("/sayHello")
//	public String greeting(
//			@RequestParam(value="name", defaultValue="World") String name){
//		//String name now has the value from the url
//		return String.format("Hello, %s.", name);
//	}

	// https://pvt.dsv.su.se/ptvxx/sayHello?Moon
	@RequestMapping(value = "/sayHello/{name}", method = RequestMethod.GET)
	public String greeting(@PathVariable String name) { // String name now has the value from the url
		return String.format("Hello, %s.", name);
	}

	@GetMapping("/sayHelloSimple")
	public String greeting() {
		return "Hello Pvt73";
	}

	@GetMapping("/hello")
	public Collection<String> sayHello() {
		return IntStream.range(0, 10).mapToObj(i -> "Hello number " + i).collect(Collectors.toList());
	}

	@GetMapping("/time")
	public String time() {
		return Calendar.getInstance().getTime().toString();
	}

	@GetMapping("/sayHelloBomb")
	public String hejBomb() {
		return "Hej Bomben 2";
	}

	@GetMapping("/sayHelloBomb3")
	public String hejBomb3() {
		return "Hej Bomben 3";
	}

	@CrossOrigin
	@RequestMapping("/hejBomb")
	public HejBomb hejBomb(@RequestParam(required = false, defaultValue = "TEST") String name) {
		return new HejBomb(name);
	}

	@CrossOrigin
	@GetMapping("/geoLoc")
	public GeoLocText GeoLoc(@RequestParam double lat, @RequestParam double lon) {
		return new GeoLocText("lat = " + lat + " long = " + lon);
	}

	@RequestMapping("/trailImage")
	@ResponseBody
	public HttpEntity<byte[]> getPhoto(@RequestParam (required=false, defaultValue="default") String id) throws IOException, URISyntaxException {
		File file = File.createTempFile("tmp", ".jpg");
		if (!id.equals("default")) {
			InputStream is = new URL(
					String.format("http://api.stockholm.se/ServiceGuideService/ImageFiles/%s/Data?apikey=7ea7ade21aae4f7d89073bb8047d07cf", id))
							.openStream();
			FileUtils.copyInputStreamToFile(is, file);
		} else {
			file = new File("C:/Users/erikl/Pictures/Saved Pictures/fangace.png");
		}
		
		if(id.equals("653df67f-4859-4af3-a6f6-955a592f572d2")) {
			FileInputStream input = new FileInputStream(file);
			MultipartFile multipartFile = new MockMultipartFile("file",
					file.getName(), "text/plain", IOUtils.toByteArray(input));
			
			//new FTPController().handleFileUpload(multipartFile, );
		}
		
//		File file = new File("src/test/resources/input.txt");
		
	
		
		byte[] image = org.apache.commons.io.FileUtils.readFileToByteArray(file);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		headers.setContentLength(image.length);
		return new HttpEntity<byte[]>(image, headers);
	}

//	@RequestMapping("/sayHello")
//	public class IndexController  {
//
//		@RequestMapping(method = RequestMethod.GET) String get(){
//			return "Hello from get";
//		}
//		@RequestMapping(method = RequestMethod.DELETE) String delete(){
//			return "Hello from delete";
//		}
//		//		RESTful Endpoints
//		//		• In a RestController you can also specify the verb used
//		@RequestMapping(method = RequestMethod.POST) String post(){
//			return "Hello from post";
//		}
//		@RequestMapping(method = RequestMethod.PUT) String put(){
//			return "Hello from put";
//		}
//		@RequestMapping(method = RequestMethod.PATCH) String patch(){
//			return "Hello from patch";
//		}
//		
//	}

}
