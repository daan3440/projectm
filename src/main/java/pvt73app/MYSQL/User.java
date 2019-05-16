package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// This tells Hibernate to make a table out of this class
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	private String fname;
	private String lname;
	private String email;
	private String psw;
	private String tagline;
	private String photo;

	public Integer getId() {
		return id;
	}

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

	public void setEmail(String psw){
		this.psw=psw;
	}
	public String getPassword(){
		return psw;
	}
	
	public void setPassword(String email){
		this.email=email;
	}
	public String getTagline(){
		return tagline;
	}
	
	public void setTagline(String tagline){
		this.tagline=tagline;
	}

	public String getPhoto(){
		return photo;
	}

	public void setPhoto(String photo){
		this.photo=photo;
	}
}

   