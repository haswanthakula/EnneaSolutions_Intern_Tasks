import React, { createContext, useContext, useState, useEffect } from 'react';
import axios from 'axios';
import { message } from 'antd';

const ProductContext = createContext();

export const ProductProvider = ({ children }) => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [orders, setOrders] = useState([]);
  const [cart, setCart] = useState(() => {
    const savedCart = localStorage.getItem('cart');
    return savedCart ? JSON.parse(savedCart) : [];
  });

  useEffect(() => {
    fetchProducts();
    fetchOrders();
  }, []);

  useEffect(() => {
    localStorage.setItem('cart', JSON.stringify(cart));
  }, [cart]);

  const fetchProducts = async () => {
    setLoading(true);
    try {
      const response = await axios.get('http://localhost:4000/movies');
      setProducts(response.data);
      setError(null);
    } catch (err) {
      setError('Failed to fetch products');
      console.error('Error fetching products:', err);
    } finally {
      setLoading(false);
    }
  };

  const fetchOrders = async () => {
    try {
      const response = await axios.get('http://localhost:4000/orders');
      setOrders(response.data);
    } catch (err) {
      console.error('Error fetching orders:', err);
      message.error('Failed to fetch orders');
    }
  };

  const addProduct = async (product) => {
    try {
      const response = await axios.post('http://localhost:4000/movies', product);
      setProducts([...products, response.data]);
      return true;
    } catch (error) {
      console.error('Error adding product:', error);
      message.error('Failed to add product');
      return false;
    }
  };

  const deleteProduct = async (id) => {
    try {
      await axios.delete(`http://localhost:4000/movies/${id}`);
      setProducts(products.filter(p => p.id !== id));
      return true;
    } catch (error) {
      console.error('Error deleting product:', error);
      message.error('Failed to delete product');
      return false;
    }
  };

  const updateProduct = async (id, updatedProduct) => {
    try {
      await axios.put(`http://localhost:4000/movies/${id}`, updatedProduct);
      setProducts(products.map(p => p.id === id ? { ...p, ...updatedProduct } : p));
      return true;
    } catch (error) {
      console.error('Error updating product:', error);
      message.error('Failed to update product');
      return false;
    }
  };

  const placeOrder = async (orderData) => {
    try {
      const order = {
        ...orderData,
        items: cart,
        date: new Date().toISOString()
      };
      const response = await axios.post('http://localhost:4000/orders', order);
      setOrders(prevOrders => [...prevOrders, response.data]);
      setCart([]);
      message.success('Order placed successfully! Thank you for your purchase.');
      return true;
    } catch (error) {
      console.error('Error placing order:', error);
      message.error('Failed to place order. Please try again.');
      return false;
    }
  };

  const addToCart = (product) => {
    const existingItem = cart.find(item => item.id === product.id);
    if (existingItem) {
      updateQuantity(product.id, existingItem.quantity + 1);
    } else {
      setCart([...cart, { ...product, quantity: 1 }]);
    }
  };

  const removeFromCart = (id) => setCart(cart.filter(item => item.id !== id));

  const updateQuantity = (id, newQuantity) => {
    if (newQuantity < 1) return;
    setCart(cart.map(item => item.id === id ? { ...item, quantity: newQuantity } : item));
  };

  const clearCart = () => setCart([]);

  const getTotalPrice = () => cart.reduce((total, item) => total + (item.price * item.quantity), 0);

  return (
    <ProductContext.Provider value={{
      products, orders, loading, error, cart,
      addProduct, deleteProduct, updateProduct,
      fetchProducts, fetchOrders, setOrders,
      addToCart, removeFromCart, updateQuantity,
      clearCart, placeOrder, getTotalPrice
    }}>
      {children}
    </ProductContext.Provider>
  );
};

export const useProduct = () => {
  const context = useContext(ProductContext);
  if (!context) throw new Error('useProduct must be used within a ProductProvider');
  return context;
};

export default ProductProvider;
