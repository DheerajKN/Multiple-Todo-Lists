package com.om.todos.service;

import java.util.List;

import com.om.todos.model.Todos;


public interface TodosService {

	List<Todos> fetchAllNonDeletedTodos();

	void deleteTodos(Todos todos);

	void addTodos(Todos todos);

	void updateTodo(Todos todoData, Todos todos);

}
