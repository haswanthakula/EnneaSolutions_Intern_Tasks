import React, { useState, useEffect, useMemo } from 'react';
import { Layout, Card, Row, Col, Typography, Space, Statistic, DatePicker } from 'antd';
import { ShoppingOutlined, DollarOutlined, UserOutlined } from '@ant-design/icons';
import { useProduct } from '../../contexts/ProductContext';
import AdminNavbar from '../../components/AdminNavbar';
import dayjs from 'dayjs';

const { Content } = Layout;
const { Title, Text } = Typography;
const { RangePicker } = DatePicker;

const Dashboard = () => {
  const { orders, products } = useProduct();
  const [dateRange, setDateRange] = useState([
    dayjs().subtract(7, 'day'),
    dayjs()
  ]);

  const filteredOrders = useMemo(() => {
    if (!orders || !dateRange) return [];
    
    const [startDate, endDate] = dateRange;
    return orders.filter(order => {
      const orderDate = dayjs(order.date);
      return orderDate.isAfter(startDate) && orderDate.isBefore(endDate.add(1, 'day'));
    });
  }, [orders, dateRange]);

  const stats = useMemo(() => {
    if (!filteredOrders.length) return {
      totalOrders: 0,
      totalRevenue: 0,
      averageOrderValue: 0,
      uniqueCustomers: 0
    };

    const totalOrders = filteredOrders.length;
    const totalRevenue = filteredOrders.reduce((sum, order) => 
      sum + order.items.reduce((itemSum, item) => itemSum + (item.price * (item.quantity || 1)), 0), 0
    );
    const uniqueCustomers = new Set(filteredOrders.map(order => order.email)).size;

    return {
      totalOrders,
      totalRevenue,
      averageOrderValue: totalRevenue / totalOrders,
      uniqueCustomers
    };
  }, [filteredOrders]);

  // Calculate most sold movies
  const mostSoldMovies = useMemo(() => {
    const movieSales = {};
    orders.forEach(order => {
      order.items.forEach(item => {
        if (!movieSales[item.id]) {
          movieSales[item.id] = {
            ...item,
            totalSold: 0
          };
        }
        movieSales[item.id].totalSold += (item.quantity || 1);
      });
    });
    return Object.values(movieSales)
      .sort((a, b) => b.totalSold - a.totalSold)
      .slice(0, 6);
  }, [orders]);

  return (
    <Layout style={{ minHeight: '100vh' }}>
      <AdminNavbar />
      <Content style={{ padding: '24px' }}>
        <Row justify="space-between" align="middle" style={{ marginBottom: 24 }}>
          <Col>
            <Title level={2}>Dashboard Overview</Title>
          </Col>
          <Col>
            <RangePicker
              value={dateRange}
              onChange={setDateRange}
              allowClear={false}
              style={{ width: 300 }}
              disabledDate={current => current && current > dayjs().endOf('day')}
            />
          </Col>
        </Row>

        <Row gutter={[24, 24]}>
          <Col xs={24} sm={12} lg={6}>
            <Card>
              <Statistic
                title="Total Orders"
                value={stats.totalOrders}
                prefix={<ShoppingOutlined />}
                valueStyle={{ color: '#3f8600' }}
              />
            </Card>
          </Col>
          <Col xs={24} sm={12} lg={6}>
            <Card>
              <Statistic
                title="Total Revenue"
                value={stats.totalRevenue}
                precision={2}
                prefix={<DollarOutlined />}
                valueStyle={{ color: '#3f8600' }}
              />
            </Card>
          </Col>
          <Col xs={24} sm={12} lg={6}>
            <Card>
              <Statistic
                title="Average Order Value"
                value={stats.averageOrderValue}
                precision={2}
                prefix={<DollarOutlined />}
                valueStyle={{ color: '#3f8600' }}
              />
            </Card>
          </Col>
          <Col xs={24} sm={12} lg={6}>
            <Card>
              <Statistic
                title="Unique Customers"
                value={stats.uniqueCustomers}
                prefix={<UserOutlined />}
                valueStyle={{ color: '#3f8600' }}
              />
            </Card>
          </Col>
        </Row>

        <Card style={{ marginTop: 24 }}>
          <Title level={4}>Most Sold Movies</Title>
          <Row gutter={[16, 16]}>
            {mostSoldMovies.map(movie => (
              <Col xs={24} sm={12} md={8} lg={6} xl={4} key={movie.id}>
                <Card
                  hoverable
                  cover={
                    <img
                      alt={movie.title}
                      src={movie.image}
                      style={{ height: 300, objectFit: 'cover' }}
                    />
                  }
                >
                  <Card.Meta
                    title={movie.title}
                    description={
                      <Space direction="vertical">
                        <Text>Total Sold: {movie.totalSold}</Text>
                        <Text>Revenue: ${(movie.price * movie.totalSold).toFixed(2)}</Text>
                      </Space>
                    }
                  />
                </Card>
              </Col>
            ))}
          </Row>
        </Card>
      </Content>
    </Layout>
  );
};

export default Dashboard;
