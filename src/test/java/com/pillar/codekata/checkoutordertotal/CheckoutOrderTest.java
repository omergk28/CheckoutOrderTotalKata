package com.pillar.codekata.checkoutordertotal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class CheckoutOrderTest {

    Item item1;
    Item item2;
    Item item3;
    Item item4;
    CheckoutOrder checkoutOrder;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setup() {
        checkoutOrder = new CheckoutOrder();
        initializeTestItems();
    }

    private void initializeTestItems() {
        item1 = new Item();
        item1.setItemName( "foo" );
        item1.setSoldBy( Item.SOLD_BY.PER_UNIT );
        item1.setUnitPrice( new BigDecimal( "12.12" ) );

        item2 = new Item();
        item2.setItemName( "foo" );
        item2.setUnitPrice( new BigDecimal( "13.13" ) );
        item2.setSoldBy( Item.SOLD_BY.PER_WEIGHT );

        item3 = new Item();
        item3.setItemName( "bar" );
        item3.setUnitPrice( new BigDecimal( "10.00" ) );
        item3.setSoldBy( Item.SOLD_BY.PER_WEIGHT );

        item4 = new Item();
        item4.setItemName( "baz" );
        item4.setUnitPrice( new BigDecimal( "20.00" ) );
        item4.setSoldBy( Item.SOLD_BY.PER_WEIGHT );
    }

    private void addItemsToInventory() {
        checkoutOrder.addItemToInventory( item2 );
        checkoutOrder.addItemToInventory( item3 );
        checkoutOrder.addItemToInventory( item4 );
    }

    @Test
    public void scanItemWithWeightShouldThrowForInvalidWeight() {
        addItemsToInventory();

        thrown.expect( NumberFormatException.class );

        LineItem item = checkoutOrder.scanItemWithWeight( "bar", "invalid" );
    }

    @Test
    public void scanItemWithWeightShouldAddAnItemWithWeightAndUpdateTotalPrice() {
        addItemsToInventory();

        LineItem added = checkoutOrder.scanItemWithWeight( "bar", "0.5" );

        Assert.assertTrue( checkoutOrder.getLineItems().containsKey( added.getLineItemId() ) );

        Assert.assertEquals( new BigDecimal( "5.00" ), checkoutOrder.getOrderTotal() );
    }

    @Test
    public void scanItemShouldAddMultipleItemsToOrderAndUpdateTotalPrice() {
        addItemsToInventory();

        LineItem addedItem1 = checkoutOrder.scanItem( "bar" );
        LineItem addedItem2 = checkoutOrder.scanItem( "baz" );

        Assert.assertTrue( checkoutOrder.getLineItems().containsKey( addedItem1.getLineItemId() ) );
        Assert.assertTrue( checkoutOrder.getLineItems().containsKey( addedItem2.getLineItemId() ) );

        Assert.assertEquals( new BigDecimal( "30.00" ), checkoutOrder.getOrderTotal() );
    }

    @Test
    public void scanItemShouldAddAnItemToOrderAndUpdateTotalPrice() {

        addItemsToInventory();

        LineItem added = checkoutOrder.scanItem( "bar" );

        Assert.assertTrue( checkoutOrder.getLineItems().containsKey( added.getLineItemId() ) );

        Assert.assertEquals( new BigDecimal( "10.00" ), checkoutOrder.getOrderTotal() );
    }

    @Test
    public void updateOrderItemAmountShouldUpdateOrderItemAmount() {
        checkoutOrder.addItemToInventory( item1 );

        checkoutOrder.updateOrderItemAmount( item1, new BigDecimal( "1" ) );

        Assert.assertEquals( new BigDecimal( "1" ), checkoutOrder.getOrderItemAmount()
                .get( item1.getItemName() ) );

        checkoutOrder.updateOrderItemAmount( item1, new BigDecimal( "1" ) );

        Assert.assertEquals( new BigDecimal( "2" ), checkoutOrder.getOrderItemAmount()
                .get( item1.getItemName() ) );

        checkoutOrder.updateOrderItemAmount( item1, new BigDecimal( "2" ).negate() );

        Assert.assertEquals( new BigDecimal( "0" ), checkoutOrder.getOrderItemAmount()
                .get( item1.getItemName() ) );
    }

    @Test
    public void addItemToOrderShouldCreateALineItemAndAddItToLineItems() {
        checkoutOrder.addItemToInventory( item1 );

        LineItem addedLineItem = checkoutOrder.addItemToOrder( item1, new BigDecimal( "1" ) );

        Assert.assertTrue( checkoutOrder.getLineItems().containsKey( addedLineItem.getLineItemId() ) );
        Assert.assertEquals( new BigDecimal( "1" ), checkoutOrder.getOrderItemAmount()
                .get( item1.getItemName() ) );
    }

    @Test
    public void addItemToInventoryShouldUpdatePreviouslyDefinedItem() {

        checkoutOrder.addItemToInventory( item1 );

        Assert.assertTrue( checkoutOrder.getInventory().containsKey( "foo" ) );
        Assert.assertEquals( "foo", checkoutOrder.getInventory().get( "foo" ).getItemName() );
        Assert.assertEquals( Item.SOLD_BY.PER_UNIT, checkoutOrder.getInventory().get( "foo" ).getSoldBy() );
        Assert.assertEquals( new BigDecimal( "12.12" ), checkoutOrder.getInventory().get( "foo" ).getUnitPrice() );

        checkoutOrder.addItemToInventory( item2 );

        Assert.assertEquals( "foo", checkoutOrder.getInventory().get( "foo" ).getItemName() );
        Assert.assertEquals( Item.SOLD_BY.PER_WEIGHT, checkoutOrder.getInventory().get( "foo" ).getSoldBy() );
        Assert.assertEquals( new BigDecimal( "13.13" ), checkoutOrder.getInventory().get( "foo" ).getUnitPrice() );
    }

    @Test
    public void addItemToInventoryShouldAddItemToInventory() throws Exception {

        checkoutOrder.addItemToInventory( item1 );

        Assert.assertTrue( checkoutOrder.getInventory().containsKey( "foo" ) );
        Assert.assertEquals( "foo", checkoutOrder.getInventory().get( "foo" ).getItemName() );
        Assert.assertEquals( Item.SOLD_BY.PER_UNIT, checkoutOrder.getInventory().get( "foo" ).getSoldBy() );
        Assert.assertEquals( new BigDecimal( "12.12" ), checkoutOrder.getInventory().get( "foo" ).getUnitPrice() );

    }

}
