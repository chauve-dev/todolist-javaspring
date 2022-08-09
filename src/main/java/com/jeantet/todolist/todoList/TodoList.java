package com.jeantet.todolist.todoList;

import com.jeantet.todolist.todo.Todo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class TodoList {
    @Id
    @SequenceGenerator(
            name="todolist_sequence",
            sequenceName="todolist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "todolist_sequence"
    )
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "todoList", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Getter
    @Setter
    private List<Todo> todos;

    public TodoList() {
        this.todos = new ArrayList<>();
    }

    public TodoList(String name) {
        this.name = name;
        this.todos = new ArrayList<>();
    }

    public TodoList(int id, String name) {
        this.id = id;
        this.name = name;
        this.todos = new ArrayList<>();
    }

    public void addTodo(Todo todo) {
        this.todos.add(todo);
    }

    public void removeTodo(Long todo_id) {
        todos.forEach(todo -> {
            if(todo.getId() == todo_id) {
                todos.remove(todo);
            }
        });
    }

    public void setTodoStatus(Long todo_id, boolean status) {
        this.todos.forEach(todo -> {
            if(todo.getId() == todo_id) {
                int index = this.todos.indexOf(todo);
                todo.setStatus(status);
                this.todos.set(index, todo);
            }
        });
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", todos=" + todos +
                '}';
    }
}
