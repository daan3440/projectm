package pvt73app.MYSQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Challenge {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
		private int tid;
		private int caid;
		private String name;

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
	}