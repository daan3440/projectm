package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Challenge {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private int tid;
	private int caid;
	private String name;

	
//    public Challenge(int tid, int caid, String name) {
//        this.tid = tid;
//        this.caid = caid;
//        this.name = name;
//    }
    
	public Integer getId(){
		return id;
	}

	public int getTid(){
		return tid;
	}

	public void setTid(int tid){
		this.tid=tid;
	}

	public int getCaid(){
		return caid;
	}

	public void setCaid(int caid){
		this.caid=caid;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	@Override
	public String toString() {
		return "Challenge [id=" + id + ", tid=" + tid + ", caid=" + caid + ", name=" + name + "]";
	}
}