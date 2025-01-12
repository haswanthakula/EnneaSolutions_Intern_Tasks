import { useContext } from "react";
import Modal from "./Modal";
import CartContext from "../store/CartContext";
import Input from "./Input";
import Button from "./Button";
import UserProgressContext from "../store/UserProgressContext";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function Checkout() {
  const cartCtx = useContext(CartContext);
  const userProgressCtx = useContext(UserProgressContext);

  const cartTotal = cartCtx.items.reduce(
    (totalPrice, item) => totalPrice + item.price * item.quantity,
    0
  );

  function handleClose() {
    userProgressCtx.hideCheckout();
  }

  async function handleSubmit(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const customerData = Object.fromEntries(formData.entries());

    const orderData = {
      customer: customerData,
      items: cartCtx.items,
      totalAmount: cartTotal,
      date: new Date().toISOString()
    };

    try {
      const response = await fetch("http://localhost:5000/orders", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(orderData)
      });

      if (response.ok) {
        const responseData = await response.json();
        console.log("Order submitted successfully:", responseData);

        cartCtx.clearCart();

        toast.success("Order submitted successfully!", {
          position: "top-right",
          autoClose: 3000
        });

        setTimeout(() => {
          window.location.reload();
        }, 3000);
      } else {
        throw new Error("Failed to submit order.");
      }
    } catch (error) {
      console.error("Error submitting order:", error);

      toast.error("Failed to submit order. Please try again.", {
        position: "top-right",
        autoClose: 3000
      });
    }
  }

  return (
    <>
      <Modal open={userProgressCtx.progress === "checkout"}>
        <form onSubmit={handleSubmit}>
          <h2>Checkout</h2>
          <p>Total Amount: {cartTotal}</p>

          <Input label="Full Name" type="text" id="name" name="name" />
          <Input label="E-Mail Address" type="email" id="email" name="email" />
          <Input label="Street" type="text" id="street" name="street" />
          <div className="control-row">
            <Input
              label="Postal Code"
              type="text"
              id="postal-code"
              name="postal-code"
            />
            <Input label="City" type="text" id="city" name="city" />
          </div>

          <p className="modal-actions">
            <Button type="button" textOnly onClick={handleClose}>
              Close
            </Button>
            <Button>Submit Order</Button>
          </p>
        </form>
      </Modal>

      <ToastContainer />
    </>
  );
}
