package pvt73app.FTP;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.mock.web.MockMultipartFile;

import org.junit.Before;
import org.junit.Test;

public class FTPTest {
	FTPController ftp = new FTPController();

//	Path path = Paths.get("/path/to/the/file.txt");
//	String name = "file.txt";
//	String originalFileName = "file.txt";
//	String contentType = "text/plain";
//	byte[] content = null;try
//	{
//		content = Files.readAllBytes(path);
//	}catch(
//	final IOException e)
//	{
//	}
//	MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void handleFileUploadTest() {
		fail("Not yet implemented");
	}

}
