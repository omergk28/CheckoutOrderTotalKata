package com.pillar.codekata.checkoutordertotal;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

public class CheckoutOrder {

    private HashMap<String, Item> inventory = new HashMap<String, Item>();
    private HashMap<UUID, LineItem> lineItems = new HashMap<UUID, LineItem>();
    private HashMap<String, BigDecimal> orderItemAmount = new HashMap<String, BigDecimal>();
    private BigDecimal orderTotal = new BigDecimal( "0.00" );

    public LineItem scanItem( String itemName ) {
        LineItem lineItem = addItemToOrder( inventory.get( itemName ), new BigDecimal( "1" ) );
        orderTotal = calculateOrderTotal();
        return lineItem;
    }

    private BigDecimal calculateOrderTotal() {
        return new BigDecimal( "10.00" );
    }

    public void addItemToInventory( Item item ) {
        inventory.put( item.getItemName(), item );
    }

    public LineItem addItemToOrder( Item item, BigDecimal amount ) {
        LineItem lineItem = new LineItem( item, amount );
        lineItems.put( lineItem.getLineItemId(), lineItem );

        updateOrderItemAmount( item, amount );

        return lineItem;
    }

    public void updateOrderItemAmount( Item item, BigDecimal amount ) {
        BigDecimal itemAmount = amount;
        if ( orderItemAmount.containsKey( item.getItemName() ) ) {
            BigDecimal currentTotal = orderItemAmount.get( item.getItemName() );
            itemAmount = itemAmount.add( currentTotal );
        }
        orderItemAmount.put( item.getItemName(), itemAmount );
    }

    public HashMap<UUID, LineItem> getLineItems() {
        return lineItems;
    }

    public HashMap<String, BigDecimal> getOrderItemAmount() {
        return orderItemAmount;
    }

    public HashMap<String, Item> getInventory() {
        return inventory;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }
}
