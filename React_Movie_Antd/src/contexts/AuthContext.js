import React, { createContext, useContext, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { message } from 'antd';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const storedRole = localStorage.getItem('userRole');
    if (storedRole) {
      setUser({ role: storedRole });
    }
  }, []);

  const login = (role) => {
    localStorage.setItem('userRole', role);
    setUser({ role });
    message.success(`Logged in as ${role}`);
    navigate(role === 'admin' ? '/admin' : '/user');
  };

  const logout = () => {
    localStorage.removeItem('userRole');
    setUser(null);
    message.success('Logged out successfully');
    navigate('/');
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};

export default AuthContext;
