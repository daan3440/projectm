package pvt73app.MYSQL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.List;

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
     public void testUpdateUser() {
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
    	 assertEquals(2, (long) trail.getId());
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
    	 userRun.setUserId(1);
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
	 
//	 @Test //TODO: Test not working don't know how to make sure the return class is correct
//	 public void testGettingFavTrails() {
//		 int id = 12;
//		 ResponseEntity<List<UserTrails>> utList = restTemplate.getForObject(getRootUrl() + "/usersFavTrails/" + id, ResponseEntity.class);
//		 utList.getBody().forEach(ut ->  {
//			 assertEquals(true, ut.getFavourite());
//		 });
//	 }
     

}
	
	
