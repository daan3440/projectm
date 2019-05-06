package pvt73app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pvt73app.ProjectmApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectmApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void oneIsOne() {
		ProjectmApplication pvt1 = new ProjectmApplication ();
		assertEquals(1, pvt1.getOne());
	}

}
