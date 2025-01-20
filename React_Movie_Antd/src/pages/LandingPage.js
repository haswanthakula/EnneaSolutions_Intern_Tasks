import React, { useEffect } from 'react';
import { Card, Row, Col, Button, Typography, Layout } from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import { useAuth } from '../contexts/AuthContext';
import { useNavigate } from 'react-router-dom';

const { Title } = Typography;
const { Content } = Layout;

const LandingPage = () => {
  const { login, user } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (user) {
      navigate(user.role === 'admin' ? '/admin' : '/user');
    }
  }, [user, navigate]);

  if (user) {
    return null; // Don't render anything while redirecting
  }

  return (
    <Layout>
      <Content style={{ padding: '50px', minHeight: '100vh', background: '#f0f2f5' }}>
        <Title level={2} style={{ textAlign: 'center', marginBottom: '40px' }}>
          Welcome to Our Store
        </Title>
        
        <Row gutter={[16, 16]} justify="center">
          <Col xs={24} sm={12} md={8} lg={6}>
            <Card
              hoverable
              style={{ textAlign: 'center' }}
            >
              <LockOutlined style={{ fontSize: '48px', color: '#1890ff', marginBottom: '24px' }} />
              <Title level={4}>Admin Login</Title>
              <Button type="primary" size="large" block onClick={() => login('admin')}>
                Login as Admin
              </Button>
            </Card>
          </Col>
          
          <Col xs={24} sm={12} md={8} lg={6}>
            <Card
              hoverable
              style={{ textAlign: 'center' }}
            >
              <UserOutlined style={{ fontSize: '48px', color: '#52c41a', marginBottom: '24px' }} />
              <Title level={4}>User Access</Title>
              <Button type="primary" size="large" block onClick={() => login('user')}>
                Continue as User
              </Button>
            </Card>
          </Col>
        </Row>
      </Content>
    </Layout>
  );
};

export default LandingPage;
