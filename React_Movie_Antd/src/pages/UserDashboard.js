import React, { useState, useMemo } from 'react';
import {
  Layout,
  Card,
  Button,
  Row,
  Col,
  Space,
  Typography,
  Input,
  Select,
  Badge,
  Drawer,
  List,
  Avatar,
  Tag,
  Modal,
  Form,
  Checkbox
} from 'antd';
import {
  ShoppingCartOutlined,
  SearchOutlined,
  LogoutOutlined,
  DeleteOutlined,
  PlusOutlined,
  MinusOutlined,
  StarFilled,
  UserOutlined,
  ArrowUpOutlined
} from '@ant-design/icons';
import { useProduct } from '../contexts/ProductContext';
import { useAuth } from '../contexts/AuthContext';
import { useNavigate } from 'react-router-dom';

const { Header, Content } = Layout;
const { Meta } = Card;
const { Title, Text } = Typography;
const { Option } = Select;

const UserDashboard = () => {
  // Context values and state hooks
  const {
    products,
    loading,
    addToCart,
    cart,
    removeFromCart,
    updateQuantity,
    clearCart,
    placeOrder,
    getTotalPrice
  } = useProduct();
  const { logout } = useAuth();
  const navigate = useNavigate();
  
  const [cartVisible, setCartVisible] = useState(false);
  const [checkoutVisible, setCheckoutVisible] = useState(false);
  const [searchText, setSearchText] = useState('');
  const [selectedCategories, setSelectedCategories] = useState([]);
  const [selectedYears, setSelectedYears] = useState([]);
  const [form] = Form.useForm();

  // Utility functions
  const handleLogout = () => {
    logout();
    navigate('/');
  };

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'short',
      day: 'numeric'
    });
  };

  const getYear = (dateString) => new Date(dateString).getFullYear();
  
  const getRating = (movie) => movie.rating?.rate || 0;
  const getRatingCount = (movie) => movie.rating?.count || 0;

  // Categories and years for filters
  const categories = useMemo(() => {
    if (!products) return [];
    return [...new Set(products.map(movie => movie.category))].sort();
  }, [products]);

  const years = useMemo(() => {
    if (!products) return [];
    return [...new Set(products.map(movie => getYear(movie.releaseDate)))].sort((a, b) => b - a);
  }, [products]);

  // Filtered products based on categories, search, and years
  const filteredProducts = useMemo(() => {
    if (!products) return [];
    if (selectedCategories.length === 0) return products;
    return products.filter(product => selectedCategories.includes(product.category));
  }, [products, selectedCategories]);

  const filteredMovies = filteredProducts.filter(movie => {
    const matchesSearch = movie.title.toLowerCase().includes(searchText.toLowerCase());

    const matchesYear = selectedYears.length === 0 || selectedYears.includes(getYear(movie.releaseDate));
    
    return matchesSearch && matchesYear;
  });

  const handleCategoryChange = (checkedValues) => {
    setSelectedCategories(checkedValues);
  };

  const handleSelectAllCategories = (e) => {
    setSelectedCategories(e.target.checked ? categories : []);
  };

  // Checkout modal handler
  const handleCheckout = async (values) => {
    const success = await placeOrder(values);
    if (success) {
      setCheckoutVisible(false);
      setCartVisible(false);
      form.resetFields();
      navigate('/user');
    }
  };

  const checkoutModal = (
    <Modal
      title="Checkout"
      open={checkoutVisible}
      onCancel={() => {
        setCheckoutVisible(false);
        form.resetFields();
      }}
      footer={null}
    >
      <Form
        form={form}
        layout="vertical"
        onFinish={handleCheckout}
        initialValues={{
          name: '',
          email: '',
          phone: '',
          address: ''
        }}
      >
        <Form.Item
          name="name"
          label="Full Name"
          rules={[{ required: true, message: 'Please enter your name' }]}
        >
          <Input />
        </Form.Item>

        <Form.Item
          name="email"
          label="Email"
          rules={[
            { required: true, message: 'Please enter your email' },
            { type: 'email', message: 'Please enter a valid email' }
          ]}
        >
          <Input />
        </Form.Item>

        <Form.Item
          name="phone"
          label="Phone Number"
          rules={[
            { required: true, message: 'Please enter your phone number' },
            { pattern: /^\d+$/, message: 'Please enter a valid phone number' }
          ]}
        >
          <Input />
        </Form.Item>

        <Form.Item
          name="address"
          label="Delivery Address"
          rules={[{ required: true, message: 'Please enter your address' }]}
        >
          <Input.TextArea rows={3} />
        </Form.Item>

        <Form.Item>
          <Space style={{ width: '100%', justifyContent: 'flex-end' }}>
            <Button
              onClick={() => {
                setCheckoutVisible(false);
                form.resetFields();
              }}
            >
              Cancel
            </Button>
            <Button type="primary" htmlType="submit">
              Place Order (${getTotalPrice().toFixed(2)})
            </Button>
          </Space>
        </Form.Item>
      </Form>
    </Modal>
  );

  const scrollToTop = () => {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  return (
    <Layout>
      <Header style={{ background: '#fff', padding: '0 24px', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <Title level={3} style={{ margin: 0 }}>Movie Catalog</Title>
        <Space>
          <Badge count={cart.length} size="small">
            <Button
              type="primary"
              icon={<ShoppingCartOutlined />}
              onClick={() => setCartVisible(true)}
            >
              Cart ({cart.length} items)
            </Button>
          </Badge>
          <Button
            type="primary"
            danger
            icon={<LogoutOutlined />}
            onClick={handleLogout}
          >
            Logout
          </Button>
        </Space>
      </Header>

      <Content style={{ padding: '24px', minHeight: 'calc(100vh - 64px)', background: '#f0f2f5' }}>
        <Row gutter={[16, 16]} style={{ marginBottom: 24 }}>
          <Col xs={24} sm={12} md={8} lg={6}>
            <Input
              placeholder="Search movies..."
              prefix={<SearchOutlined />}
              value={searchText}
              onChange={e => setSearchText(e.target.value)}
            />
          </Col>
          <Col xs={24} sm={12} md={8} lg={6}>
            <Select
              style={{ width: '100%' }}
              placeholder="Select Years"
              value={selectedYears}
              onChange={setSelectedYears}
              mode="multiple"
              allowClear
            >
              {years.map(year => (
                <Option key={year} value={year}>{year}</Option>
              ))}
            </Select>
          </Col>
        </Row>

        <div style={{ marginBottom: 24 }}>
          <Space direction="vertical" style={{ width: '100%' }}>
            <Text strong>Filter by Category:</Text>
            <Checkbox
              onChange={handleSelectAllCategories}
              checked={selectedCategories.length === categories.length}
              indeterminate={selectedCategories.length > 0 && selectedCategories.length < categories.length}
            >
              Select All
            </Checkbox>
            <Checkbox.Group
              options={categories.map(cat => ({ label: cat, value: cat }))}
              value={selectedCategories}
              onChange={handleCategoryChange}
              style={{ display: 'flex', flexWrap: 'wrap', gap: 8 }}
            />
          </Space>
        </div>

        <Row gutter={[16, 16]}>
          {loading ? (
            <Col span={24}>
              <div style={{ textAlign: 'center', padding: '50px' }}>Loading...</div>
            </Col>
          ) : filteredMovies.length === 0 ? (
            <Col span={24}>
              <div style={{ textAlign: 'center', padding: '50px' }}>No movies found</div>
            </Col>
          ) : (
            filteredMovies.map(movie => (
              <Col xs={24} sm={12} md={8} lg={6} key={movie.id}>
                <Card
                  hoverable
                  style={{ height: '100%' }}
                  cover={
                    <div style={{ height: 400, overflow: 'hidden', position: 'relative' }}>
                      <img
                        alt={movie.title}
                        src={movie.image}
                        style={{
                          width: '100%',
                          height: '100%',
                          objectFit: 'cover',
                          objectPosition: 'center'
                        }}
                      />
                      <Tag color="blue" style={{ position: 'absolute', top: 10, right: 10 }}>
                        <StarFilled /> {getRating(movie).toFixed(1)}
                      </Tag>
                      <Tag color="green" style={{ position: 'absolute', top: 40, right: 10 }}>
                        <UserOutlined /> {getRatingCount(movie)}
                      </Tag>
                    </div>
                  }
                >
                  <Meta
                    title={
                      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                        <Text strong style={{ fontSize: '16px' }}>{movie.title}</Text>
                        <Text strong style={{ fontSize: '16px', color: '#1890ff' }}>${Number(movie.price).toFixed(2)}</Text>
                      </div>
                    }
                    description={
                      <div>
                        <Space direction="vertical" style={{ width: '100%' }}>
                          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                            <Text type="secondary">{movie.category}</Text>
                            <Text type="secondary">{formatDate(movie.releaseDate)}</Text>
                          </div>
                          <Button
                            type="primary"
                            icon={<ShoppingCartOutlined />}
                            onClick={() => addToCart(movie)}
                            style={{ width: '100%', marginTop: 8 }}
                          >
                            Add to Cart
                          </Button>
                        </Space>
                      </div>
                    }
                  />
                </Card>
              </Col>
            ))
          )}
        </Row>

        <Button
          type="primary"
          shape="circle"
          icon={<ArrowUpOutlined />}
          size="large"
          onClick={scrollToTop}
          style={{
            position: 'fixed',
            bottom: 50,
            right: 50,
            zIndex: 1000,
            boxShadow: '0 2px 8px rgba(0,0,0,0.15)'
          }}
        />

        <Drawer
          title={
            <Row justify="space-between" align="middle" style={{ width: '100%' }}>
              <Col>
                <Text strong style={{ fontSize: '16px' }}>
                  Shopping Cart ({cart.length} {cart.length === 1 ? 'item' : 'items'})
                </Text>
              </Col>
              <Col>
                <Space>
                  <Text strong>TotalPrice: ${getTotalPrice().toFixed(2)}</Text>
                  <Button
                    type="text"
                    danger
                    onClick={clearCart}
                    disabled={cart.length === 0}
                  >
                    Clear Cart
                  </Button>
                </Space>
              </Col>
            </Row>
          }
          placement="right"
          onClose={() => setCartVisible(false)}
          open={cartVisible}
          width={500}
        >
          <List
            itemLayout="horizontal"
            dataSource={cart}
            renderItem={item => (
              <List.Item
                style={{ padding: '16px 0' }}
                actions={[
                  <Button
                    type="text"
                    danger
                    icon={<DeleteOutlined />}
                    onClick={() => removeFromCart(item.id)}
                  />
                ]}
              >
                <List.Item.Meta
                  avatar={
                    <Avatar
                      shape="square"
                      size={60}
                      src={item.image}
                      style={{
                        objectFit: 'cover',
                        borderRadius: '8px',
                        border: '1px solid #f0f0f0'
                      }}
                    />
                  }
                  title={
                    <Space direction="vertical" size={4} style={{ width: '100%' }}>
                      <Text strong>{item.title}</Text>
                      <Text type="secondary">${Number(item.price).toFixed(2)}</Text>
                    </Space>
                  }
                  description={
                    <Space direction="vertical" size={12} style={{ width: '100%', marginTop: 8 }}>
                      <div style={{
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'space-between'
                      }}>
                        <Space size="large">
                          <Button
                            size="small"
                            icon={<MinusOutlined />}
                            onClick={() => updateQuantity(item.id, (item.quantity || 1) - 1)}
                            disabled={(item.quantity || 1) <= 1}
                          />
                          <Text strong>{item.quantity || 1}</Text>
                          <Button
                            size="small"
                            icon={<PlusOutlined />}
                            onClick={() => updateQuantity(item.id, (item.quantity || 1) + 1)}
                          />
                        </Space>
                        <Text strong>
                          ${((item.quantity || 1) * item.price).toFixed(2)}
                        </Text>
                      </div>
                    </Space>
                  }
                />
              </List.Item>
            )}
          />

          {cart.length > 0 && (
            <div style={{
              position: 'sticky',
              bottom: 0,
              marginTop: 24,
              padding: '16px 0',
              background: '#fff',
              borderTop: '1px solid #f0f0f0'
            }}>
              <Button
                type="primary"
                block
                size="large"
                onClick={() => setCheckoutVisible(true)}
              >
                Proceed to Checkout ({cart.length} items) - ${getTotalPrice().toFixed(2)}
              </Button>
            </div>
          )}
        </Drawer>

        {checkoutModal}
      </Content>
    </Layout>
  );
};

export default UserDashboard;
