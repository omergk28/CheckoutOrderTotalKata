package com.pillar.codekata.checkoutordertotal;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

public class CheckoutOrder {

    private HashMap<String, Item> inventory = new HashMap<String, Item>();
    private HashMap<UUID, LineItem> lineItems = new HashMap<UUID, LineItem>();
    private HashMap<String, BigDecimal> orderItemTotalAmount = new HashMap<String, BigDecimal>();

    public void addItemToInventory( Item item ) {
        inventory.put( item.getItemName(), item );
    }

    public LineItem addItemToOrder( Item item, BigDecimal amount ) {
        LineItem lineItem = new LineItem( item, amount );
        lineItems.put( lineItem.getLineItemId(), lineItem );

        updateOrderItemTotalAmount( item, amount );

        return lineItem;
    }

    public void updateOrderItemTotalAmount( Item item, BigDecimal amount ) {
        BigDecimal totalItemAmount = amount;
        if ( orderItemTotalAmount.containsKey( item.getItemName() ) ) {
            BigDecimal currentTotal = orderItemTotalAmount.get( item.getItemName() );
            totalItemAmount = totalItemAmount.add( currentTotal );
        }
        orderItemTotalAmount.put( item.getItemName(), totalItemAmount );
    }

    public HashMap<UUID, LineItem> getLineItems() {
        return lineItems;
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
