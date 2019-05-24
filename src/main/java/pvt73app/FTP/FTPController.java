package pvt73app.FTP;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@RequestMapping
public class FTPController {
	static FTPClient con = new FTPClient();
	
//	@CrossOrigin
//	@PostMapping("/")
//	public String handleFileUpload(@RequestParam("file") MultipartFile file,
//	                               RedirectAttributes redirectAttributes) {
//	    String FTP_ADDRESS = "rymden.com";
//	    String LOGIN = "rymden-pvt73";
//	    String PSW = "Bd_uPw93PsHz";
//	    
//	    System.out.println("file no null");
//	    if(file != null) {
//	    }
//
////	    FTPClient con = null;
//
//	    try {
////	        con = new FTPClient();
//	        con.connect(FTP_ADDRESS);
//	        
//
//	        if (con.login(LOGIN, PSW)) {
//	            con.enterLocalPassiveMode(); // important!
//	            con.setFileType(FTP.BINARY_FILE_TYPE);
//
//	            boolean result = con.storeFile(file.getOriginalFilename(), file.getInputStream());
//	            con.logout();
//	            con.disconnect();
//	            redirectAttributes.addFlashAttribute("message",
//	                    "You successfully uploaded " + file.getOriginalFilename() + "!");
//	        }
//	    } catch (Exception e) {
//	        redirectAttributes.addFlashAttribute("message",
//	                "Could not upload " + file.getOriginalFilename() + "!");
//	    }
//
//	    return "redirect:/";
//	}
//	@CrossOrigin
//	@PostMapping("/")
	public String handleRawFileUpload(@RequestParam("file") MultipartFile file, String id) {
		String FTP_ADDRESS = "rymden.com";
		String LOGIN = "rymden-pvt73";
		String PSW = "Bd_uPw93PsHz";
		
		System.out.println("file no null");
		if(file != null) {
		}
		
//	    FTPClient con = null;
		
		try {
//	        con = new FTPClient();
			con.connect(FTP_ADDRESS);
			
			
			if (con.login(LOGIN, PSW)) {
				con.enterLocalPassiveMode(); // important!
				con.setFileType(FTP.BINARY_FILE_TYPE);
				
				boolean result = con.storeFile(file.getOriginalFilename(), file.getInputStream());
//				boolean result = con.storeFile(id+".jpg", file.getInputStream());
				con.logout();
				con.disconnect();
//				redirectAttributes.addFlashAttribute("message",
//						"You successfully uploaded " + file.getOriginalFilename() + "!");
			}
		} catch (Exception e) {
//			redirectAttributes.addFlashAttribute("message",
//					"Could not upload " + file.getOriginalFilename() + "!");
		}
		
		return "redirect:/";
	}
	
}
