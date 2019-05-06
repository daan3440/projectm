package pvt73app.MYSQL;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EditSQLTest {
	UserRuns ur = new UserRuns(); 
	SQLController sc = new SQLController(); 
	
	

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void createUserRun() {
		sc.addUserRun(0, null, null, 0, 0, null);
//		assertEquals();
	}
	@Test
	public void getAllTrailsTest() {
		
//		assertEquals();
	}

}
