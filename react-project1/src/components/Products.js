import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { addToCart, removeFromCart } from '../redux/cartSlice';
import styled from 'styled-components';

const ProductContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
  margin: 20px;
`;

const ProductCard = styled.div`
  border: 1px solid #ddd;
  border-radius: 10px;
  padding: 15px;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
`;

const Image = styled.img`
  width: 100%;
  height: 200px;
  object-fit: contain;
  margin-bottom: 10px;
`;

const Title = styled.h3`
  font-size: 1.1rem;
  margin-bottom: 10px;
`;

const Price = styled.p`
  font-size: 1.2rem;
  font-weight: bold;
  color: #28a745;
`;

const Rating = styled.div`
  font-size: 1rem;
  color: #f4b400;
  margin-bottom: 10px;
`;

const Button = styled.button`
  background-color: ${(props) => (props.variant === 'add' ? '#007bff' : '#dc3545')};
  color: white;
  border: none;
  padding: 10px 15px;
  cursor: pointer;
  font-size: 1rem;
  border-radius: 5px;
  &:hover {
    background-color: ${(props) => (props.variant === 'add' ? '#0056b3' : '#c82333')};
  }
`;

const QuantityButton = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f0f0f0;
  border: 1px solid #ddd;
  border-radius: 20px;
  padding: 0 10px;
  font-size: 1rem;
  cursor: pointer;
  width: 120px;
  height: 40px;
  margin-left: 10px;
`;

const CountButton = styled.button`
  background-color: #6c757d;
  font-size: 1.5rem;
  padding: 5px 10px;
  border-radius: 50%;
  width: 30px;
  height: 30px;
  display: inline-block;
  cursor: pointer;
  margin: 0 5px;
`;

const CartCount = styled.span`
  font-size: 1rem;
  color: #ff6347;
  padding: 0 10px;
  background-color: #fff;
  border-radius: 50%;
  width: 30px;
  height: 30px;
  text-align: center;
  line-height: 30px;
`;


const LoadingMessage = styled.div`
  text-align: center;
  font-size: 1.5rem;
  color: #007bff;
  margin-top: 50px;
`;

const Products = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const cartItems = useSelector((state) => state.cart.cartItems);
  const dispatch = useDispatch();

  useEffect(() => {
    fetch('https://fakestoreapi.com/products')
      .then((res) => res.json())
      .then((data) => {
        setProducts(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error('Error fetching products:', err);
        setLoading(false);
      });
  }, []);

  const handleAddToCart = (product) => {
    dispatch(
      addToCart({
        id: product.id,
        title: product.title,
        price: product.price,
        image: product.image,
      })
    );
  };

  const handleRemoveFromCart = (productId) => {
    const item = cartItems.find((item) => item.id === productId);

    if (item && item.quantity > 1) {
      dispatch(removeFromCart(productId));
    } else if (item && item.quantity === 1) {
      dispatch(removeFromCart(productId));
    }
  };

  const getCartItemCount = (productId) => {
    const item = cartItems.find((item) => item.id === productId);
    return item ? item.quantity : 0;
  };

  return (
    <div>
      {loading ? (
        <LoadingMessage>Loading products...</LoadingMessage>
      ) : (
        <ProductContainer>
          {products.map((product) => (
            <ProductCard key={product.id}>
              <Image src={product.image} alt={product.title} />
              <Title>{product.title}</Title>
              <Price>${product.price.toFixed(2)}</Price>
              <Rating>Rating: {product.rating.rate}</Rating>
              <div>
                <Button onClick={() => handleAddToCart(product)} variant="add">
                  Add to Cart
                </Button>
                <QuantityButton>
                  <CountButton onClick={() => handleRemoveFromCart(product.id)}>
                    -
                  </CountButton>
                  <CartCount>
                    {getCartItemCount(product.id) > 0
                      ? getCartItemCount(product.id)
                      : 0}
                  </CartCount>
                  <CountButton onClick={() => handleAddToCart(product)}>
                    +
                  </CountButton>
                </QuantityButton>
              </div>
            </ProductCard>
          ))}
        </ProductContainer>
      )}
    </div>
  );
};

export default Products;
