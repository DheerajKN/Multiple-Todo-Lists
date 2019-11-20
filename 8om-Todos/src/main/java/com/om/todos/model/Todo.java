package com.om.todos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "todo")
@Getter
@Setter
@NoArgsConstructor
public class Todo extends AbstractEntity{
	
	@Column(name = "todo")
	private String todo;
	
	@Column(name = "completed")
	private Boolean completed;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="todos_id",referencedColumnName="id")
	private Todos todos;
}
