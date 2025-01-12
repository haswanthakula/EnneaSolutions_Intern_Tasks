import React from 'react';
import { Layout, Button } from 'antd';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import LogoutOutlined from '@ant-design/icons/lib/icons/LogoutOutlined';

const { Header } = Layout;

const AdminNavbar = () => {
  const { logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <Header style={{ 
      background: '#fff', 
      padding: '0 20px', 
      display: 'flex', 
      justifyContent: 'space-between',
      alignItems: 'center',
      boxShadow: '0 2px 8px rgba(0,0,0,0.1)'
    }}>
      <Link to="/admin" style={{ 
        fontSize: '20px', 
        fontWeight: 'bold',
        color: '#000'
      }}>
        Movie Admin
      </Link>
      <div style={{ display: 'flex', gap: '16px' }}>
        <Link to="/admin">
          <Button type="text">Dashboard</Button>
        </Link>
        <Link to="/admin/products">
          <Button type="text">Products</Button>
        </Link>
        <Link to="/admin/orders">
          <Button type="text">Orders</Button>
        </Link>
        <Link>
        <Button 
          type="primary" 
          danger
          icon={<LogoutOutlined />}
          onClick={handleLogout}
        >
          Logout
        </Button>
        </Link>
      </div>
    </Header>
  );
};

export default AdminNavbar;
