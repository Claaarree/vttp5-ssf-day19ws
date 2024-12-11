package sg.edu.nus.iss.vttp5a_ssf_day19ws.controller;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5a_ssf_day19ws.model.Todo;
import sg.edu.nus.iss.vttp5a_ssf_day19ws.service.TodoService;

@Controller
@RequestMapping("/")
public class TodoController {
    
    @Autowired
    TodoService todoService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestBody MultiValueMap<String, String> credentials, 
    HttpSession session) {
        String fullName = credentials.getFirst("fullName");
        String age = credentials.getFirst("age");
        if (Integer.parseInt(age) < 10) {
            return "underage";
        } else {
            session.setAttribute("fullName", fullName);
            session.setAttribute("age", age);
            return "redirect:/todos";
        }
    }

    // where and how to handle parse exception?
    @GetMapping("/todos")
    public String getAllTodos(@RequestParam(required = false) String status, Model model, HttpSession session) throws ParseException {
        if (session.getAttribute("fullName") == null){
            return "refused";
        }
        List<Todo> todoList = todoService.getAllTodos();
        if (status != null){
            todoList = todoList.stream().filter(t -> t.getStatus().equals(status)).collect(Collectors.toList());
        }
        model.addAttribute("todos", todoList);
        model.addAttribute("status", status);
        return "listing";
    }

    @GetMapping("/todos/add")
    public String addNewTodo(Model model) {
        // issue lies here.... 
        // it creates a new id everytime
        Todo t = new Todo();
        t.setId(UUID.randomUUID().toString());
        System.out.println("In addtodo controller: "+ t.getId());
        model.addAttribute("todo", t);
        return "add";
    }

    @PostMapping("/todos/add")
    public String handleTodoForm(@Valid @ModelAttribute Todo t, BindingResult result) {
        // t.setId(UUID.randomUUID().toString());
        System.out.println(t.getId());
        if (result.hasErrors()){
            return "add";
        } else {
            todoService.addTodo(t);
            return "redirect:/todos";
        }
    }

    @GetMapping("/todos/{t_id}")
    public String deleteTodo(@PathVariable (name = "t_id") String id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }

    @GetMapping("/todos/edit/{t_id}")
    public String editTodo(@PathVariable (name = "t_id") String id, Model model) {
        Todo t = todoService.getTodoById(id);
        System.out.println("In edittodo controllerget: "+ t.getId());
        model.addAttribute("todo", t);
        return "edit";
    }

    // model attribute is the issue...
    // issit because it uses the empty constructor...yep
    // therefore need to set the id using the path variable in the postmapping
    // just use path variable? yep
    @PostMapping("/todos/edit/{t_id}")
    public String handleEditTodo(@PathVariable (name = "t_id") String id, @ModelAttribute Todo t) {
        // isn't put a replace if present?
        // System.out.println("In edittodo controllerpost: "+ t.getId());
        t.setId(id);
        // Todo t = todoService.getTodoById(id);
        todoService.addTodo(t);
        System.out.println("In edittodo controllerpost: "+ t.getDescription());
        return "redirect:/todos";
    }

}
