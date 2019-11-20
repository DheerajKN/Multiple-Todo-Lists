package com.om.todos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.om.todos.model.Todos;
import com.om.todos.service.TodosService;

@RestController
@Validated
public class TodosController {

	@Autowired
	private TodosService todosService;
	
	@GetMapping("/todos")
	public List<Todos> getAllTodos(){
		return this.todosService.fetchAllNonDeletedTodos();
	}
	
	@GetMapping("/todos/{todos}")
	public ResponseEntity<Todos> getSpecificTodos(@PathVariable Todos todos){
		return ResponseEntity.ok().body(todos);
	}
	
	@PostMapping("/todos")
	public ResponseEntity<Void> addTodo(@RequestBody Todos todos){
		this.todosService.addTodos(todos);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/todos/{todos}")
	public ResponseEntity<Void> updateTodo(@RequestBody Todos todosData, @PathVariable Todos todos){
		this.todosService.updateTodo(todosData, todos);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/todos/{todos}")
	public ResponseEntity<Void> deleteTodo(@PathVariable Todos todos){
		this.todosService.deleteTodos(todos);
		return ResponseEntity.ok().build();
	}
}
