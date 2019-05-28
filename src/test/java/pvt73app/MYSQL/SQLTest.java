package pvt73app.MYSQL;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import pvt73app.ProjectmApplication;
	 
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectmApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
	public class SQLTest {
	
	
	 @Autowired
     private TestRestTemplate restTemplate;

     @LocalServerPort
     private int port;

     private String getRootUrl() {
         return "http://localhost:" + port;
     }

     @Test
     public void contextLoads() {

     }
     
     @Test
     public void testGetAllChallenges() {
     HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/challenges",
        HttpMethod.GET, entity, String.class);  
        assertNotNull(response.getBody());
    }
     
     @Test
     public void testGetChallengesById() {
         Challenge challenge = restTemplate.getForObject(getRootUrl() + "/challenge/1", Challenge.class);
         System.out.println(challenge.getName());
         assertNotNull(challenge);
         assertEquals(1, (long) challenge.getId());
     }
     
     @Test
     public void testCreateChallenge() {
         Challenge challenge= new Challenge ();
         challenge.setCaid(1);
         challenge.setTid(8);
         challenge.setName("Linda Lovelace");
         ResponseEntity<Challenge> postResponse = restTemplate.postForEntity(getRootUrl() + "/challenges", challenge, Challenge.class);
         assertNotNull(postResponse);
         System.out.println(postResponse.getBody().toString());
         assertNotNull(postResponse.getBody());
     }

     @Test
     public void testUpdateChallenge() {
         int id = 1;
         Challenge challenge = restTemplate.getForObject(getRootUrl() + "/challenges/" + id, Challenge.class);
         challenge.setName("Lindas Lovelacer");
         challenge.setTid(9);
         restTemplate.put(getRootUrl() + "/challenges/" + id, challenge);
         Challenge updatedChallenge = restTemplate.getForObject(getRootUrl() + "/challenges/" + id, Challenge.class);
         assertNotNull(updatedChallenge);
     }

     @Test
     public void testDeleteChallenge() {
          int id = 2;
          Challenge challenge = restTemplate.getForObject(getRootUrl() + "/challenges/" + id, Challenge.class);
          assertNotNull(challenge);
          restTemplate.delete(getRootUrl() + "/challenges/" + id);
          try {
        	  challenge = restTemplate.getForObject(getRootUrl() + "/challenges/" + id, Challenge.class);
          } catch (final HttpClientErrorException e) {
               assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
          }
     }
     @Test
     public void testGetAllUserGroups() {
    	 HttpHeaders headers = new HttpHeaders();
    	 HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    	 ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/userGroups",
    			 HttpMethod.GET, entity, String.class);  
    	 assertNotNull(response.getBody());
     }
     
     @Test
     public void testGetUserGroupsById() {
    	 UserGroup userGroup = restTemplate.getForObject(getRootUrl() + "/userGroup/101", UserGroup.class);
    	 assertNotNull(userGroup);
    	 assertEquals(101, (long) userGroup.getID());
     }
     
     @Test
     public void testCreateUserGroup() {
    	 UserGroup userGroup= new UserGroup ();
    	 userGroup.setGroupName("GroupName");
    	 ResponseEntity<UserGroup> postResponse = restTemplate.postForEntity(getRootUrl() + "/userGroups", userGroup, UserGroup.class);
    	 assertNotNull(postResponse);
    	 System.out.println(postResponse.getBody().toString());
    	 assertNotNull(postResponse.getBody());
     }
     
     @Test
     public void testUpdateUserGroup() {
    	 int id = 1;
    	 UserGroup userGroup = restTemplate.getForObject(getRootUrl() + "/userGroups/" + id, UserGroup.class);
    	 userGroup.setGroupName("Lindas Lovelacer Group");
    	 restTemplate.put(getRootUrl() + "/userGroups/" + id, userGroup);
    	 UserGroup updatedUserGroup = restTemplate.getForObject(getRootUrl() + "/userGroups/" + id, UserGroup.class);
    	 assertNotNull(updatedUserGroup);
     }
     
     @Test
     public void testDeleteUserGroup() {
    	 int id = 2;
    	 UserGroup userGroup = restTemplate.getForObject(getRootUrl() + "/userGroups/" + id, UserGroup.class);
    	 assertNotNull(userGroup);
    	 restTemplate.delete(getRootUrl() + "/userGroups/" + id);
    	 try {
    		 userGroup = restTemplate.getForObject(getRootUrl() + "/userGroups/" + id, UserGroup.class);
    	 } catch (final HttpClientErrorException e) {
    		 assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
    	 }
     }
     @Test
     public void testGetAllUsers() {
    	 HttpHeaders headers = new HttpHeaders();
    	 HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    	 ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users",
    			 HttpMethod.GET, entity, String.class);  
    	 assertNotNull(response.getBody());
     }
     
     @Test
     public void testGetUsersById() {
    	 User user = restTemplate.getForObject(getRootUrl() + "/user/1", User.class);
    	 System.out.println("user.email() "+user.getEmail());
    	 assertNotNull(user);
    	 assertEquals(1, (long) user.getId());
     }
     
     @Test
     public void testCreateUser() {
    	 User user= new User ();
    	 user.setEmail("test@email.se");
    	 user.setTagline("Ok, test");
    	 user.setFname("Linda Lovelace");
    	 ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", user, User.class);
    	 assertNotNull(postResponse);
    	 System.out.println(postResponse.getBody().toString());
    	 assertNotNull(postResponse.getBody());
     }
     
     @Test
     public void testUpdateUserOldVer() {
    	 String email = "test@emailer.se";
    	 User user = restTemplate.getForObject(getRootUrl() + "/users/" + email, User.class);
    	 user.setFname("Lindas Lovelacer");
    	 user.setEmail("test@emailer.se");
    	 restTemplate.put(getRootUrl() + "/users/" + email, user);
    	 User updatedUser = restTemplate.getForObject(getRootUrl() + "/users/" + email, User.class);
    	 assertNotNull(updatedUser);
     }
     
     @Test
     public void testDeleteUser() {
    	 int id = 2;
    	 User user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
    	 assertNotNull(user);
    	 restTemplate.delete(getRootUrl() + "/users/" + id);
    	 try {
    		 user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
    	 } catch (final HttpClientErrorException e) {
    		 assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
    	 }
     }
     @Test
     public void testGetAllTrails() {
    	 HttpHeaders headers = new HttpHeaders();
    	 HttpEntity<String> entity = new HttpEntity<String>(null, headers);
    	 ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/trails",
    			 HttpMethod.GET, entity, String.class);  
    	 assertNotNull(response.getBody());
     }
     
     @Test
     public void testGetTrailsById() {
    	 Trail trail = restTemplate.getForObject(getRootUrl() + "/trail/7", Trail.class);
    	 System.out.println("trail.email() "+trail.getId());
    	 assertNotNull(trail);
    	 assertEquals(7, (long) trail.getId());
     }
     
     @Test
     public void testCreateTrail() {
    	 Trail trail= new Trail ();
    	 trail.setId(1);
    	 trail.setLocation("Södermalm");
    	 trail.setTrailName("Motionspåret");
    	 ResponseEntity<Trail> postResponse = restTemplate.postForEntity(getRootUrl() + "/trails", trail, Trail.class);
    	 assertNotNull(postResponse);
    	 System.out.println(postResponse.getBody().toString());
    	 assertNotNull(postResponse.getBody());
     }
     
     @Test
     public void testUpdateTrail() {
    	 String email = "test@emailer.se";
    	 Trail trail = restTemplate.getForObject(getRootUrl() + "/trails/" + email, Trail.class);
    	 trail.setTrailName("Lidas Love Track");
    	 trail.setImage("/images/lvoepic.gif");
    	 restTemplate.put(getRootUrl() + "/trails/" + email, trail);
    	 Trail updatedTrail = restTemplate.getForObject(getRootUrl() + "/trails/" + email, Trail.class);
    	 assertNotNull(updatedTrail);
     }
     
     @Test
     public void testDeleteTrail() {
    	 int id = 2;
    	 Trail trail = restTemplate.getForObject(getRootUrl() + "/trails/" + id, Trail.class);
    	 assertNotNull(trail);
    	 restTemplate.delete(getRootUrl() + "/trails/" + id);
    	 try {
    		 trail = restTemplate.getForObject(getRootUrl() + "/trails/" + id, Trail.class);
    	 } catch (final HttpClientErrorException e) {
    		 assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
    	 }
     }
     @Test
     public void testGetUserRunsById() {
    	 UserRuns userRun = restTemplate.getForObject(getRootUrl() + "/userRuns/1", UserRuns.class);
    	 System.out.println("userRun.getTid() "+userRun.getId());
    	 assertNotNull(userRun);
    	 assertEquals(1, (long) userRun.getId());
     }
     
     @Test
     public void testCreateUserRuns() {
    	 UserRuns userRun= new UserRuns ();
    	 userRun.setComment("Bästa svängen.");
    	 userRun.setLength(10000);
    	 userRun.setTid(2);
    	 BigInteger bg = new BigInteger("560000000000");
    	 userRun.setTime(bg);
    	 userRun.setUid(1);
    	 ResponseEntity<UserRuns> postResponse = restTemplate.postForEntity(getRootUrl() + "/userRuns", userRun, UserRuns.class);
    	 assertNotNull(postResponse);
    	 System.out.println(postResponse.getBody().toString());
    	 assertNotNull(postResponse.getBody());
     }
     
     @Test
     public void testUpdateUserRuns() {
    	 int id = 2;
    	 UserRuns userRun = restTemplate.getForObject(getRootUrl() + "/userRuns/" + id, UserRuns.class);
    	 userRun.setComment("Lindas Love Track");
    	 userRun.setTid(2);
    	 restTemplate.put(getRootUrl() + "/userRuns/" + id, userRun);
    	 UserRuns updatedUserRuns = restTemplate.getForObject(getRootUrl() + "/userRuns/" + id, UserRuns.class);
    	 assertNotNull(updatedUserRuns);
     }
     
     @Test
     public void testDeleteUserRuns() {
    	 int id = 2;
    	 UserRuns userRun = restTemplate.getForObject(getRootUrl() + "/userRuns/" + id, UserRuns.class);
    	 assertNotNull(userRun);
    	 restTemplate.delete(getRootUrl() + "/userRuns/" + id);
    	 try {
    		 userRun = restTemplate.getForObject(getRootUrl() + "/userRuns/" + id, UserRuns.class);
    	 } catch (final HttpClientErrorException e) {
    		 assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
    	 }
	 }
     
     @Test
     public void testUpdateTrailReview() {
    	 int userId = 12;
    	 int trailId = 13;
    	 String reviewString = "This is a new review";
    	 TrailReview review = restTemplate.getForObject(getRootUrl() + "/userTrailReview/" + trailId + "/" + userId, TrailReview.class);
    	 assertNotNull(review);
    	 String response = restTemplate.getForObject(getRootUrl() + "/updateTrailReview?tid=" + trailId + "&uid=" + userId + "&review=" + reviewString, String.class);
    	 assertEquals(response, "Updated");
    	 TrailReview reviewUpdated = restTemplate.getForObject(getRootUrl() + "/userTrailReview/" + trailId + "/" + userId, TrailReview.class);
    	 assertNotNull(review);
    	 
    	 //System.err.println("THIS IS TEST \n\n\n\n" + review.getReview());
    	 assertEquals(review.getTid(), reviewUpdated.getTid());
    	 assertEquals(review.getUid(), reviewUpdated.getUid());
    	 assertEquals(review.getDate(), reviewUpdated.getDate());
    	 assertEquals(reviewString, reviewUpdated.getReview());
    	 restTemplate.getForObject(getRootUrl() + "/updateTrailReview?tid=" + trailId + "&uid=" + userId + "&review=" + "Det bästa som finns!", String.class);
    	 
     }
     
     @Test
     public void testHejSQL() {
    	 String expectedString = "SQL up!";
    	 String response = restTemplate.getForObject(getRootUrl() + "/hejSQL", String.class);
    	 assertEquals(expectedString, response);
     }
     
     @Test
     public void testAddChallenge() {
    	 int tid = 12;
    	 int caid = 1;
    	 String name = "Test Challenge";
    	 Challenge testChallenge = new Challenge();
    	 testChallenge.setCaid(caid);
    	 testChallenge.setTid(tid);
    	 testChallenge.setName(name);
    	 String response = restTemplate.getForObject(getRootUrl() + "/addChallenge?tid=" + tid + "&caid=" + caid + "&name=" + name, String.class);
    	 assertNotNull(response);
    	 assertEquals("Saved", response);
     }
     
     @Test
     public void testUpdateExistingChallenge() {
    	 int cid = 1;
    	 String newName = "New name";
    	 Challenge chall = restTemplate.getForObject(getRootUrl() + "/challenge/" + cid, Challenge.class);
    	 assertNotNull(chall);
    	 String strResponse = restTemplate.getForObject(getRootUrl() + "/updateChallenge?id=" + cid + "&name=" + newName, String.class);
    	 assertNotNull(strResponse);
    	 assertEquals("Updated", strResponse);
    	 
    	 Challenge newChall = restTemplate.getForObject(getRootUrl() + "/challenge/" + cid, Challenge.class);
    	 assertEquals(newName, newChall.getName());
     }
     
     //@Test
     public void testRemoveChallenge() {
    	 int cid = 1;
    	 
    	 Challenge chall = restTemplate.getForObject(getRootUrl() + "challenge/" + cid, Challenge.class);
    	 assertNotNull(chall);
    	 
    	 Map<String, Boolean> objMap = restTemplate.getForObject(getRootUrl() + "/deleteChallenge/" + cid, Map.class);
    	 assertEquals(Boolean.TRUE, objMap.get("deleted"));
    	 
     }
     
     @Test
     public void testAddNewGroup() { // TODO: Make it remove the group after
    	int uid = 11;
    	String name = "Testing group";
    	String response = restTemplate.getForObject(getRootUrl() + "/addUserGroup?uid=" + uid + "&groupname=" + name, String.class);
    	assertNotNull(response);
    	assertEquals("Saved", response);
     }
     
     @Test
     public void testAddNewChallengeAttribute() {
    	 
     }
     
     @Test
     public void testUserFeed() {
    	 int uid = 12;
    	 int uid2 = 11;
    	 List<FeedElement> lst = restTemplate.getForObject(getRootUrl() + "/userFeed/" + uid, List.class);
    	 assertNotNull(lst);
    	 lst = restTemplate.getForObject(getRootUrl() + "/userFeed/" + uid2, List.class);
    	 assertNotNull(lst);
     }
     
     @Test
     public void testTrailsSortedByLocation() {
    	 Iterable<Trail> list = restTemplate.getForObject(getRootUrl() + "/allTrailsSortedByLocation?lat=" + 1 + "&lon=" + 1, Iterable.class);
    	 assertNotNull(list);
     }
     
     @Test
     public void testAllTrailsByName() {
    	 List<Trail> lst = restTemplate.getForObject(getRootUrl() + "/allTrails", List.class);
    	 assertNotNull(lst);
     }
     
     @Test
     public void testAddFavourite() {
    	 int uid = 7;
    	 int tid = 12;
    	 UserTrails lst = restTemplate.getForObject(getRootUrl() + "/addFavourite?uid=" + uid + "&tid=" + tid, UserTrails.class);
    	 assertNotNull(lst);
     }
     
     @Test
     public void testUpdateUser() {
    	int uid = 11;
    	
    	User user = restTemplate.getForObject(getRootUrl() + "/user/" + uid, User.class);
    	assertNotNull(user);
    	assertEquals("Erik", user.getFname());
    	assertEquals("Vikström", user.getLname());
    	assertEquals("don@doing.se", user.getEmail());
    	
    	
    	String response = restTemplate.getForObject(getRootUrl() + "/updateUser?id=" + uid + "&fname=Anton&lname=Harneby&email=No@No.nu", String.class);
    	assertNotNull(response);
    	response = restTemplate.getForObject(getRootUrl() + "/updateUser?id=" + uid + "&fname=Erik&lname=Vikström&email=don@doing.se", String.class);
    	assertNotNull(response);
    	
    	user = restTemplate.getForObject(getRootUrl() + "/user/" + uid, User.class);
    	assertNotNull(user);
    	assertEquals("Erik", user.getFname());
    	assertEquals("Vikström", user.getLname());
    	assertEquals("don@doing.se", user.getEmail());
    	    	
    	response = restTemplate.getForObject(getRootUrl() + "/updateUser?id=" + uid + "&tagline=Hi", String.class);
    	assertNotNull(response);
    	user = restTemplate.getForObject(getRootUrl() + "/user/" + uid, User.class);
    	assertNotNull(user);
    	assertEquals("Hi", user.getTagline());
    	
    	response = restTemplate.getForObject(getRootUrl() + "/updateUser?id=" + uid + "&tagline=", String.class);
    	assertNotNull(response);
    	user = restTemplate.getForObject(getRootUrl() + "/user/" + uid, User.class);
    	assertNotNull(user);
    	assertEquals("", user.getTagline());
     }
     
     @Test
     public void testAddAndRemoveUser() {
    	 String fname = "TestF";
    	 String lname = "TestL";
    	 String email = "Test@Test.test";
    	 String tagline = "TestTag";
    	 String psw = "Secret";
    	 String response = restTemplate.getForObject(getRootUrl() + "/addUser?fname=" + fname + "&lname=" + lname + "&email=" + email 
    			 									 + "&tagline=" + tagline + "&psw=" + psw, String.class);
    	 assertNotNull(response);
    	 
    	 int id = restTemplate.getForObject(getRootUrl() + "/userGetIdByEmail/" + email, Integer.class);
    	 User user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
    	 assertNotNull(user);
    	 assertEquals(email, user.getEmail());
    	 
    	 Map<String, Boolean> map = restTemplate.getForObject(getRootUrl() + "/deleteUser/" + id, Map.class);
    	 assertNotNull(map);
    	 assertEquals(Boolean.TRUE, map.get("deleted"));
    	 
    	 id  = restTemplate.getForObject(getRootUrl() + "/userGetIdByEmail/" + email, Integer.class);
    	 
    	 assertEquals(-1, id);
    	 
     }
     
     @Test
     public void testAddTrails() {
    	 String response = restTemplate.getForObject(getRootUrl() + "/addTrails", String.class);
    	 assertNotNull(response);
    	 //assertEquals("All trails added", response);
     }
     
     @Test
     public void testGetAllUsersNew() {
    	 Iterable<User> users = restTemplate.getForObject(getRootUrl() + "/allUsers", Iterable.class);
    	 assertNotNull(users);
     }
     
     @Test
     public void testGetAllGroups() {
    	 Iterable<UserGroup> users = restTemplate.getForObject(getRootUrl() + "/allGroups", Iterable.class);
    	 assertNotNull(users);
     }
     
     @Test
     public void testGetAllUsersSecond() {
    	 List<User> users = restTemplate.getForObject(getRootUrl() + "/allUser", List.class);
    	 assertNotNull(users);
     }
     
     @Test
     public void testGetAllFavTrails() {
    	 int uid = 12;
    	 UserTrails list = restTemplate.getForObject(getRootUrl() + "/usersFavTrails" + uid, UserTrails.class);
    	 assertNotNull(list);
    	 //"/usersFavTrails/{uid}"
     }
     
     @Test
     public void testAllUserTrails() {
    	 List<UserTrails> lst = restTemplate.getForObject(getRootUrl() + "/userTrails", List.class);
    	 assertNotNull(lst);
     }
	 
     @Test
     public void testUsersFavTrails() {
    	 int uid = 12;
    	 
    	 List<UserTrails> lst = restTemplate.getForObject(getRootUrl() + "/usersFavTrails/" + uid, List.class);
    	 assertNotNull(lst);
     }
     
     @Test
     public void testAddAndRemoveUserTrail() {
    	 int tid = 10;
    	 int uid = 3;
    	 boolean favortite = false;
    	 
    	 String response = restTemplate.getForObject(getRootUrl() + "/addUserTrails?tid=" + tid + "&uid=" + uid + "&favourite=" + favortite, String.class);
    	 assertNotNull(response);
    	 
    	 response = restTemplate.getForObject(getRootUrl() + "/updateUserTrails?tid=" + tid + "&uid=" + uid + "&favourite=true", String.class);
    	 assertNotNull(response);
    	 
    	 Map<String, Boolean> map = restTemplate.getForObject(getRootUrl() + "/deleteUserTrails?tid=" + tid + "&uid=" + uid, Map.class);
    	 assertNotNull(map);
    	 assertEquals(Boolean.TRUE, map.get("deleted"));
     }
     
     //@Test
     public void testAddFav() { //TODO: find a way to make ut work here
    	 int uid = 3;
    	 int tid = 12;
    	 
    	 ResponseEntity<List<UserTrails>> ut = restTemplate.getForObject(getRootUrl() + "/addFavourite?uid=" + uid + "&tid=" + tid, ResponseEntity.class);
    	 assertNotNull(ut);
    	 
    	 String response = restTemplate.getForObject(getRootUrl() + "/updateUserTrails?tid=" + tid + "&uid=" + uid + "&favourite=false", String.class);
    	 assertNotNull(response);
     }
     
     @Test
     public void testAllGroupConnections() {
    	 List<UserGroupConnect> list = restTemplate.getForObject(getRootUrl() + "/userGroupConnect", List.class);
    	 assertNotNull(list);
     }
     
     @Test
     public void testUpdateUserGroup() {
    	 
     }
     
//	 @Test //TODO: Test not working don't know how to make sure the return class is correct
//	 public void testGettingFavTrails() {
//		 int id = 12;
//		 ResponseEntity<List<UserTrails>> utList = restTemplate.getForObject(getRootUrl() + "/usersFavTrails/" + id, ResponseEntity.class);
//		 utList.getBody().forEach(ut ->  {
//			 assertEquals(true, ut.getFavourite());
//		 });
//	 }
     

}
	
	
