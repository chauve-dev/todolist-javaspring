package com.jeantet.todolist.todo;

import com.jeantet.todolist.todoList.TodoList;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
public class Todo {
    @Id
    @SequenceGenerator(
            name="todo_sequence",
            sequenceName="todo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "todo_sequence"
    )
    @Getter
    @Setter
    private long id;

    @ManyToOne
    @JoinColumn(name="todolist_id", nullable=false)
    private TodoList todoList;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private boolean status;

    public Todo() {
    }

    public Todo(int id, TodoList todoList, String name, String content, boolean status) {
        this.id = id;
        this.todoList = todoList;
        this.name = name;
        this.content = content;
        this.status = status;
    }

    public Todo(TodoList todoList, String name, String content, boolean status) {
        this.todoList = todoList;
        this.name = name;
        this.content = content;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }
}
