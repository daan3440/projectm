package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	private String fname;
	private String lname;
	private String email;
	private String photo;

	public Integer getId() {
		return id;
	}
	//TODO SLå ihop names - MEN bara just nu...
	
//	public String getName() {		
//		return fname +" "+ lname;
//	}

	public String getFname(){
		return fname;
	}

	public void setFname(String fname){
		this.fname=fname;
	}

	public String getLname(){
		return lname;
	}

	public void setLname(String lname){
		this.lname=lname;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getPhoto(){
		return photo;
	}

	public void setPhoto(String photo){
		this.photo=photo;
	}
}

   