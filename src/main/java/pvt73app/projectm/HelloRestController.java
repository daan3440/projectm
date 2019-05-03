package pvt73app.projectm;

import java.util.Calendar;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
	
	//https://pvt.dsv.su.se/ptvxx/sayHello?Moon
	@RequestMapping(
			value="/sayHello/{name}",method=RequestMethod.GET)
			public String greeting(@PathVariable String name) { //String name now has the value from the url
		return String.format("Hello, %s.", name);
			}
	
	@GetMapping("/sayHelloSimple")
	public String greeting() {
		return "Hello Pvt73";
	}
	
	@GetMapping("/hello")
    public Collection<String> sayHello() {
        return IntStream.range(0, 10)
          .mapToObj(i -> "Hello number " + i)
          .collect(Collectors.toList());
    }
	
	@GetMapping("/time")
    public String time() {
            return Calendar.getInstance().getTime().toString();
    }

	@GetMapping("/sayHelloBomb")
	public String hejBomb() {
		return "Hej Bomben";
	}
	
	
	@CrossOrigin
	@RequestMapping("/hejBomb") 
	public HejBomb hejBomb(@RequestParam(required=false, defaultValue="TEST") String name){
		return new HejBomb(name);
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

