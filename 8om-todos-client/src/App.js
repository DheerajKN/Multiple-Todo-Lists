import React from 'react';
import {BrowserRouter as Router} from 'react-router-dom';
import {Route} from 'react-router-dom';
import TodoCards from './components/TodoCards';
import "./style.css";
import axios from 'axios';
import TodosPage from './components/TodosPage';

export default class App extends React.Component {
  constructor (props) {
    super(props);

    this.state = {
        todos: []
    };
}
componentWillMount(){
    axios.get('http://localhost:8080/todos').then((res)=>{
      this.setState({todos: res.data});
    })
}
  render(){
    return(
      <Router>
        <div align="center" className="App">
          <Route path="/" exact component={() => <TodoCards todos={this.state.todos}/>} />
          <Route path="/todos/:id" component={TodosPage} />
        </div>
      </Router>
    )
  }
}
