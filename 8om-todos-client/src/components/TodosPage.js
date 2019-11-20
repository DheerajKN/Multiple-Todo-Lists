import React from 'react';
import CreateTodo from './create-todo';
import {Button} from 'react-bootstrap';
import {Link} from 'react-router-dom';

import TodoOps from './TodoOps'

import "./../style.css";

import axios from 'axios';

export default class TodosPage extends React.Component {
    constructor(props){
        super(props);

        this.state = {
            todos: JSON.parse(localStorage.getItem("todosData"))
        }
    }
    toggleCompletion = todo => {
        axios.put(`http://localhost:8080/todo/${todo.id}`,
        {
            "completed": !todo.completed
        }).then(() => {
            this.fetchLatestData();
        });
    }
    deleteTodo = todo => {
        axios.delete(`http://localhost:8080/todo/${todo.id}`)
        .then(() => {
            this.fetchLatestData();
        });
    }
    fetchLatestData(){
        axios.get(`http://localhost:8080/todos/${this.props.match.params.id}`).then(res => {
            this.setState({todos: res.data})
            localStorage.setItem("todosData", JSON.stringify(res.data));
        });
    }
    renderAllTodos(){
        if (!this.state.todos.todos.length) {
            return <p className="tutorial">No ToDos Created!!</p>;
        }
        return this.state.todos.todos.map((todo, index) => {
            return (
                <TodoOps 
                    key={index}
                    data = {todo}
                    toggleCompletion={this.toggleCompletion}
                    deleteTodo={this.deleteTodo}
                    fetchLatestData={this.fetchLatestData}
                />
            )
        });
    }
    render(){
        return (
            <div>
                {/* <h1>{this.props.match.params.id}</h1> */}
                <h1 style={{marginBottom: '20px'}}>{this.state.todos.todosName}</h1>
                <CreateTodo routeId={this.props.match.params.id}/>
                <table>
                    <tbody>
                        {this.renderAllTodos()}
                    </tbody>
                </table>
                <Link to="/">
                    <Button variant="info">Back</Button>
                </Link>
            </div>
        )
    }
}