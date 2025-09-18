import React from "react";
import { Nav, Navbar, NavItem, NavbarBrand, NavLink } from "reactstrap";

function AppNav() {
  return (
    <div>
      <Navbar color="dark" dark expand="md">
        <NavbarBrand href="/">Expense Tracker Application</NavbarBrand>
        <Nav className="ms-auto" navbar>
          <NavItem>
            <NavLink href="/">Home</NavLink>
          </NavItem>
          <NavItem>
            <NavLink href="/categories">Categories</NavLink>
          </NavItem>
          <NavItem>
            <NavLink href="/expenses">Expenses</NavLink>
          </NavItem>
        </Nav>
      </Navbar>
    </div>
  );
}

export default AppNav;
