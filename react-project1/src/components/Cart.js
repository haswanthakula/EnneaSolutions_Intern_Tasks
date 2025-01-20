import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { removeFromCart } from '../redux/cartSlice';
import { removeAllFromCart } from '../redux/cartSlice';
import {clearCart } from '../redux/cartSlice'
import styled from 'styled-components';

const CartContainer = styled.div`
  padding: 20px;
  display: flex;
  flex-direction: column;
`;

const CartItem = styled.div`
  display: flex;
  justify-content: space-between;
  border-bottom: 1px solid #ddd;
  padding: 10px 0;
`;

const ProductInfo = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
`;

const Image = styled.img`
  width: 50px;
  height: 50px;
  object-fit: contain;
  margin-right: 10px;
`;

const Title = styled.h3`
  font-size: 1rem;
  margin: 0;
`;

const Price = styled.p`
  font-size: 1.1rem;
  font-weight: bold;
  color: #28a745;
`;

const Quantity = styled.p`
  font-size: 1rem;
  color: #333;
  margin: 0;
`;

const RemoveButton = styled.button`
  background-color: #dc3545;
  color: white;
  padding: 5px 10px;
  border: none;
  cursor: pointer;
  font-size: 0.9rem;
  border-radius: 5px;
  &:hover {
    background-color: #c82333;
  }
`;


const ClearCartButton = styled.button`
  background-color: #dc3545;
  color: white;
  padding: 5px 10px;
  border: none;
  cursor: pointer;
  font-size: 0.9rem;
  border-radius: 5px;
  &:hover {
    background-color: #c82333;
  }
`;

const TotalPriceContainer = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 20px 0;
  font-size: 1.5rem;
  font-weight: bold;
  border-top: 2px solid #ddd;
  margin-top: 20px;
`;

const TotalItemsContainer = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 15px 0;
  font-size: 1.5rem;
  font-weight: bold;
  border-top: 2px solid #ddd;
  margin-top: 10px;
`;

const SummarySection = styled.div`
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
`;

const Cart = () => {
  const cartItems = useSelector((state) => state.cart.cartItems);
  const dispatch = useDispatch();


  const totalPrice = cartItems.reduce((total, item) => total + item.price * item.quantity, 0);


  const totalItems = cartItems.reduce((total, item) => total + item.quantity, 0);

  const handleRemoveFromCart = (productId) => {
    dispatch(removeFromCart(productId));
  };


  const handleRemoveAllFromCart = (productId) => {
    dispatch(removeAllFromCart(productId));
  };

  


  const handleClearCart = () => {
    dispatch(clearCart());
  };
  

  return (
    <CartContainer>
      <h2>Your Cart</h2>
      {cartItems.length === 0 ? (
        <p>Your cart is empty!</p>
      ) : (
        <>
          {cartItems.map((item) => (
            <CartItem key={item.id}>
              <ProductInfo>
                <Image src={item.image} alt={item.title} />
                <div>
                  <Title>{item.title}</Title>
                  <Price>${item.price.toFixed(2)}</Price>
                  <Quantity>Quantity: {item.quantity}</Quantity>
                </div>
              </ProductInfo>
              <RemoveButton onClick={() => handleRemoveAllFromCart(item.id)}>
                Remove
              </RemoveButton>
            </CartItem>
          ))}
  
          <SummarySection>
            <TotalItemsContainer>
              <span>Total Items:</span>
              <span>{totalItems}</span>
            </TotalItemsContainer>
            <TotalPriceContainer>
              <span>Total Price:</span>
              <span>${totalPrice.toFixed(2)}</span>
            </TotalPriceContainer>
          </SummarySection>
  
         
          <ClearCartButton onClick={handleClearCart}>Clear Cart</ClearCartButton>
        </>
      )}
    </CartContainer>
  );
  
};

export default Cart;