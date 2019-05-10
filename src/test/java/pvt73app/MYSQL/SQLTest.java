package pvt73app.MYSQL;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import pvt73app.MYSQL.SQLController;
import pvt73app.MYSQL.UserRepository;


//	UserRuns ur = new UserRuns(); 
//	SQLController sc = new SQLController(); 
	 
	
	

	
	 
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectmApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
	public class SQLTest {
//	  @Autowired
//	  TrailRepository tr;
//	  
//	  @Autowired
//	  UserGroupRepository ugr;
//	  
//	  @Autowired
//	  UserRepository ur;
//	  
//	  @Autowired
//	  UserGroupConnectRepo ugcr;
//	  
//	  @Autowired
//	  UserRunsRepository urr;
//	  
//	  @Autowired
//	  UserTrailsRepository utr;
	  

	  //	  @Test
//	  @Transactional
//	  public void testFindAll() {
//	    Iterable<Trail> trails = tr.findAll();
//	    List<Trail> trailList = new ArrayList<>();
//	    trails.forEach(trailList::add);
//	    assertEquals(2, trailList.size());
//	    Trail item = trailList.get(0);
//	    System.out.println("Print da lista a: " +trailList.toString());
//	    assertEquals("101", item.getTrailID());
//	   
//	  }
	
	
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
     

}
	
	
