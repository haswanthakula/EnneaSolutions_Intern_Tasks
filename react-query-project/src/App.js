import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import Navbar from './components/Navbar';
import Home from './components/Home';
import Tanstack from './components/Tanstack';
import Traditional from './components/Traditional';
import Mutation from './components/Mutation';

const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/tanstack" element={<Tanstack />} />
          <Route path="/traditional" element={<Traditional />} />
          <Route path="/mutation" element={<Mutation />} />
        </Routes>
      </Router>
    </QueryClientProvider>
  );
}

export default App;