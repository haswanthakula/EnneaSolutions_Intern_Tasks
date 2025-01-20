
import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const Nav = styled.nav`
  background-color: #343a40;
  padding: 10px 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const NavLinks = styled.div`
  display: flex;
  gap: 20px;
`;

const NavLink = styled(Link)`
  color: white;
  text-decoration: none;
  font-size: 1.1rem;
  &:hover {
    color: #007bff;
  }
`;

const Brand = styled.h1`
  color: white;
  font-size: 1.5rem;
`;

const NavBar = () => {
  return (
    <Nav>
      <Brand>MyStore</Brand>
      <NavLinks>
        <NavLink to="/">Home</NavLink>
        <NavLink to="/products">Products</NavLink>
        <NavLink to="/cart">Cart</NavLink>
      </NavLinks>
    </Nav>
  );
};

export default NavBar;
