import React from 'react';
import { NavLink } from 'react-router-dom';
import './Navbar.css';

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-container">
        <NavLink 
          to="/" 
          className={({ isActive }) => 
            isActive ? 'navbar-item active' : 'navbar-item'
          }
        >
          Home
        </NavLink>
        <NavLink 
          to="/traditional" 
          className={({ isActive }) => 
            isActive ? 'navbar-item active' : 'navbar-item'
          }
        >
          Traditional
        </NavLink>
        <NavLink 
          to="/tanstack" 
          className={({ isActive }) => 
            isActive ? 'navbar-item active' : 'navbar-item'
          }
        >
          Tanstack
        </NavLink>
        <NavLink 
          to="/mutation" 
          className={({ isActive }) => 
            isActive ? 'navbar-item active' : 'navbar-item'
          }
        >
          Mutation
        </NavLink>
      </div>
    </nav>
  );
};

export default Navbar;
