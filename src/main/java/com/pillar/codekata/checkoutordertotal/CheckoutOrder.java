package com.pillar.codekata.checkoutordertotal;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

public class CheckoutOrder {

    private HashMap<String, Item> inventory = new HashMap<String, Item>();
    private HashMap<UUID, LineItem> orderItems = new HashMap<UUID, LineItem>();
    private HashMap<String, BigDecimal> orderItemTotalAmount = new HashMap<String, BigDecimal>();

    public void addItemToInventory( Item item ) {
        inventory.put( item.getItemName(), item );
    }

    public LineItem addItemToOrder( Item item, BigDecimal amount ) {
        LineItem newItem = new LineItem( item, amount );
        orderItems.put( newItem.getLineItemId(), newItem );

        BigDecimal totalItemAmount = amount;
        if ( orderItemTotalAmount.containsKey( item.getItemName() ) ) {
            BigDecimal currentItemAmount = orderItemTotalAmount.get( item.getItemName() );
            totalItemAmount.add( currentItemAmount );
        }
        orderItemTotalAmount.put( item.getItemName(), totalItemAmount );

        return newItem;
    }

    public HashMap<UUID, LineItem> getOrderItems() {
        return orderItems;
    }

    public HashMap<String, BigDecimal> getOrderItemTotalAmount() {
        return orderItemTotalAmount;
    }


    public HashMap<String, Item> getInventory() {
        return inventory;
    }

    public void setInventory( HashMap<String, Item> inventory ) {
        this.inventory = inventory;
    }
}
