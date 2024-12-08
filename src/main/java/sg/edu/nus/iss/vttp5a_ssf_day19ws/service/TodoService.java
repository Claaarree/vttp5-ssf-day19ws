package sg.edu.nus.iss.vttp5a_ssf_day19ws.service;

import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.vttp5a_ssf_day19ws.constant.Constant;
import sg.edu.nus.iss.vttp5a_ssf_day19ws.model.Todo;
import sg.edu.nus.iss.vttp5a_ssf_day19ws.repository.HashRepo;

@Service
public class TodoService {
    
    @Autowired
    HashRepo todoRepo;

    public List<Todo> getAllTodos() throws ParseException {
        List<Todo> todoList = new ArrayList<>();

        Set<String> todoSet = todoRepo.getAllFields(Constant.redisTodoKey);
        for (String t: todoSet) {
            String td = todoRepo.getFieldValue(Constant.redisTodoKey, t);
            JsonReader jReader = Json.createReader(new StringReader(td));
            JsonObject todo = jReader.readObject();
            
            Date rawDueDate = new Date(Long.parseLong(todo.getString("due_date")));
            Date rawCreatedAt = new Date(Long.parseLong(todo.getString("created_at")));
            Date rawUpdatedAt = new Date(Long.parseLong(todo.getString("updated_at")));

            // It doesn't matter what format the date is stored in as in the model!
            // As long as it is formatted in the html it's fine!
            // SimpleDateFormat sdf = new SimpleDateFormat("E, MM/dd/yyyy");
            // String formattedDueDate = sdf.format(rawDueDate);
            // String formattedCreatedAt = sdf.format(rawCreatedAt);
            // String formattedUpdatedAt = sdf.format(rawUpdatedAt);

            // Date dueDate = sdf.parse(formattedDueDate);
            // Date createAt = sdf.parse(formattedCreatedAt);
            // Date updatedAt = sdf.parse(formattedUpdatedAt);
            
            Todo to = new Todo();
            to.setId(todo.getString("id"));
            to.setName(todo.getString("name"));
            to.setDescription(todo.getString("description"));
            to.setDueDate(rawDueDate);
            to.setPriorityLevel(todo.getString("priority_level"));
            to.setStatus(todo.getString("status"));
            to.setCreateAt(rawCreatedAt);
            to.setUpdatedAt(rawUpdatedAt);

            todoList.add(to);
        }
        return todoList;
    }

	public void addTodo(Todo t) {
        Long dueDateLong = t.getDueDate().getTime();
        Long createAtLong = t.getCreateAt().getTime();
        Long updatedAtLong = t.getUpdatedAt().getTime();

		JsonObject newTodo = Json.createObjectBuilder()
                .add("id", t.getId())
                .add("name", t.getName())
                .add("description", t.getDescription())
                .add("due_date", String.valueOf(dueDateLong))
                .add("priority_level", t.getPriorityLevel())
                .add("status", t.getStatus())
                .add("created_at", String.valueOf(createAtLong))
                .add("updated_at", String.valueOf(updatedAtLong))
                .build();
        
        todoRepo.addToHash(Constant.redisTodoKey, t.getId(), newTodo.toString());
	}
}
