package sg.edu.nus.iss.vttp5a_ssf_day19ws.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;

public class Utility {
    public static final String todoFilePath = "C:\\Users\\Clare Lau\\VTTP_SSF\\vttp5a-ssf-day19ws\\src\\main\\resources\\static\\JSON\\todos.json";

    public static JsonArray readJsonFile(File todoFile) {
        JsonArray dataRead = null;
        try {
            // below line is to let online redis read local file
            // path starts after resources
            // InputStream is1 = getClass().getResourceAsStream("/static/JSON/todos.json");
            InputStream is = new FileInputStream(todoFile);
            JsonReader jReader = Json.createReader(is);
            dataRead = jReader.readArray();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found...");
            e.printStackTrace();
        }
        return dataRead;
    }
}
