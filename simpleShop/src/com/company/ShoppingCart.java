package com.company;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<ShoppingItem> cartItems = new ArrayList();
    private String address;
    private String paymentType;

    //add item to cart
    public boolean addToCart(ShoppingItem item) {
        if (item.getQuantity() >= 1) {
            this.cartItems.add(item);
            item.setQuantity(item.getQuantity() - 1);

            return true;
        } else {
            return false;
        }
    }

    //delete from cart
    public boolean deleteFromCart(ShoppingItem item) {
        if (cartItems.contains(item)) {
            this.cartItems.remove(item);
            item.setQuantity(item.getQuantity() + 1);

            return true;
        } else {
            return false;
        }
    }

    //get checkout address
    public String getAddress() {
        return address;
    }

    //set checkout address
    public void setAddress(String address) {
        this.address = address;
    }

    //get payment type
    public String getPaymentType() {
        return paymentType;
    }

    //chooses payment type
    public boolean setPaymentType(String paymentType) {
        if (paymentType.equals("Debit") || paymentType.equals("Credit") || paymentType.equals("PayPal")) {
            this.paymentType = paymentType;

            return true;
        } else {
            return false;
        }
    }

    //show cart
    public ArrayList<ShoppingItem> getCartItems() {
        return cartItems;
    }

}