package pvt73app.FTP;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class FTPController {

	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
	                               RedirectAttributes redirectAttributes) {
	    String FTP_ADDRESS = "rymden.com";
	    String LOGIN = "rymden-pvt73";
	    String PSW = "Bd_uPw93PsHz";

	    FTPClient con = null;

	    try {
	        con = new FTPClient();
	        con.connect(FTP_ADDRESS);

	        if (con.login(LOGIN, PSW)) {
	            con.enterLocalPassiveMode(); // important!
	            con.setFileType(FTP.BINARY_FILE_TYPE);

	            boolean result = con.storeFile(file.getOriginalFilename(), file.getInputStream());
	            con.logout();
	            con.disconnect();
	            redirectAttributes.addFlashAttribute("message",
	                    "You successfully uploaded " + file.getOriginalFilename() + "!");
	        }
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("message",
	                "Could not upload " + file.getOriginalFilename() + "!");
	    }

	    return "redirect:/";
	}
	
}
