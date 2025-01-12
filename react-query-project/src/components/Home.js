import React from 'react';
import './Home.css';

const Home = () => {
  return (
    <div className="home-container">
      <h1 className="page-title">Hello! Welcome🤞</h1>
      
      <div className="content-section">
        <h2 className="section-title">Tanstack Query</h2>
        <ul className="point-list">
          <li>Used for fetching, caching, and updating data in React applications.</li>
          <li>Handles automatic caching, background refetching, pagination, and more.</li>
          <li>Provides hooks like `useQuery` for fetching data and `useMutation` for sending data.</li>
          <li>Improves performance by reducing unnecessary re-renders.</li>
          <li>Built-in support for error handling and retries on failure.</li>
        </ul>
      </div>

      <div className="content-section">
        <h2 className="section-title">Axios/Fetch API</h2>
        <ul className="point-list">
          <li>Traditional way of making HTTP requests in JavaScript using `fetch()` or Axios library.</li>
          <li>Requires manual handling of caching, loading states, and errors.</li>
          <li>Requires more boilerplate code compared to Tanstack Query.</li>
          <li>Axios has automatic JSON transformation, unlike `fetch()` which needs additional handling for response parsing.</li>
          <li>Used in smaller or simpler applications where additional features like caching are not required.</li>
        </ul>
      </div>
    </div>
  );
};

export default Home;
