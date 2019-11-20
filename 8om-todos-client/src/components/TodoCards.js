import React from 'react';
import Card from 'react-bootstrap/Card';

import axios from 'axios';

export default class TodoCards extends React.Component {
    state = {
        title: '',
        id: 0
    }
    reloadPage(){
        axios.get('http://localhost:8080/todos').then((res)=>{
            window.open(`http://localhost:3000/`, '_self')
        })
    }
    onSubmit = (e) => {
        e.preventDefault();
        if(this.state.id === 0){
            axios.post(`http://localhost:8080/todos`, 
            {
                todosName: this.state.title
            })
            .then(() => {
                this.reloadPage();
            })
        } else {
            axios.put(`http://localhost:8080/todos/${this.state.id}`, 
            {
                todosName: this.state.title
            })
            .then(() => {
                this.setState({title: '', id: 0})
                this.reloadPage();
            })
        }
    }
    
    onChange = (e) => this.setState({ [e.target.name]: e.target.value });
    deleteList = (id) => {
        axios.delete(`http://localhost:8080/todos/${id}`);
    }  

    renderBox(){
        return (
            <React.Fragment>
                <h1>TODOs</h1>
                <form onSubmit={this.onSubmit} style={{ display: 'flex' }}>
                    <input 
                    type="text" 
                    name="title" 
                    style={{ flex: '10', padding: '5px' }}
                    placeholder="Add a Todo List ..." 
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
            </React.Fragment>
        );
    }
    renderItems () {
        return this.props.todos.map((todosData, index) => {
            return (
                <Card tag="a" style={{ 
                    display: "inline-block", marginLeft: '10px', 
                    width: '18rem', marginTop:'10px', flex: '1' 
                }} key={index} 
                >
                <Card.Body>
                    <Card.Title 
                    onClick={() => {
                        localStorage.setItem("todosData", JSON.stringify(todosData));
                        window.open(`http://localhost:3000/todos/${todosData.id}`, '_self')
                        }
                    }>{todosData.todosName}</Card.Title>
                    <Card.Link href="#" onClick={()=>this.setState({title: todosData.todosName, id: todosData.id})}>Edit</Card.Link>
                    <Card.Link href="" onClick={() => this.deleteList(todosData.id)}
                        style={{color: 'red'}}>Delete</Card.Link>
                </Card.Body>
                </Card>
            )
        });
    }
    render () {
        if (!this.props.todos.length) {
            return <p className="tutorial">No ToDos Created!!</p>;
        }
        return (
            <React.Fragment>
                {this.renderBox()}
                {this.renderItems()}
            </React.Fragment>
        )
    }
}