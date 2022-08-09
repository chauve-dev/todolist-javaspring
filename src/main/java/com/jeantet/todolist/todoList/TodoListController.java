package com.jeantet.todolist.todoList;

import com.jeantet.todolist.todo.Todo;
import com.jeantet.todolist.todo.TodoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/todo", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class TodoListController {

    private final TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping(path = "{id}")
    public TodoList getTodos(@PathVariable("id") Long id) {
        TodoList todoList = todoListService.getTodoList(id);
        return todoList;
    }

    @GetMapping(path = "{id}/{status}")
    public List<Todo> getTodosByStatus(@PathVariable("id") Long id, @PathVariable("status") boolean status) {
        return todoListService.getTodoByStatus(id, status);
    }
    @GetMapping
    public List<TodoList> getTodoLists() {
        return todoListService.getTodoLists();
    }

    @PutMapping(path = "{id}/{todoId}")
    public void setTodoStatus(
            @PathVariable("id") Long list_id,
            @PathVariable("todoId") Long todo_id,
            @RequestParam boolean status
    ) {
        todoListService.setTodoStatus(list_id, todo_id, status);
    }

    @PostMapping()
    public void addTodoList(@RequestParam String name) {
        todoListService.addTodoList(name);
    }

    @PostMapping(path = "{id}")
    public void addTodo(@PathVariable Long id, @RequestBody TodoDTO todo) {
        todoListService.addTodo(id, todo);
    }

    @DeleteMapping(path = "{listId}")
    public void deleteTodoList(@PathVariable("listId") Long list_id) {
        todoListService.removeTodoList(list_id);
    }

    @DeleteMapping(path = "{listId}/{todoId}")
    public void deleteTodo(@PathVariable("listId") Long list_id, @PathVariable("todoId") Long todo_id) {
        todoListService.removeTodo(list_id, todo_id);
    }
}
