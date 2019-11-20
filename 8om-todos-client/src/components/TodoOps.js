import React from 'react';
import axios from 'axios';

import { FaTrashAlt, FaPencilAlt, FaSave, FaWindowClose } from "react-icons/fa";

import "./../style.css";

export default class TodoOps extends React.Component{
    constructor (props) {
        super(props);

        this.state = {
            isEditing: false
        };
    }
    editTask(){
        this.setState({isEditing: true});
    }
    editTaskSubmit (e) {
        axios.put(`http://localhost:8080/todo/${this.props.data.id}`,
        {
            todo: this.refs.task.value
        }).then(()=>{
            this.props.fetchLatestData();
            this.setState({ isEditing: false });
        })
        e.preventDefault();
    }
    render(){
        const {data} = this.props;
        console.log(data.completed)
        if(this.state.isEditing){
            return (
                <tr className={"todo-" + (data.completed ? "completed" : "not-completed") }>
                    <td>
                        <form onSubmit={this.editTaskSubmit.bind(this)}>
                            <input ref="task" defaultValue={data.todo} autoFocus />
                        </form>
                    </td>
                    <td value="submit"><FaSave onClick={this.editTaskSubmit.bind(this)}/></td>
                    <td onClick={() => this.setState({isEditing: !this.state.isEditing})}><FaWindowClose/></td>
                </tr>
            );
        }
        return(
            <tr className={"todo-" + (data.completed ? "completed" : "not-completed") }>
                <td onClick={() => this.props.toggleCompletion(data)}>{data.todo}</td>
                <td onClick={() => this.editTask()}><FaPencilAlt/></td>
                <td onClick={() => this.props.deleteTodo(data)}><FaTrashAlt/></td>
            </tr>
        )
    }
        
}