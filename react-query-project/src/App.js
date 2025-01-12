import React from 'react';
import './App.css';
import { NavLink } from 'react-router-dom';
import { BrowserRouter, Routes, Route} from 'react-router-dom';
import Tanstack from './components/Tanstack';
import Mutation from './components/Mutation';
import  Traditional from './components/Traditional';
import Home from './components/Home';


function App() {
  return (
    <BrowserRouter>
      <div>
        <nav>
          <ul>
              <li>
                <NavLink to="/" activeClassName="active">Home</NavLink>
              </li>
              <li>
                <NavLink to="/traditional" activeClassName="active">Traditional</NavLink>
              </li>
              <li>
                <NavLink to="/tanstack" activeClassName="active">Tanstack</NavLink>
              </li>
              <li>
                <NavLink to="/mutation" activeClassName="active">Mutation</NavLink>
              </li>
          </ul>
        </nav>
          <Routes>
            <Route path='/' element={<Home />} />
            <Route exact path='/traditional' element={<Traditional />} />
            <Route exact path='/tanstack' element={<Tanstack />} />
            <Route exact path='/mutation' element={<Mutation />} />
          </Routes>
      </div>
    </BrowserRouter>
  )
}

export default App;