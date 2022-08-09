package com.jeantet.todolist.todoList;

import com.jeantet.todolist.todo.Todo;
import com.jeantet.todolist.todo.TodoRepository;
import com.jeantet.todolist.todo.TodoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListService {

    private final TodoListRepository todoListRepository;
    private final TodoRepository todoRepository;

    @Autowired
    public TodoListService(
        TodoListRepository todoListRepository,
        TodoRepository todoRepository
    ) {
        this.todoListRepository = todoListRepository;
        this.todoRepository = todoRepository;
    }

    public TodoList getTodoList(Long id) {
        TodoList list = getListFromId(id);
        return list;
    }

    public void addTodo(Long id, TodoDTO todoRep) {
        TodoList list = getListFromId(id);
        Todo todo = new Todo(list, todoRep.name, todoRep.content, todoRep.status);
        list.addTodo(todo);
        todoRepository.save(todo);
        todoListRepository.save(list);
    }

    public void removeTodo(Long list_id, Long todo_id) {
        TodoList list = getListFromId(list_id);
        list.removeTodo(todo_id);
        todoRepository.deleteById(todo_id);
        todoListRepository.save(list);
    }

    public List<Todo> getTodoByStatus(Long id, boolean status) {
        TodoList list = getListFromId(id);
        return list.getTodos().stream().filter(todo -> todo.isStatus() == status).toList();
    }

    public void setTodoStatus(Long list_id, Long todo_id, boolean status) {
        TodoList list = getListFromId(list_id);
        list.setTodoStatus(todo_id, status);
        todoListRepository.save(list);
    }

    private TodoList getListFromId(Long id) {
        Optional<TodoList> optionalList = todoListRepository.findById(id);
        if(optionalList.isEmpty()) {
            throw new IllegalStateException(
                    "This todolist does not exists"
            );
        }
        return optionalList.get();
    }

    public void addTodoList(String name) {
        TodoList list = new TodoList(name);
        todoListRepository.save(list);
    }

    public void removeTodoList(Long list_id) {
        todoListRepository.deleteById(list_id);
    }

    public List<TodoList> getTodoLists() {
        return todoListRepository.findAll();
    }
}
