package com.om.todos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.om.todos.model.Todos;
import com.om.todos.repo.TodosRepository;
import com.om.todos.service.TodosService;

@Service
public class TodosServiceImpl implements TodosService {

	@Autowired
	private TodosRepository todosRepository;

	@Override
	public List<Todos> fetchAllNonDeletedTodos() {
		return todosRepository.findByDeletedIsFalse();
	}

	@Override
	public void deleteTodos(Todos todos) {
		todos.setDeleted(true);
		todosRepository.save(todos);
	}

	@Override
	public void addTodos(Todos todos) {
		Todos newTodos = new Todos();
		newTodos.setDeleted(false);
		newTodos.setTodosName(todos.getTodosName());
		
		todosRepository.save(newTodos);
	}

	@Override
	public void updateTodo(Todos todosData, Todos todos) {
		todos.setTodosName(todosData.getTodosName() == null ? todos.getTodosName() : todosData.getTodosName());
		
		todosRepository.save(todos);
	}

}
