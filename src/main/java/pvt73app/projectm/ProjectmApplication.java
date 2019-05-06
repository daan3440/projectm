package pvt73app.projectm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"pvt73app.*"})
public class ProjectmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectmApplication.class, args);
	}

	public int getOne() {
		// TODO Auto-generated method stub
		return 1;
	}

}
