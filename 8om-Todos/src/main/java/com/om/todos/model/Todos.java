package com.om.todos.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "todos")
@Getter
@Setter
@NoArgsConstructor
public class Todos extends AbstractEntity{
	
	@Column(name = "todos_name")
	private String todosName;
	
	@OneToMany(targetEntity=Todo.class, mappedBy="todos", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE, orphanRemoval=false)
	private List<Todo> todos;
	
	public List<Todo> getTodos(){
		return todos.stream().filter(todo -> !todo.getDeleted())
				.collect(Collectors.toList());
	}
}
