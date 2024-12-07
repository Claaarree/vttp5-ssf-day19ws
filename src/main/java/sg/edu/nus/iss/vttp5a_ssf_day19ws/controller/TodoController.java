package sg.edu.nus.iss.vttp5a_ssf_day19ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.vttp5a_ssf_day19ws.service.TodoService;

@Controller
@RequestMapping("/todos")
public class TodoController {
    
    @Autowired
    TodoService todoService;

    @GetMapping
    public String getAllTodos(Model model) {

        return "listing";
    }

}
