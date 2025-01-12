import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  cartItems: [],
  totalPrice: 0,
};

const cartSlice = createSlice({
  name: 'cart',
  initialState,
  reducers: {
    addToCart: (state, action) => {
      const item = action.payload;
      const existingItem = state.cartItems.find((product) => product.id === item.id);
      if (existingItem) {
        
        existingItem.quantity += 1;
        state.totalPrice += item.price;
      } else {

        state.cartItems.push({ ...item, quantity: 1 });
        state.totalPrice += item.price;
      }
    },
    removeFromCart: (state, action) => {
      const itemId = action.payload;
      const item = state.cartItems.find((product) => product.id === itemId);
      if (item && item.quantity > 1) {
        item.quantity -= 1;
        state.totalPrice -= item.price;
      } else {
        state.cartItems = state.cartItems.filter((product) => product.id !== itemId);
        state.totalPrice -= item.price;
      }
    },
        removeAllFromCart: (state, action) => {
      const itemId = action.payload;
      const item = state.cartItems.find((product) => product.id === itemId);
      if (item) {
        state.cartItems = state.cartItems.filter((product) => product.id !== itemId);
        state.totalPrice -= item.price * item.quantity;
      }
    },
    clearCart(state) {
      state.cartItems = []; 
    },
  },  
});

export const { addToCart, removeFromCart, removeAllFromCart,clearCart } = cartSlice.actions;
export default cartSlice.reducer;
