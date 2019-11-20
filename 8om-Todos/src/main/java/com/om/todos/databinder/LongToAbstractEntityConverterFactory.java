package com.om.todos.databinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import com.om.todos.model.AbstractEntity;
import com.om.todos.model.Todo;
import com.om.todos.model.Todos;
import com.om.todos.repo.TodoRepository;
import com.om.todos.repo.TodosRepository;

public class LongToAbstractEntityConverterFactory implements ConverterFactory<Long, AbstractEntity> {

	@Autowired
	private static TodoRepository todoRepository;
	
	@Autowired
	private static TodosRepository todosRepository;

	@Override
	public <T extends AbstractEntity> Converter<Long, T> getConverter(Class<T> targetType) {
		return new StringToAbstractEntityConverter<T>(targetType);
	}
	
	private static class StringToAbstractEntityConverter<T>
    implements Converter<Long, T> {

      private Class<T> targetClass;

      public StringToAbstractEntityConverter(Class<T> targetClass) {
          this.targetClass = targetClass;
      }

      @Override
      public T convert(Long source) {
          if(this.targetClass == Todo.class) {
              return (T) todoRepository.findById(source);
          }
          else if(this.targetClass == Todos.class) {
              return (T) todosRepository.findById(source);
          } else {
              return null;
          }
      }
  }
}