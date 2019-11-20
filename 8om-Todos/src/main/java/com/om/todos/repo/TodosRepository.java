package com.om.todos.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.om.todos.model.Todos;

public interface TodosRepository extends JpaRepository<Todos, Long> {
	 List<Todos> findByDeletedIsFalse();
}
