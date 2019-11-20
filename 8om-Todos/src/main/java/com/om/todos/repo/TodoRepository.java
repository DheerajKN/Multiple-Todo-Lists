package com.om.todos.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.om.todos.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
