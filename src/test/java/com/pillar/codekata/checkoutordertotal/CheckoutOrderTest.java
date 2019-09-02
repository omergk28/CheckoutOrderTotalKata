package com.pillar.codekata.checkoutordertotal;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CheckoutOrderTest {


    @Test
    public void addItemToOrderShouldCreateALineItemAndAddItToOrderItemsAndUpdateTheItemOrderTotalAmount() {
        CheckoutOrder checkoutOrder = new CheckoutOrder();

        Item testItem = new Item();

        testItem.setItemName( "foo" );
        testItem.setSoldBy( Item.SOLD_BY.PER_UNIT );
        testItem.setUnitPrice( new BigDecimal( "12.12" ) );

        checkoutOrder.addItemToInventory( testItem );

        LineItem addedLineItem = checkoutOrder.addItemToOrder( testItem, new BigDecimal( "1" ) );

        Assert.assertTrue( checkoutOrder.getOrderItems().containsKey( addedLineItem.getLineItemId() ) );
        Assert.assertEquals( new BigDecimal( "1" ), checkoutOrder.getOrderItemTotalAmount()
                .get( testItem.getItemName() ) );
    }

    @Test
    public void addItemToInventoryShouldUpdatePreviouslyDefinedItem() {
        CheckoutOrder checkoutOrder = new CheckoutOrder();

        Item testItem = new Item();

        testItem.setItemName( "foo" );
        testItem.setSoldBy( Item.SOLD_BY.PER_UNIT );
        testItem.setUnitPrice( new BigDecimal( "12.12" ) );

        checkoutOrder.addItemToInventory( testItem );

        Assert.assertTrue( checkoutOrder.getInventory().containsKey( "foo" ) );
        Assert.assertEquals( "foo", checkoutOrder.getInventory().get( "foo" ).getItemName() );
        Assert.assertEquals( Item.SOLD_BY.PER_UNIT, checkoutOrder.getInventory().get( "foo" ).getSoldBy() );
        Assert.assertEquals( new BigDecimal( "12.12" ), checkoutOrder.getInventory().get( "foo" ).getUnitPrice() );

        Item replacementItem = new Item();

        replacementItem.setItemName( "foo" );
        replacementItem.setUnitPrice( new BigDecimal( "13.13" ) );
        replacementItem.setSoldBy( Item.SOLD_BY.PER_WEIGHT );

        checkoutOrder.addItemToInventory( replacementItem );

        Assert.assertEquals( "foo", checkoutOrder.getInventory().get( "foo" ).getItemName() );
        Assert.assertEquals( Item.SOLD_BY.PER_WEIGHT, checkoutOrder.getInventory().get( "foo" ).getSoldBy() );
        Assert.assertEquals( new BigDecimal( "13.13" ), checkoutOrder.getInventory().get( "foo" ).getUnitPrice() );
    }

    @Test
    public void addItemToInventoryShouldAddItemToInventory() throws Exception {
        CheckoutOrder checkoutOrder = new CheckoutOrder();

        Item testItem = new Item();

        testItem.setItemName( "foo" );
        testItem.setSoldBy( Item.SOLD_BY.PER_UNIT );
        testItem.setUnitPrice( new BigDecimal( "12.12" ) );

        checkoutOrder.addItemToInventory( testItem );

        Assert.assertTrue( checkoutOrder.getInventory().containsKey( "foo" ) );
        Assert.assertEquals( "foo", checkoutOrder.getInventory().get( "foo" ).getItemName() );
        Assert.assertEquals( Item.SOLD_BY.PER_UNIT, checkoutOrder.getInventory().get( "foo" ).getSoldBy() );
        Assert.assertEquals( new BigDecimal( "12.12" ), checkoutOrder.getInventory().get( "foo" ).getUnitPrice() );

    }

}
