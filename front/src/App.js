import React, { Component } from 'react';
import {
  BrowserRouter as Router,
  Redirect,
  Route,
  Switch,
} from 'react-router-dom';
import Layout from './component/Layout';
import Login from './pages/Login';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Redirect path="/" exact to="/connexion" />
          <Route path="/connexion" component={Login} />
          <Route path="/" component={Layout} />
        </Switch>
      </Router>
    );
  }
}

export default App;
