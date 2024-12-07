package sg.edu.nus.iss.vttp5a_ssf_day19ws.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.vttp5a_ssf_day19ws.constant.Constant;
import sg.edu.nus.iss.vttp5a_ssf_day19ws.repository.HashRepo;
import sg.edu.nus.iss.vttp5a_ssf_day19ws.utility.Utility;

@Service
public class FileReaderService {
    
    @Autowired
    HashRepo todoRepo;

    public void initData(File todoFile) throws ParseException {
        JsonArray dataRead = Utility.readJsonFile(todoFile);


        for (int i = 0; i < dataRead.size(); i++) {
            JsonObject jsonObject = dataRead.getJsonObject(i);
            String dueDateString = jsonObject.getString("due_date");
            String createdAtString = jsonObject.getString("created_at");
            String updatedAtString = jsonObject.getString("updated_at");

            SimpleDateFormat sdf = new SimpleDateFormat("E, MM/dd/yyyy");

            Long dueDateEpoch = sdf.parse(dueDateString).getTime();
            Long createAtEpoch = sdf.parse(createdAtString).getTime();
            Long updatedAtEpoch = sdf.parse(updatedAtString).getTime();

            String jObjectString = jsonObject.toString();
            jObjectString = jObjectString.replace(dueDateString, String.valueOf(dueDateEpoch));
            jObjectString = jObjectString.replace(createdAtString, String.valueOf(createAtEpoch));
            jObjectString = jObjectString.replace(updatedAtString, String.valueOf(updatedAtEpoch));

            todoRepo.addToHash(Constant.redisTodoKey, jsonObject.getString("id"), jObjectString);
        }
    }
}
