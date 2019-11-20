package com.om.todos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.om.todos.model.Todo;
import com.om.todos.model.Todos;
import com.om.todos.repo.TodoRepository;
import com.om.todos.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	@Override
	public void addTodo(Todo todo, Todos todos) {
		Todo newTodo = new Todo();
		newTodo.setCompleted(false);
		newTodo.setDeleted(false);
		newTodo.setTodo(todo.getTodo());
		
		newTodo.setTodos(todos);
		
		todoRepository.save(newTodo);
	}

	@Override
	public void updateTodo(Todo todoData, Todo todo) {
		todo.setCompleted(todoData.getCompleted() != null ? todoData.getCompleted() : todo.getCompleted());
		todo.setTodo(todoData.getTodo() != null ? todoData.getTodo() : todo.getTodo());
		
		todoRepository.save(todo);
	}

	@Override
	public void deleteTodo(Todo todo) {
		todo.setDeleted(true);
		
		todoRepository.save(todo);
	}
}