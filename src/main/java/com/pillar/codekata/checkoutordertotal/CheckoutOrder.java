package com.pillar.codekata.checkoutordertotal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.UUID;

public class CheckoutOrder {

    private HashMap<String, Item> inventory = new HashMap<String, Item>();
    private HashMap<UUID, LineItem> lineItems = new HashMap<UUID, LineItem>();
    private HashMap<String, BigDecimal> orderItemAmount = new HashMap<String, BigDecimal>();
    private BigDecimal orderTotal = new BigDecimal( "0.00" );
    private HashMap<String, Special> specials = new HashMap<String, Special>();

    public LineItem scanItemWithWeight( String itemName, String weight ) {
        BigDecimal itemWeight = new BigDecimal( weight );
        LineItem lineItem = addItemToOrder( inventory.get( itemName ), itemWeight );
        orderTotal = calculateOrderTotal();

        return lineItem;
    }

    private BigDecimal calculateOrderTotal() {
        BigDecimal orderTotal = new BigDecimal( "0.00" );

        for ( String itemId : orderItemAmount.keySet() ) {
            BigDecimal itemUnitPrice = inventory.get( itemId ).getUnitPrice();
            BigDecimal itemTotalAmount = orderItemAmount.get( itemId );
            BigDecimal itemTotalPrice = itemUnitPrice.multiply( itemTotalAmount );
            orderTotal = orderTotal.add( itemTotalPrice );
        }

        return orderTotal;
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

    public LineItem scanItem( String itemName ) {
        LineItem lineItem = addItemToOrder( inventory.get( itemName ), new BigDecimal( "1" ) );
        orderTotal = calculateOrderTotal();
        return lineItem;
    }

    public void addBuyNGetMXPercentOffSpecialWithLimit( String itemName, int buyN, int getM, int XPercentOff, int limit ) {
        Special special = new Special( buyN, getM, XPercentOff, limit );
        specials.put( itemName, special );
    }

    public void addBuyNGetMXPercentOffSpecial( String itemName, int buyN, int getM, int XPercentOff ) {
        Special special = new Special( buyN, getM, XPercentOff );
        specials.put( itemName, special );
    }

    public void addItemToInventory( Item item ) {
        inventory.put( item.getItemName(), item );
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
        return orderTotal.setScale( 2, RoundingMode.HALF_EVEN );
    }

    public HashMap<String, Special> getSpecials() {
        return specials;
    }
}
