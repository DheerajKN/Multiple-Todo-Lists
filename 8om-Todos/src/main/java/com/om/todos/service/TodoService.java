package com.om.todos.service;

import com.om.todos.model.Todo;
import com.om.todos.model.Todos;

public interface TodoService {

	void addTodo(Todo todo, Todos todos);

	void updateTodo(Todo todoData, Todo todo);

	void deleteTodo(Todo todo);

}
