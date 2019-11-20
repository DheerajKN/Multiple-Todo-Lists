package com.om.todos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.om.todos.model.Todo;
import com.om.todos.model.Todos;
import com.om.todos.service.TodoService;

@RestController
@Validated
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@PostMapping("/todo/{todos}")
	public ResponseEntity<Void> addTodo(@RequestBody Todo todo, @PathVariable Todos todos){
		this.todoService.addTodo(todo,todos);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/todo/{todo}")
	public ResponseEntity<Void> updateTodo(@RequestBody Todo todoData, @PathVariable Todo todo){
		this.todoService.updateTodo(todoData, todo);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/todo/{todo}")
	public ResponseEntity<Void> deleteTodo(@PathVariable Todo todo){
		this.todoService.deleteTodo(todo);
		return ResponseEntity.ok().build();
	}
}
