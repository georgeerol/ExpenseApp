import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Category from "./Category";
import Home from "./Home";
import Expenses from "./Expenses";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/categories" element={<Category />} />
        <Route path="/expenses" element={<Expenses />} />
      </Routes>
    </Router>
  );
}

export default App;
