package sg.edu.nus.iss.vttp5a_ssf_day19ws;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.vttp5a_ssf_day19ws.service.FileReaderService;
import sg.edu.nus.iss.vttp5a_ssf_day19ws.utility.Utility;

@SpringBootApplication
public class Vttp5aSsfDay19wsApplication implements CommandLineRunner{

	@Autowired
	FileReaderService fileReaderService;

	public static void main(String[] args) {
		SpringApplication.run(Vttp5aSsfDay19wsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		if(args.length > 0) {
			for (String arg : args) {
				if (arg.startsWith("--file=")){
					String todo = arg.replace("--file=", "");
						File todoFile = new File(todo);
						fileReaderService.initData(todoFile);
				}
			}
		} else {
			File todoFile = new File(Utility.todoFilePath);
			fileReaderService.initData(todoFile);
		}

		// String todo = "";
		// ApplicationArguments appArgs = new DefaultApplicationArguments(args);
		// if (appArgs.containsOption("file")) {
		// 	todo = appArgs.getOptionValues("file").getFirst();
		// 	File todoFile = new File(todo);
		// 	fileReaderService.initData(todoFile);
		// } else {
		// 	System.out.println("Please specify file path for todo file!");
		// 	System.exit(1);
		// }
	}
	// java -jar target/vttp5a-ssf-day19ws-0.0.1-SNAPSHOT.jar --file=.\src\main\resources\static\JSON\todos.json
	// mvn spring-boot:run -Dspring-boot.run.arguments="--file=.\src\main\resources\static\JSON\todos.json"

}
