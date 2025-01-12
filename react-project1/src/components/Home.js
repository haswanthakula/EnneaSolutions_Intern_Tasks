import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const HomeContainer = styled.div`
  text-align: center;
  margin-top: 50px;
`;

const Button = styled.button`
  background-color: #007bff;
  color: white;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  &:hover {
    background-color: #0056b3;
  }
`;

const Home = () => {
  return (
    <HomeContainer>
      <h1>Welcome to Our Store</h1> <br />
      <Link to="/products">
        <Button>View Products</Button>
      </Link>
    </HomeContainer>
  );
};

export default Home;
