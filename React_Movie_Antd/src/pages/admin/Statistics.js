import React from 'react';
import { Layout, Card, Row, Col } from 'antd';
import AdminNavbar from '../../components/AdminNavbar';
import { useProduct } from '../../contexts/ProductContext';

const { Content } = Layout;

const Statistics = () => {
  const { orders, products } = useProduct();

  return (
    <Layout>
      <AdminNavbar />
      <Content style={{ padding: '24px' }}>
        <Row gutter={[16, 16]}>
          <Col span={8}>
            <Card title="Total Orders">
              <h2>{orders.length}</h2>
            </Card>
          </Col>
          <Col span={8}>
            <Card title="Total Products">
              <h2>{products.length}</h2>
            </Card>
          </Col>
          <Col span={8}>
            <Card title="Total Revenue">
              <h2>
                ${orders.reduce((sum, order) => sum + order.total, 0).toFixed(2)}
              </h2>
            </Card>
          </Col>
        </Row>
      </Content>
    </Layout>
  );
};

export default Statistics;
