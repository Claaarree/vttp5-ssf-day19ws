package sg.edu.nus.iss.vttp5a_ssf_day19ws.controller;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5a_ssf_day19ws.model.Todo;
import sg.edu.nus.iss.vttp5a_ssf_day19ws.service.TodoService;

@Controller
@RequestMapping("/todos")
public class TodoController {
    
    @Autowired
    TodoService todoService;

    // where and how to handle parse exception?
    @GetMapping
    public String getAllTodos(@RequestParam(required = false) String status, Model model) throws ParseException {
        List<Todo> todoList = todoService.getAllTodos();
        if (status != null){
            todoList = todoList.stream().filter(t -> t.getStatus().equals(status)).collect(Collectors.toList());
        }
        model.addAttribute("todos", todoList);
        model.addAttribute("status", status);
        return "listing";
    }

    @GetMapping("/add")
    public String addNewTodo(Model model) {
        Todo t = new Todo();
        model.addAttribute("todo", t);
        return "add";
    }

    @PostMapping("/add")
    public String handleTodoForm(@Valid @ModelAttribute Todo t, BindingResult result) {
        if (result.hasErrors()){
            return "add";
        } else {
            todoService.addTodo(t);
            return "redirect:/todos";
        }
    }

}
