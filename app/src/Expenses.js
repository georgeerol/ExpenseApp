import React, { useEffect, useState } from "react";
import AppNav from "./AppNav";
import DatePicker from "react-datepicker";
import "./App.css";
import "react-datepicker/dist/react-datepicker.css";
import { Container, Table, Input, Button, Label, Form, FormGroup } from "reactstrap";
import { Link } from "react-router-dom";
import Moment from "react-moment";

function Expenses(props) {
  const emptyItem = {
    description: "",
    expenseDate: new Date(),
    id: 104,
    location: "",
    category: { id: 1, name: "Travel" }
  };

  const [isLoading, setIsLoading] = useState(false);
  const [categories, setCategories] = useState([]);
  const [expenses, setExpenses] = useState([]);
  const [item, setItem] = useState(emptyItem);

  useEffect(() => {
    let isMounted = true;
    (async () => {
      try {
        const response = await fetch("/api/categories");
        const body = await response.json();
        if (isMounted) setCategories(body);
      } catch (e) {}
      try {
        const responseExp = await fetch("/api/expenses");
        const bodyExp = await responseExp.json();
        if (isMounted) setExpenses(bodyExp);
      } catch (e) {}
      if (isMounted) setIsLoading(false);
    })();
    return () => {
      isMounted = false;
    };
  }, []);

  const handleSubmit = async (event) => {
    event.preventDefault();
    await fetch(`/api/expenses`, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(item)
    });
    props.history.push("/expenses");
  };

  const handleChange = (event) => {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    setItem((prev) => ({ ...prev, [name]: value }));
  };

  const handleDateChange = (date) => {
    setItem((prev) => ({ ...prev, expenseDate: date }));
  };

  const handleCategoryChange = (event) => {
    const value = parseInt(event.target.value, 10);
    const selected = categories.find((c) => c.id === value) || { id: value };
    setItem((prev) => ({ ...prev, category: selected }));
  };

  const remove = async (id) => {
    await fetch(`/api/expenses/${id}`, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      }
    });
    setExpenses((prev) => prev.filter((i) => i.id !== id));
  };

  const title = <h3>Add Expense</h3>;

  if (isLoading) {
    return <div>Loading....</div>;
  }

  const optionList = categories.map((category) => (
    <option value={category.id} key={category.id}>
      {category.name}
    </option>
  ));

  const rows = expenses.map((expense) => (
    <tr key={expense.id}>
      <td>{expense.description}</td>
      <td>{expense.location}</td>
      <td>
        <Moment date={expense.expenseDate} format="YYYY/MM/DD" />
      </td>
      <td>{expense.category.name}</td>
      <td>
        <Button size="sm" color="danger" onClick={() => remove(expense.id)}>
          Delete
        </Button>
      </td>
    </tr>
  ));

  return (
    <div>
      <AppNav />
      <Container>
        {title}
        <Form onSubmit={handleSubmit}>
          <FormGroup>
            <Label for="description">Title</Label>
            <Input
              type="text"
              name="description"
              id="description"
              onChange={handleChange}
              autoComplete="name"
            />
          </FormGroup>

          <FormGroup>
            <Label for="category">Category</Label>
            <select
              name="categoryId"
              onChange={handleCategoryChange}
              value={item.category.id}
            >
              {optionList}
            </select>
          </FormGroup>

          <FormGroup>
            <Label for="city">Date</Label>
            <DatePicker selected={item.expenseDate} onChange={handleDateChange} />
          </FormGroup>

          <div className="row">
            <FormGroup className="col-md-4 mb-3">
              <Label for="location">Location</Label>
              <Input type="text" name="location" id="location" onChange={handleChange} />
            </FormGroup>
          </div>

          <FormGroup>
            <Button color="primary" type="submit">
              Save
            </Button>{" "}
            <Button color="secondary" tag={Link} to="/">
              Cancel
            </Button>
          </FormGroup>
        </Form>
      </Container>

      <Container>
        <h3>Expense List</h3>
        <Table className="mt-4">
          <thead>
            <tr>
              <th width="30%">Description</th>
              <th width="10%">Location</th>
              <th> Date</th>
              <th> Category</th>
              <th width="10%">Action</th>
            </tr>
          </thead>
          <tbody>{rows}</tbody>
        </Table>
      </Container>
    </div>
  );
}

export default Expenses;
