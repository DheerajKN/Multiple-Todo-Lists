import React, { Component } from 'react';

import axios from 'axios';

export class CreateTodo extends Component {
  state = {
    todo: ''
  }

  onSubmit = (e) => {
    e.preventDefault();
    axios.post(`http://localhost:8080/todo/${this.props.routeId}`, 
    {
        todo: this.state.todo
    })
    .then(() => {
        axios.get(`http://localhost:8080/todos/${this.props.routeId}`).then(res => {
            localStorage.setItem("todosData", JSON.stringify(res.data));
            window.open(`http://localhost:3000/todos/${this.props.routeId}`, '_self')
        });
    })
  }

  onChange = (e) => this.setState({ [e.target.name]: e.target.value });

  render() {
    return (
      <form onSubmit={this.onSubmit} style={{ display: 'flex' }}>
        <input 
          type="text" 
          name="todo" 
          style={{ flex: '10', padding: '5px' }}
          placeholder="Add Todo ..." 
          value={this.state.title}
          onChange={this.onChange}
        />
        <input 
          type="submit" 
          value="Submit" 
          className="btn"
          style={{flex: '1'}}
        />
      </form>
    )
  }
}


export default CreateTodo