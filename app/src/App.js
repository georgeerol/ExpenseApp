import React from "react";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import Category from "./Category";
import Home from "./Home";
import Expenses from "./Expenses";

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/" exact={true} component={Home} />
        <Route path="/categories" exact={true} component={Category} />
        <Route path="/expenses" exact={true} component={Expenses} />
      </Switch>
    </Router>
  );
}

export default App;
