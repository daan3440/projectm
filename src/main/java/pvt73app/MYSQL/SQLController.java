package pvt73app.MYSQL;

import org.hibernate.internal.IteratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pvt73app.API.APIRetriever;
import pvt73app.API.TrailDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import javax.validation.Valid;

@RestController
@RequestMapping
public class SQLController {
	private APIRetriever api = new APIRetriever();

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRunsRepository userRunsRepository;
	@Autowired
	private TrailRepository trailRepository;
	@Autowired
	private UserGroupRepository groupRepository; 
	@Autowired
	private ChallengeRepository challengeRepository;
	
	@GetMapping("/hejSQL")
	public String hejSQL() {
		return "SQL up!";
	}

    @GetMapping("/challenges")
    public List<Challenge> getAllChallenges() {
        return (List<Challenge>) challengeRepository.findAll();
    }

    @GetMapping("/challenge/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable(value = "id") int id)
      throws ResourceNotFoundException {
        Challenge challenge = challengeRepository.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Utmaning finns inte - id :: " + id));
        return ResponseEntity.ok().body(challenge);
    }

//    @PostMapping("/addchallenge")
    @RequestMapping(value = "/addchallenge", method = RequestMethod.GET)
    public @ResponseBody String createChallenge(@RequestParam(required = true) int tid,
			@RequestParam(required = true) int caid, @RequestParam(required = true) String name){
    	 Challenge challenge = new Challenge();
    	 challenge.setTid(tid);
    	 challenge.setCaid(caid);
    	 challenge.setName(name);
        		challengeRepository.save(challenge);
        		return "Saved"; 
    }

//    @PutMapping("/updatechallenge/{id}")
//	http://pvt73back.azurewebsites.net//updatechallenge?id=int&tid=int&caid=int&name="string"
    @RequestMapping(value = "/updatechallenge", method = RequestMethod.GET)
    public @ResponseBody String updateChallenge(
    		@RequestParam(required = true) int id,
    		Integer tid,
    		Integer caid,
    		String name) throws ResourceNotFoundException {
    	Challenge challenge = challengeRepository.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Utmaning finns inte - id :: " + id));

    	System.out.println("tid: " + tid + " caid: " + caid + " name: "+ name);
    	challenge.setTid(tid);
    	challenge.setCaid(caid);
    	challenge.setName(name);
        
    	final Challenge updatedChallenge = challengeRepository.save(challenge);
        if(updatedChallenge != null)
        	return "Updated";
        else
        	return "No update";
    }

//    @DeleteMapping("/challenges/{id}")
    @RequestMapping(value = "/deletechallenge/{id}", method = RequestMethod.GET)
    public Map<String, Boolean> deleteChallenge(@PathVariable(value = "id") int id)
      throws ResourceNotFoundException {
        Challenge challenge = challengeRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Utmaning finns inte - id :: " + id));

        challengeRepository.delete(challenge);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    
	@GetMapping(path = "/addUser")
	public @ResponseBody String addNewUser(@RequestParam(required = true) String fname,
			@RequestParam(required = true) String lname, @RequestParam(required = true) String email, String photo) {

		System.out.println("" + fname + " " + lname + " " + email);

		User n = new User();
		n.setFname(fname);
		n.setLname(lname);
		n.setEmail(email);
		if (photo != null)
			n.setPhoto(photo);
		else {
			String tmp = "no picture";
			n.setPhoto(tmp);
		}
		userRepository.save(n);
		return "Saved";
	}

	// /addUserRun?uid=1333&tid=namnppåspår&
	@GetMapping(path = "/addUserRun")
	public @ResponseBody String addUserRun(@RequestParam(required = true) int uid,
			@RequestParam(required = true) String tid, @RequestParam(required = true) Timestamp date,
			@RequestParam(required = true) double time, @RequestParam(required = true) int length, String comment) {

		UserRuns ur = new UserRuns();
		ur.setUserId(uid);
		ur.setTid(tid);
		ur.setDate(date);
		ur.setTime(time);
		ur.setLength(length);
		if (comment != null)
			ur.setComment(comment);
		else {
			String tmp = " ";
			ur.setComment(tmp);
		}
		userRunsRepository.save(ur);
		return "Saved";
	}

	@GetMapping(path = "/addGroup")
	public @ResponseBody String addNewGroup(@RequestParam(required = true) String groupname, Integer uid) {

		Usergroup group = new Usergroup();
		group.setGroupName(groupname);
		groupRepository.save(group);
		if (group != null) {
			System.out.println("tmp not Null");
			addNewGroupConnect(group.getID(), uid);
		}
		return "Saved";
	}

	// @PostMapping
	private @ResponseBody String addNewGroupConnect(int uid, int gid) {
		UserGroupConnect ugc = new UserGroupConnect();
		ugc.setUid(uid);
		ugc.setGid(gid);
		return "Success";
	}
	
	@CrossOrigin
	@GetMapping(path = "/allUsers")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path="/allGroups")
	public @ResponseBody Iterable<Usergroup> getAllGroups() {
		return groupRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping(path = "/allTrails")
	public @ResponseBody Iterable<Trail> getAllTrails() {
		return trailRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping(path = "/getUser")
	public @ResponseBody Optional<User> getUser(@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer id) {
		if (id != null) {
			return userRepository.findById(id);
		}
		// else if (name != null) {
		// return userRepository.findByName(name).stream().findAny();
		// }
		else {
			return Optional.empty();
		}

	}

//	@Transactional
//	@CrossOrigin
//	@GetMapping(path = "/getTrails")
//	public @ResponseBody List<Trail> getAllTrailsList() {
		public List<Trail> getAllTrailsList() {
		Iterable<Trail> trails = trailRepository.findAll();
		List<Trail> trailList = new ArrayList<>();
		trails.forEach(trailList::add);
		Trail item = trailList.get(0);
		// System.out.println("Print da lista a: " +trailList.toString());
		return trailList;
	}

	@GetMapping(path="/addTrails")
	public @ResponseBody String addTrails() {
		trailRepository.saveAll(getTrailsFromDTOs());
		return "All trails added";
	}

	private List<Trail> getTrailsFromDTOs() {
		List<Trail> trails = new ArrayList<>();
		List<TrailDTO> trailDTOs = api.getTrails();
		for (TrailDTO dto : trailDTOs) {
			Trail trail = new Trail();
			trail.setTrailID(dto.getId());
			trail.setTrailName(dto.getName());
			trail.setGeoLocationX(dto.getGeoLocationX());
			trail.setGeoLocationY(dto.getGeoLocationY());
			trail.setDescription(dto.getDescription());
			trail.setLocation(dto.getLocation());
			trail.setImage(dto.getImageId());
			trails.add(trail); 
		}
		return trails;
	}
	@CrossOrigin
	@GetMapping(path="/getTrailById/{id}")
	public @ResponseBody Optional<Trail> getTrailById(@PathVariable("id") int id) {
		return trailRepository.findById(id);
	}

	@GetMapping(path = "/dropTrail/{id}")
	public @ResponseBody String dropTrailById(@PathVariable("id") int id) {
		trailRepository.deleteById(id);

		return id + " is GONE";
	}
	
	@GetMapping(path = "/dropUser/{id}")
	public @ResponseBody String dropUserById(@PathVariable("id") int id) {
		userRepository.deleteById(id);
		return id + " is GONE";
	}
	
	

}
