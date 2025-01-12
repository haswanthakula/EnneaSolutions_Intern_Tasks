import React from 'react';
import { Layout, Card, Table, Typography, Space, Tag } from 'antd';
import { useProduct } from '../../contexts/ProductContext';
import AdminNavbar from '../../components/AdminNavbar';
import dayjs from 'dayjs';

const { Content } = Layout;
const { Title, Text } = Typography;

const Orders = () => {
  const { orders } = useProduct();

  // Sort orders by date in descending order (most recent first)
  const sortedOrders = [...orders].sort((a, b) => 
    dayjs(b.date).valueOf() - dayjs(a.date).valueOf()
  );

  const columns = [
    {
      title: 'Order Date',
      dataIndex: 'date',
      key: 'date',
      render: (date) => dayjs(date).format('YYYY-MM-DD HH:mm'),
    },
    {
      title: 'Customer Details',
      key: 'customer',
      render: (_, record) => (
        <Space direction="vertical" size={0}>
          <Text strong>{record.name}</Text>
          <Text type="secondary">{record.email}</Text>
          <Text type="secondary">{record.phone}</Text>
        </Space>
      ),
    },
    {
      title: 'Items',
      key: 'items',
      render: (_, record) => (
        <Space direction="vertical">
          {record.items.map((item, index) => (
            <div key={index}>
              <Text>{item.title}</Text>
              <Text type="secondary"> x {item.quantity || 1}</Text>
            </div>
          ))}
        </Space>
      ),
    },
    {
      title: 'Total Amount',
      key: 'total',
      render: (_, record) => {
        const total = record.items.reduce((sum, item) => 
          sum + (item.price * (item.quantity || 1)), 0
        );
        return <Text strong>${total.toFixed(2)}</Text>;
      }
    },
  ];

  return (
    <Layout style={{ minHeight: '100vh' }}>
      <AdminNavbar />
      <Content style={{ padding: '24px' }}>
        <Card>
          <Title level={2}>Orders</Title>
          <Table
            columns={columns}
            dataSource={sortedOrders}
            rowKey="id"
            pagination={{
              pageSize: 10,
              showTotal: (total, range) => `${range[0]}-${range[1]} of ${total} orders`
            }}
          />
        </Card>
      </Content>
    </Layout>
  );
};

export default Orders;
