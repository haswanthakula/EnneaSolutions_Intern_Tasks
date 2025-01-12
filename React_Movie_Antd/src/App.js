import React, { Suspense } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { Spin } from 'antd';
import { AuthProvider, useAuth } from './contexts/AuthContext';
import { ProductProvider } from './contexts/ProductContext';

// Lazy load components
const LandingPage = React.lazy(() => import('./pages/LandingPage'));
const UserDashboard = React.lazy(() => import('./pages/UserDashboard'));
const AdminDashboard = React.lazy(() => import('./pages/admin/Dashboard'));
const Products = React.lazy(() => import('./pages/admin/Products'));
const Orders = React.lazy(() => import('./pages/admin/Orders'));

const LoadingFallback = () => (
  <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
    <Spin size="large" />
  </div>
);

const ProtectedRoute = ({ children, allowedRole }) => {
  const { user } = useAuth();
  
  if (!user) {
    return <Navigate to="/" />;
  }

  if (allowedRole && user.role !== allowedRole) {
    return <Navigate to={user.role === 'admin' ? '/admin' : '/user'} />;
  }

  return children;
};

function App() {
  return (
    <Router>
      <AuthProvider>
        <ProductProvider>
          <Suspense fallback={<LoadingFallback />}>
            <Routes>
              <Route path="/" element={<LandingPage />} />
              
              <Route path="/user" element={
                <ProtectedRoute allowedRole="user">
                  <UserDashboard />
                </ProtectedRoute>
              } />

              <Route path="/admin/*" element={
                <ProtectedRoute allowedRole="admin">
                  <Routes>
                    <Route path="/" element={<AdminDashboard />} />
                    <Route path="products" element={<Products />} />
                    <Route path="orders" element={<Orders />} />
                  </Routes>
                </ProtectedRoute>
              } />
            </Routes>
          </Suspense>
        </ProductProvider>
      </AuthProvider>
    </Router>
  );
}

export default App;
