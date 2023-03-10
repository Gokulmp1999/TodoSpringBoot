package com.myTodoApplication.springBoot.MyFirstTodoApplication.Todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos=new ArrayList<>();
    static int todosCount=0;
    static {
        todos.add(new Todo(++todosCount, "admin","Learn AWS",
                LocalDate.now().plusYears(1), false ));
        todos.add(new Todo(++todosCount, "admin","Learn DevOps",
                LocalDate.now().plusYears(2), false ));
        todos.add(new Todo(++todosCount, "admin","Learn Full Stack Development",
                LocalDate.now().plusYears(3), false ));
    }

    public static List<Todo> getTodosByUsername(String username) {
        Predicate<? super Todo> predicate = todo -> username.equalsIgnoreCase(todo.getUsername());
        return todos.stream().filter(predicate).toList();
    }
    public void addTodo(String username, String description, LocalDate targetDate, boolean done){
        Todo todo=new Todo(++todosCount,username,description,targetDate,done);
        todos.add(todo);
    }

    public void deleteTodo(int id){
        Predicate<? super Todo> predicate = todo -> id==todo.getId();
        todos.removeIf(predicate);
    }

    public Todo updateTodoById(int id) {
        Predicate<? super Todo> predicate = todo -> id==todo.getId();
Todo todo=todos.stream().filter(predicate).findFirst().get();
return todo;

    }

    public void updateTodo(@Valid Todo todo) {
        deleteTodo(todo.getId());
       todos.add(todo);
    }
}
