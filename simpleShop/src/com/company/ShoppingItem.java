package com.company;

//make class for item
public class ShoppingItem {
    public String itemName;
    public double itemCost;
    public int quantity;
    public String description;


    //constructor for item
    public ShoppingItem(String itemName, double itemCost, int quantity, String description) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.quantity = quantity;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemCost() {
        return itemCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public boolean equals(Object other) {
        if (this == other) return true;

        if (!(other instanceof ShoppingItem))
            return false;

        ShoppingItem otherItem =
                (ShoppingItem) other;

        return (itemName.equals(otherItem.itemName)) &&
                (itemCost == otherItem.itemCost);
    }
}
