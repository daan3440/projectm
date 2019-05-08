package pvt73app.MYSQL;

import org.hibernate.internal.IteratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@RequestMapping
public class SQLController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRunsRepository userRunsRepository;
	@Autowired
	private TrailRepository trailRepository;
	@Autowired
	private UserGroupRepository groupRepository; // TODO: Make a new controller for different 
											 //repos so that we won't get cluttered code
	
	@GetMapping("/hejSQL")
	public String hejSQL() {
		return "SQL up!";
	}
	
	@GetMapping(path="/addUser")
	public @ResponseBody String addNewUser (@RequestParam(required = true) String fname,
											@RequestParam(required = true) String lname, 
											@RequestParam(required = true) String email,
											String photo) {
		
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
	@GetMapping(path="/addUserRun")	
	public @ResponseBody String addUserRun(@RequestParam(required = true) int uid,
			@RequestParam(required = true) String tid, 
			@RequestParam(required = true) Timestamp date,
			@RequestParam(required = true) double time,
			@RequestParam(required = true) int length,
			String comment) {
				
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
	
	@GetMapping(path="/addGroup")
	public @ResponseBody String addNewGroup(@RequestParam(required = true) String groupname,
											Integer uid) {

		Usergroup group = new Usergroup();
		group.setGroupName(groupname);
		groupRepository.save(group);
		if(group != null) {
			System.out.println("tmp not Null");
			addNewGroupConnect(group.getID(), uid); 
		}
		return "Saved";
	}
//	@PostMapping
	private @ResponseBody String addNewGroupConnect(int uid, int gid) {
		UserGroupConnect ugc = new UserGroupConnect();
		ugc.setUid(uid);
		ugc.setGid(gid);
		return "Success";
		}
	
	@GetMapping(path="/allUsers")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path="/allGroups")
	public @ResponseBody Iterable<Usergroup> getAllGroups() {
		return groupRepository.findAll();
	}
	
	@GetMapping(path="/allTrails")
	public @ResponseBody Iterable<Trail> getAllTrails() {
		return trailRepository.findAll(); 
				
//				.findAll();
	}

	@GetMapping(path="/getUser")
	public @ResponseBody Optional<User> getUser(@RequestParam(required = false) String name, @RequestParam(required = false) Integer id) {
		if (id != null) {
			return userRepository.findById(id);
		} 
//		else if (name != null) {
//			return userRepository.findByName(name).stream().findAny();
//		}
		else {
			return Optional.empty();
		}
		
	}



}
