package sg.edu.nus.iss.vttp5a_ssf_day19ws;

import java.util.Collections;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Vttp5aSsfDay19wsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Vttp5aSsfDay19wsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		SpringApplication app = new SpringApplication(Vttp5aSsfDay19wsApplication.class);
		String todo = "";
		ApplicationArguments appArgs = new DefaultApplicationArguments(args);
		if (appArgs.containsOption("file")) {
			todo = appArgs.getOptionValues("file").getFirst();
			app.setDefaultProperties(Collections.singletonMap("file", todo));
			app.run(args);
		} else {
			System.out.println("Please specify file path for todo file!");
			System.exit(1);
		}
	}

}
