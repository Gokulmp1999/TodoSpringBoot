package com.myTodoApplication.springBoot.MyFirstTodoApplication.Todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

    private TodoRepository todoRepository;

    public TodoControllerJpa(TodoRepository todoRepository) {

        this.todoRepository = todoRepository;
    }


    @RequestMapping("welcome")
    public String welcome() {
        return "Welcome";
    }

    @RequestMapping("list-todos")
    public String getListOfTodos(ModelMap model) {
//        List<Todo> todos = todoService.getTodosByUsername(getLoggedinUsername());
        List<Todo> todos = todoRepository.findByUsername(getLoggedinUsername());
        model.addAttribute("todos", todos);

        return "Todos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String userName = getLoggedinUsername();
        Todo todo = new Todo(0, userName, "Default Desc", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        String userName = getLoggedinUsername();
        todo.setUsername(userName);
        todoRepository.save(todo);
//        todoService.addTodo(userName, todo.getDescription(), todo.getTargetDate(), false);
        if (result.hasErrors()) {
            return "todo";
        }
        return "redirect:list-todos";
    }

    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoRepository.deleteById(id);
//        todoService.deleteTodo(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String updateTodo(@RequestParam int id, ModelMap model) {
//        Todo todo=todoService.updateTodoById(id);
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String addUpdateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        String userName = getLoggedinUsername();
        todo.setUsername(userName);
//        todoService.updateTodo(todo);
        todoRepository.save(todo);
        if (result.hasErrors()) {
            return "todo";
        }
        return "redirect:list-todos";
    }

    private String getLoggedinUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return userName;
    }

}
