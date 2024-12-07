package sg.edu.nus.iss.vttp5a_ssf_day19ws;

import java.io.File;
import java.io.FileReader;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.vttp5a_ssf_day19ws.service.FileReaderService;

@SpringBootApplication
public class Vttp5aSsfDay19wsApplication implements CommandLineRunner{

	@Autowired
	FileReaderService fileReaderService;

	public static void main(String[] args) {
		SpringApplication.run(Vttp5aSsfDay19wsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		String todo = "";
		ApplicationArguments appArgs = new DefaultApplicationArguments(args);
		if (appArgs.containsOption("file")) {
			todo = appArgs.getOptionValues("file").getFirst();
			File todoFile = new File(todo);
			FileReaderService frs = new FileReaderService();
			fileReaderService.initData(todoFile);
		} else {
			System.out.println("Please specify file path for todo file!");
			System.exit(1);
		}
	}

}
