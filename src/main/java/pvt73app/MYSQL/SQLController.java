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
import pvt73app.Coordinates.DistanceGPSCoordinates;

import java.sql.Date;
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
	@Autowired
	private ChallengeAttributesRepository challengeAttributesRepository;
	@Autowired
	private ChallengeConnectorRepository challengeConnectorRepository;
	@Autowired
	private GroupChallengeConnectRepository groupChallengeConnectRepository;
	@Autowired
	private TrailReviewRepository trailReviewRepository;
	@Autowired
	private UserTrailsRepository userTrailsRepository;

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
	@RequestMapping(value = "/addChallenge", method = RequestMethod.GET)
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
	@RequestMapping(value = "/updateChallenge", method = RequestMethod.GET)
	public @ResponseBody String updateChallenge(
			@RequestParam(required = true) int id,
			Integer tid,
			Integer caid,
			String name) throws ResourceNotFoundException {
		Challenge challenge = challengeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Utmaning finns inte - id :: " + id));

		//		System.out.println("tid: " + tid + " caid: " + caid + " name: "+ name);
		if(tid != null)
			challenge.setTid(tid);
		if(caid != null)
			challenge.setCaid(caid);
		if(name != null)
			challenge.setName(name);

		final Challenge updatedChallenge = challengeRepository.save(challenge);
		if(updatedChallenge != null)
			return "Updated";
		else
			return "No update";
	}

	//    @DeleteMapping("/challenges/{id}")
	@RequestMapping(value = "/deleteChallenge/{id}", method = RequestMethod.GET)
	public Map<String, Boolean> deleteChallenge(@PathVariable(value = "id") int id)
			throws ResourceNotFoundException {
		Challenge challenge = challengeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Utmaning finns inte - id :: " + id));
		challengeRepository.delete(challenge);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	//ChallengeAttributes START
	@GetMapping("/challengeAttributes")
	public List<ChallengeAttributes> getAllChallengeAttributes() {
		return (List<ChallengeAttributes>) challengeAttributesRepository.findAll();
	}

	@GetMapping("/challengeAttributes/{id}")
	public ResponseEntity<ChallengeAttributes> getChallengeAttributesById(@PathVariable(value = "id") int id)
			throws ResourceNotFoundException {
		ChallengeAttributes challengeAttributes = challengeAttributesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ChallengeAttributes finns inte - id :: " + id));
		return ResponseEntity.ok().body(challengeAttributes);
	}

	@RequestMapping(value = "/addChallengeAttributes", method = RequestMethod.GET)
	public @ResponseBody String createChallengeAttributes(
			@RequestParam(required = false) Integer count,
			@RequestParam(required = false) Date time,
			@RequestParam(required = true) Date startdate,
			@RequestParam(required = true) Date enddate){
		ChallengeAttributes challengeAttributes = new ChallengeAttributes();
		challengeAttributes.setTime(time);
		challengeAttributes.setCount(count);
		challengeAttributes.setStartdate(startdate);
		challengeAttributes.setEnddate(enddate);
		challengeAttributes.setComplete(false);
		challengeAttributesRepository.save(challengeAttributes);
		return "Saved"; 
	}

	//    @PutMapping("/updatechallengeAttributes/{id}")
	//	http://pvt73back.azurewebsites.net//updatechallengeAttributes?id=int&tid=int&caid=int&name="string"
	@RequestMapping(value = "/updateChallengeAttributes", method = RequestMethod.GET)
	public @ResponseBody String updateChallengeAttributes(
			@RequestParam(required = true) int id,
			@RequestParam(required = false) Integer count,
			@RequestParam(required = false) Date time,
			@RequestParam(required = false) Date startdate,
			@RequestParam(required = false) Date enddate,
			@RequestParam(required = false) boolean complete)  throws ResourceNotFoundException {
		ChallengeAttributes challengeAttributes = challengeAttributesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ChallengeAttributes finns inte - id :: " + id));

		//		System.out.println("tid: " + tid + " caid: " + caid + " name: "+ name);
		if(time != null)
			challengeAttributes.setTime(time);
		if(count != null)
			challengeAttributes.setCount(count);
		if(startdate != null)
			challengeAttributes.setStartdate(startdate);
		if(enddate != null)
			challengeAttributes.setEnddate(enddate);
		if(challengeAttributes.getComplete() == true)
			challengeAttributes.setComplete(true);
		else
			challengeAttributes.setComplete(complete);

		final ChallengeAttributes updatedChallengeAttributes = challengeAttributesRepository.save(challengeAttributes);
		if(updatedChallengeAttributes != null)
			return "Updated";
		else
			return "No update";
	}

	//    @DeleteMapping("/challengeAttributesattributes/{id}")
	@RequestMapping(value = "/deleteChallengeAttributes/{id}", method = RequestMethod.GET)
	public Map<String, Boolean> deleteChallengeAttributes(@PathVariable(value = "id") int id)
			throws ResourceNotFoundException {
		ChallengeAttributes challengeAttributes = challengeAttributesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ChallengeAttribute finns inte - id :: " + id));
		challengeAttributesRepository.delete(challengeAttributes);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	//ChallengeConnector START
	@GetMapping("/challengeConnector")
	public List<ChallengeConnector> getAllChallengeConnector() {
		return (List<ChallengeConnector>) challengeConnectorRepository.findAll();
	}

	@GetMapping("/challengeConnector/{cid}")
	public ResponseEntity<ChallengeConnector> getChallengeConnectorById(@PathVariable(value = "cid") int cid)
			throws ResourceNotFoundException {
		ChallengeConnector challengeConnector = challengeConnectorRepository.findByCid(cid)
				.orElseThrow(() -> new ResourceNotFoundException("ChallengeConnector finns inte - cid :: " + cid));
		return ResponseEntity.ok().body(challengeConnector);
	}

	@RequestMapping(value = "/addChallengeConnector", method = RequestMethod.GET)
	public @ResponseBody String createChallengeConnector(
			@RequestParam(required = true) Integer caid,
			@RequestParam(required = true) Integer cid,
			@RequestParam(required = true) Integer tid){
		ChallengeConnector challengeConnector = new ChallengeConnector();
		challengeConnector.setCaid(caid);
		challengeConnector.setCid(cid);
		challengeConnector.setTid(tid);

		challengeConnectorRepository.save(challengeConnector);
		return "Saved"; 
	}

	//    @PutMapping("/updatechallengeConnector/{id}")
	//	http://pvt73back.azurewebsites.net//updatechallengeConnector?id=int&tid=int&caid=int&name="string"
	@RequestMapping(value = "/updateChallengeConnector", method = RequestMethod.GET)
	public @ResponseBody String updateChallengeConnector(
			@RequestParam(required = true) Integer caid,
			@RequestParam(required = true) Integer cid,
			@RequestParam(required = true) Integer tid)  throws ResourceNotFoundException {
		ChallengeConnector challengeConnector = challengeConnectorRepository.findByCid(cid)
				.orElseThrow(() -> new ResourceNotFoundException("ChallengeConnector finns inte - cid :: " + cid));

		//		System.out.println("tid: " + tid + " caid: " + caid + " name: "+ name);
		if(caid != null)
			challengeConnector.setCaid(caid);
		if(cid != null)
			challengeConnector.setCid(cid);
		if(tid != null)
			challengeConnector.setTid(tid);

		final ChallengeConnector updatedChallengeConnector = challengeConnectorRepository.save(challengeConnector);
		if(updatedChallengeConnector != null)
			return "Updated";
		else
			return "No update";
	}

	//    @DeleteMapping("/challengeConnectorattributes/{id}")
	@RequestMapping(value = "/deleteChallengeConnector/{id}", method = RequestMethod.GET)
	public Map<String, Boolean> deleteChallengeConnector(@PathVariable(value = "cid") int cid)
			throws ResourceNotFoundException {
		ChallengeConnector challengeConnector = challengeConnectorRepository.findByCid(cid)
				.orElseThrow(() -> new ResourceNotFoundException("ChallengeAttribute finns inte - cid :: " + cid));
		challengeConnectorRepository.delete(challengeConnector);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	//GroupChallengeConnect START
	@GetMapping("/groupChallengeConnect")
	public List<GroupChallengeConnect> getAllGroupChallengeConnect() {
		return (List<GroupChallengeConnect>) groupChallengeConnectRepository.findAll();
	}

	@GetMapping("/groupChallengeConnect/{cid}")
	public ResponseEntity<GroupChallengeConnect> getGroupChallengeConnectById(@PathVariable(value = "cid") int cid)
			throws ResourceNotFoundException {
		GroupChallengeConnect groupChallengeConnect = groupChallengeConnectRepository.findByCid(cid)
				.orElseThrow(() -> new ResourceNotFoundException("GroupChallengeConnect finns inte - cid :: " + cid));
		return ResponseEntity.ok().body(groupChallengeConnect);
	}

	@RequestMapping(value = "/addGroupChallengeConnect", method = RequestMethod.GET)
	public @ResponseBody String createGroupChallengeConnect(
			@RequestParam(required = true) Integer gid,
			@RequestParam(required = true) Integer cid){
		GroupChallengeConnect groupChallengeConnect = new GroupChallengeConnect();
		groupChallengeConnect.setGid(gid);
		groupChallengeConnect.setCid(cid);

		groupChallengeConnectRepository.save(groupChallengeConnect);
		return "Saved"; 
	}

	//    @PutMapping("/updategroupChallengeConnect/{id}")
	//	http://pvt73back.azurewebsites.net//updategroupChallengeConnect?
	@RequestMapping(value = "/updateGroupChallengeConnect", method = RequestMethod.GET)
	public @ResponseBody String updateGroupChallengeConnect(
			@RequestParam(required = true) Integer gid,
			@RequestParam(required = true) Integer cid) throws ResourceNotFoundException {
		GroupChallengeConnect groupChallengeConnect = groupChallengeConnectRepository.findByCid(cid)
				.orElseThrow(() -> new ResourceNotFoundException("GroupChallengeConnect finns inte - cid :: " + cid));

		//		System.out.println("tid: " + tid + " caid: " + caid + " name: "+ name);
		if(gid != null)
			groupChallengeConnect.setGid(gid);
		if(cid != null)
			groupChallengeConnect.setCid(cid);

		final GroupChallengeConnect updatedGroupChallengeConnect = groupChallengeConnectRepository.save(groupChallengeConnect);
		if(updatedGroupChallengeConnect != null)
			return "Updated";
		else
			return "No update";
	}

	//    @DeleteMapping("/groupChallengeConnectattributes/{id}")
	@RequestMapping(value = "/deleteGroupChallengeConnect/{cid}", method = RequestMethod.GET)
	public Map<String, Boolean> deleteGroupChallengeConnect(@PathVariable(value = "cid") int cid)
			throws ResourceNotFoundException {
		GroupChallengeConnect groupChallengeConnect = groupChallengeConnectRepository.findByCid(cid)
				.orElseThrow(() -> new ResourceNotFoundException("ChallengeAttribute finns inte - cid :: " + cid));
		groupChallengeConnectRepository.delete(groupChallengeConnect);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	//TrailReview START
	@GetMapping("/trailReview")
	public List<TrailReview> getAllTrailReview() {
		return (List<TrailReview>) trailReviewRepository.findAll();
	}

	@GetMapping("/trailReview/{tid}")
	public ResponseEntity<TrailReview> getTrailReviewById(@PathVariable(value = "tid") int tid)
			throws ResourceNotFoundException {
		TrailReview trailReview = trailReviewRepository.findByTid(tid)
				.orElseThrow(() -> new ResourceNotFoundException("TrailReview finns inte - tid :: " + tid));
		return ResponseEntity.ok().body(trailReview);
	}

	@RequestMapping(value = "/addTrailReview", method = RequestMethod.GET)
	public @ResponseBody String createTrailReview(
			@RequestParam(required = true) int tid,
			@RequestParam(required = true) int uid,
			@RequestParam(required = false)String review,
			@RequestParam(required = false)int rating,
			@RequestParam(required = false)Date date,
			@RequestParam(required = false)String title){
		TrailReview trailReview = new TrailReview();
		trailReview.setTid(tid);
		trailReview.setUid(uid);
		trailReview.setReview(review);
		trailReview.setRating(rating);
		trailReview.setDate(date);
		trailReview.setTitle(title);

		trailReviewRepository.save(trailReview);
		return "Saved"; 
	}

	//    @PutMapping("/updatetrailReview/{id}")
	//	http://pvt73back.azurewebsites.net//updatetrailReview?
	@RequestMapping(value = "/updateTrailReview", method = RequestMethod.GET)
	public @ResponseBody String updateTrailReview(
			@RequestParam(required = true) int tid,
			@RequestParam(required = true) int uid,
			@RequestParam(required = false) String review,
			@RequestParam(required = false) Integer rating,
			@RequestParam(required = false) Date date,
			@RequestParam(required = false) String title
			) throws ResourceNotFoundException {
		TrailReview trailReview = trailReviewRepository.findByTid(tid)
				.orElseThrow(() -> new ResourceNotFoundException("TrailReview finns inte - tid :: " + tid));

		//		System.out.println("tid: " + tid + " caid: " + caid + " name: "+ name);
		if(review != null)
			trailReview.setReview(review);
		if(rating != null)
			trailReview.setRating(rating);
		if(date!= null)
			trailReview.setDate(date);
		if(title != null)
			trailReview.setTitle(title);

		final TrailReview updatedTrailReview = trailReviewRepository.save(trailReview);
		if(updatedTrailReview != null)
			return "Updated";
		else
			return "No update";
	}

	//    @DeleteMapping("/trailReviewattributes/{id}")
	@RequestMapping(value = "/deleteTrailReview/{id}", method = RequestMethod.GET)
	public Map<String, Boolean> deleteTrailReview(@PathVariable(value = "tid") int tid)
			throws ResourceNotFoundException {
		TrailReview trailReview = trailReviewRepository.findByTid(tid)
				.orElseThrow(() -> new ResourceNotFoundException("ChallengeAttribute finns inte - tid :: " + tid));
		trailReviewRepository.delete(trailReview);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	//UserRuns START
	@GetMapping("/userRuns")
	public List<UserRuns> getAllUserRuns() {
		return (List<UserRuns>) userRunsRepository.findAll();
	}

	@GetMapping("/userRuns/{id}")
	public ResponseEntity<UserRuns> getUserRunsById(@PathVariable(value = "id") int id)
			throws ResourceNotFoundException {
		UserRuns userRuns = userRunsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("UserRuns finns inte - id :: " + id));
		return ResponseEntity.ok().body(userRuns);
	}

	@RequestMapping(value = "/addUserRuns", method = RequestMethod.GET)
	public @ResponseBody String createUserRuns(
			@RequestParam(required = true) int uid,
			@RequestParam(required = true) int tid,
			@RequestParam(required = true) Date date,
			@RequestParam(required = true) double time,
			@RequestParam(required = true) int length,
			@RequestParam(required = false) String comment
			){
		UserRuns userRuns = new UserRuns();
		userRuns.setUserId(uid);
		userRuns.setTid(tid);
		userRuns.setDate(date);
		userRuns.setTime(time);
		userRuns.setLength(length);
		userRuns.setComment(comment);

		userRunsRepository.save(userRuns);
		return "Saved"; 
	}

	//    @PutMapping("/updateuserRuns/{id}")
	//	http://pvt73back.azurewebsites.net//updateuserRuns?
	@RequestMapping(value = "/updateUserRuns", method = RequestMethod.GET)
	public @ResponseBody String updateUserRuns(
			@RequestParam(required = true) int id,
			@RequestParam(required = true) int uid,
			@RequestParam(required = true) int tid,
			@RequestParam(required = false) Date date,
			@RequestParam(required = false) Double time,
			@RequestParam(required = false) Integer length,
			@RequestParam(required = false) String comment
			) throws ResourceNotFoundException {
		UserRuns userRuns = userRunsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("UserRuns finns inte - id :: " + id));

		//		System.out.println("tid: " + tid + " caid: " + caid + " name: "+ name);

		if(date != null)
			userRuns.setDate(date);
		if(time != null)
			userRuns.setTime(time);
		if(length != null)
			userRuns.setLength(length);
		if(comment != null)
			userRuns.setComment(comment);

		final UserRuns updatedUserRuns = userRunsRepository.save(userRuns);
		if(updatedUserRuns != null)
			return "Updated";
		else
			return "No update";
	}

	//    @DeleteMapping("/userRunsattributes/{id}")
	@RequestMapping(value = "/deleteUserRuns/{id}", method = RequestMethod.GET)
	public Map<String, Boolean> deleteUserRuns(@PathVariable(value = "id") int id)
			throws ResourceNotFoundException {
		UserRuns userRuns = userRunsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ChallengeAttribute finns inte - id :: " + id));
		userRunsRepository.delete(userRuns);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	//UserTrails START
	@GetMapping("/userTrails")
	public List<UserTrails> getAllUserTrails() {
		return (List<UserTrails>) userTrailsRepository.findAll();
	}
	
	@GetMapping("/userTrails/{uid}")
	public ResponseEntity<UserTrails> getUserTrailsById(@PathVariable(value = "uid") int uid)
			throws ResourceNotFoundException {
		UserTrails userTrails = userTrailsRepository.findByUid(uid)
				.orElseThrow(() -> new ResourceNotFoundException("UserTrails finns inte - uid :: " + uid));
		return ResponseEntity.ok().body(userTrails);
	}
	
	@RequestMapping(value = "/addUserTrails", method = RequestMethod.GET)
	public @ResponseBody String createUserTrails(
			@RequestParam(required = true) int tid,
			@RequestParam(required = true) int uid,
			@RequestParam(required = false) boolean favourite
			){
		UserTrails userTrails = new UserTrails();
		userTrails.setTid(tid);
		userTrails.setUid(uid);
		userTrails.setFavourite(favourite);
		
		userTrailsRepository.save(userTrails);
		return "Saved"; 
	}
	
	//    @PutMapping("/updateuserTrails/{id}")
	//	http://pvt73back.azurewebsites.net//updateuserTrails?
	@RequestMapping(value = "/updateUserTrails", method = RequestMethod.GET)
	public @ResponseBody String updateUserTrails(
			@RequestParam(required = true) int tid,
			@RequestParam(required = true) int uid,
			@RequestParam(required = false) boolean favourite
			) throws ResourceNotFoundException {
		UserTrails userTrails = userTrailsRepository.findByUid(uid)
				.orElseThrow(() -> new ResourceNotFoundException("UserTrails finns inte - uid :: " + uid));
		
		//		System.out.println("tid: " + tid + " caid: " + caid + " name: "+ name);

		
		if(userTrails.getFavourite() == true)
			userTrails.setFavourite(true);
		else
			userTrails.setFavourite(favourite);
		
		final UserTrails updatedUserTrails = userTrailsRepository.save(userTrails);
		if(updatedUserTrails != null)
			return "Updated";
		else
			return "No update";
	}
	
	//    @DeleteMapping("/userTrailsattributes/{id}")
	@RequestMapping(value = "/deleteUserTrails/{id}", method = RequestMethod.GET)
	public Map<String, Boolean> deleteUserTrails(@PathVariable(value = "id") int id)
			throws ResourceNotFoundException {
		UserTrails userTrails = userTrailsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ChallengeAttribute finns inte - id :: " + id));
		userTrailsRepository.delete(userTrails);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}



	@GetMapping(path = "/addUser")
	public @ResponseBody String addNewUser(@RequestParam(required = true) String fname,
			@RequestParam(required = true) String lname, @RequestParam(required = true) String email, String photo) {

		//		System.out.println("" + fname + " " + lname + " " + email);

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

//	// /addUserRun?uid=1333&tid=namnppåspår&
//	@GetMapping(path = "/addUserRun")
//	public @ResponseBody String addUserRun(@RequestParam(required = true) int uid,
//			@RequestParam(required = true) String tid, @RequestParam(required = true) Timestamp date,
//			@RequestParam(required = true) double time, @RequestParam(required = true) int length, String comment) {
//
//		UserRuns ur = new UserRuns();
//		ur.setUserId(uid);
//		ur.setTid(tid);
//		ur.setDate(date);
//		ur.setTime(time);
//		ur.setLength(length);
//		if (comment != null)
//			ur.setComment(comment);
//		else {
//			String tmp = " ";
//			ur.setComment(tmp);
//		}
//		userRunsRepository.save(ur);
//		return "Saved";
//	}

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
	@GetMapping(path = "/allTrailsSortedByLocation")
	public @ResponseBody Iterable<Trail> getAllTrailsByGeoLocation(@RequestParam double lat, @RequestParam double lon) {
		List<Trail> trails = getAllTrailsList();
		trails.sort(new TrailsGeoLocationComparator(lat, lon));
		return trails;
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
		//		Trail item = trailList.get(0);
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

	@RequestMapping(value = "/updateTrail", method = RequestMethod.GET)
	public @ResponseBody String updateTrail(
			@RequestParam(required = true) int id,
			String trailid,
			String trailname,
			Double geoLocationX,
			Double geoLocationY,
			String description,
			String location,
			String image) throws ResourceNotFoundException {
		Trail trail = trailRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Motionsspår finns inte - id :: " + id));
		if(trailname != null)
			trail.setTrailName(trailname);
		if(geoLocationX != null)
			trail.setGeoLocationX(geoLocationX);
		if(geoLocationY != null)
			trail.setGeoLocationY(geoLocationY);
		if(description != null)
			trail.setDescription(description);
		if(location != null)
			trail.setLocation(location);
		if(image != null)
			trail.setImage(image);

		final Trail updatedTrail = trailRepository.save(trail);
		if(updatedTrail != null)
			return "Updated";
		else
			return "No update";
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
	
	@GetMapping(path = "/getDistance")
	public @ResponseBody double getDistance(@RequestParam (required = true) double x1,
			@RequestParam (required = true) double y1, @RequestParam(required = true) double x2,
			@RequestParam(required = true) double y2) {
		double dist = ( DistanceGPSCoordinates.gpsDistance(x1, y1, x2, y2)); //in meters
		dist /= 10;
		dist = (int) dist;
		return dist / 100;

	}
	
	
	


}
