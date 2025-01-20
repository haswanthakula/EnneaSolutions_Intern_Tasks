import React from 'react';
import styled from 'styled-components';

const HomeContainer = styled.div`
  display: flex;
  flex-direction: column;
  height: calc(100vh - 80px); /* Subtract navbar height */
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  overflow: hidden;
`;

const HeroSection = styled.div`
  text-align: center;
  margin-bottom: 20px;

  h1 {
    margin-bottom: 10px;
    font-size: 2em;
    line-height: 1.2;
  }

  p {
    font-size: 1em;
    color: #666;
    margin-bottom: 10px;
  }
`;

const ReduxExplanationSection = styled.div`
  background-color: #f4f4f4;
  border-radius: 10px;
  padding: 20px;
  flex-grow: 1;
  overflow-y: auto; /* Allow internal scrolling if needed */
  
  h2 {
    text-align: center;
    margin-bottom: 20px;
    font-size: 1.5em;
    color: #333;
  }
`;

const ExplanationList = styled.ol`
  list-style-type: decimal;
  padding-left: 30px;
  margin: 0;
  
  li {
    margin-bottom: 15px;
    line-height: 1.6;
    font-size: 1em;
    color: #444;

    strong {
      display: block;
      margin-bottom: 5px;
      color: #222;
    }
  }
`;

const Home = () => {
  return (
    <HomeContainer>
      <HeroSection>
        <h1>Welcome to Our Redux-Powered E-Commerce Store</h1>
        <p>Discover a seamless shopping experience with state-of-the-art state management</p>
      </HeroSection>

      <ReduxExplanationSection>
        <ExplanationList>
          <li>
            <strong>Centralized State Management:</strong> Redux creates a single, centralized store 
            that holds the entire application state, making it easy to track and manage data across 
            different components.
          </li>
          <li>
            <strong>Predictable State Updates:</strong> Every state change goes through a reducer, 
            ensuring that updates are predictable and traceable. This helps in maintaining a clear 
            data flow throughout the application.
          </li>
          <li>
            <strong>Cart State Synchronization:</strong> When you add or remove items from the cart, 
            Redux instantly updates the state, which is immediately reflected across all components 
            without manual prop drilling.
          </li>
          <li>
            <strong>Performance Optimization:</strong> By using Redux Toolkit's createSlice and 
            createAsyncThunk, we optimize rendering and minimize unnecessary re-renders.
          </li>
          <li>
            <strong>Time-Travel Debugging:</strong> Redux DevTools allow developers to inspect 
            every state change, making debugging and understanding application flow much easier.
          </li>
        </ExplanationList>
      </ReduxExplanationSection>
    </HomeContainer>
  );
};

export default Home;
