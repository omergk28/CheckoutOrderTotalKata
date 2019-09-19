package com.pillar.codekata.checkoutordertotal;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class CheckoutOrderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    Item foo;
    Item fooUpdate;
    Item bar;
    Item baz;
    Item markedDown;
    CheckoutOrder checkoutOrder;

    @Before
    public void setup() {
        checkoutOrder = new CheckoutOrder();
        initializeTestItems();
    }

    private void initializeTestItems() {
        foo = new Item();
        foo.setItemName( "foo" );
        foo.setSoldBy( Item.SOLD_BY.PER_UNIT );
        foo.setUnitPrice( new BigDecimal( "12.12" ) );

        fooUpdate = new Item();
        fooUpdate.setItemName( "foo" );
        fooUpdate.setUnitPrice( new BigDecimal( "13.13" ) );
        fooUpdate.setSoldBy( Item.SOLD_BY.PER_WEIGHT );

        bar = new Item();
        bar.setItemName( "bar" );
        bar.setUnitPrice( new BigDecimal( "10.00" ) );
        bar.setSoldBy( Item.SOLD_BY.PER_WEIGHT );

        baz = new Item();
        baz.setItemName( "baz" );
        baz.setUnitPrice( new BigDecimal( "20.00" ) );
        baz.setSoldBy( Item.SOLD_BY.PER_WEIGHT );

        markedDown = new Item();
        markedDown.setItemName( "markedDown" );
        markedDown.setUnitPrice( new BigDecimal( "20.00" ) );
        markedDown.setSoldBy( Item.SOLD_BY.PER_WEIGHT );
        markedDown.setMarkdown( new BigDecimal( "5.00" ) );
    }

    @Test
    public void checkInventoryForItemShouldThrowForInvalidItem() throws Exception {
        thrown.expect( Exception.class );
        thrown.expectMessage( CoreMatchers.startsWith( "Item [test] is not in the inventory!" ) );

        checkoutOrder.checkInventoryForItem( "test" );
    }

    @Test
    public void addBuyNForXDollarsSpecialShouldAddSpecial() throws Exception {
        addItemsToInventory();
        checkoutOrder.addBuyNForXDollarsSpecial( "foo", 3, new BigDecimal( "5.00" ) );

        Assert.assertTrue( checkoutOrder.getSpecials().containsKey( "foo" ) );
    }

    private void addItemsToInventory() {
        checkoutOrder.addItemToInventory( foo );
        checkoutOrder.addItemToInventory( bar );
        checkoutOrder.addItemToInventory( baz );
        checkoutOrder.addItemToInventory( markedDown );
    }

    @Test
    public void addBuyNForXDollarsSpecialShouldAThrowForInvalidItem() throws Exception {

        thrown.expectMessage( CoreMatchers.startsWith( "Item [test] is not in the inventory!" ) );
        thrown.expect( Exception.class );

        checkoutOrder.addBuyNForXDollarsSpecial( "test", 3, new BigDecimal( "5.00" ) );

    }

    @Test
    public void addBuyNGetMXPercentOffSpecialWithLimitShouldAddSpecial() throws Exception {
        addItemsToInventory();

        checkoutOrder.addBuyNGetMXPercentOffSpecialWithLimit( "baz", 3, 1, 50, 6 );

        Assert.assertTrue( checkoutOrder.getSpecials().containsKey( "baz" ) );
    }

    @Test
    public void addBuyNGetMXPercentOffSpecialWithLimitShouldThrowForInvalidItem() throws Exception {
        thrown.expectMessage( CoreMatchers.startsWith( "Item [test] is not in the inventory!" ) );
        thrown.expect( Exception.class );

        checkoutOrder.addBuyNGetMXPercentOffSpecialWithLimit( "test", 3, 1, 50, 6 );
    }

    @Test
    public void addBuyNGetMXPercentOffSpecialShouldAddSpecial() throws Exception {
        addItemsToInventory();

        checkoutOrder.addBuyNGetMXPercentOffSpecial( "bar", 3, 1, 50 );

        Assert.assertTrue( checkoutOrder.getSpecials().containsKey( "bar" ) );
    }

    @Test
    public void addBuyNGetMXPercentOffSpecialShouldThrowForInvalidItem() throws Exception {

        thrown.expectMessage( CoreMatchers.startsWith( "Item [test] is not in the inventory!" ) );
        thrown.expect( Exception.class );

        checkoutOrder.addBuyNGetMXPercentOffSpecial( "test", 3, 1, 50 );
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
        checkoutOrder.addItemToInventory( foo );

        checkoutOrder.updateOrderItemAmount( foo, new BigDecimal( "1" ) );

        Assert.assertEquals( new BigDecimal( "1" ), checkoutOrder.getOrderItemAmount()
                .get( foo.getItemName() ) );

        checkoutOrder.updateOrderItemAmount( foo, new BigDecimal( "1" ) );

        Assert.assertEquals( new BigDecimal( "2" ), checkoutOrder.getOrderItemAmount()
                .get( foo.getItemName() ) );

        checkoutOrder.updateOrderItemAmount( foo, new BigDecimal( "2" ).negate() );

        Assert.assertEquals( new BigDecimal( "0" ), checkoutOrder.getOrderItemAmount()
                .get( foo.getItemName() ) );
    }

    @Test
    public void addItemToOrderShouldCreateALineItemAndAddItToLineItems() {
        checkoutOrder.addItemToInventory( foo );

        LineItem addedLineItem = checkoutOrder.addItemToOrder( foo, new BigDecimal( "1" ) );

        Assert.assertTrue( checkoutOrder.getLineItems().containsKey( addedLineItem.getLineItemId() ) );
        Assert.assertEquals( new BigDecimal( "1" ), checkoutOrder.getOrderItemAmount()
                .get( foo.getItemName() ) );
    }

    @Test
    public void addItemToInventoryShouldUpdatePreviouslyDefinedItem() {

        checkoutOrder.addItemToInventory( foo );

        Assert.assertTrue( checkoutOrder.getInventory().containsKey( "foo" ) );
        Assert.assertEquals( "foo", checkoutOrder.getInventory().get( "foo" ).getItemName() );
        Assert.assertEquals( Item.SOLD_BY.PER_UNIT, checkoutOrder.getInventory().get( "foo" ).getSoldBy() );
        Assert.assertEquals( new BigDecimal( "12.12" ), checkoutOrder.getInventory().get( "foo" ).getUnitPrice() );

        checkoutOrder.addItemToInventory( fooUpdate );

        Assert.assertEquals( "foo", checkoutOrder.getInventory().get( "foo" ).getItemName() );
        Assert.assertEquals( Item.SOLD_BY.PER_WEIGHT, checkoutOrder.getInventory().get( "foo" ).getSoldBy() );
        Assert.assertEquals( new BigDecimal( "13.13" ), checkoutOrder.getInventory().get( "foo" ).getUnitPrice() );
    }

    @Test
    public void addItemToInventoryShouldAddItemToInventory() throws Exception {

        checkoutOrder.addItemToInventory( foo );

        Assert.assertTrue( checkoutOrder.getInventory().containsKey( "foo" ) );
        Assert.assertEquals( "foo", checkoutOrder.getInventory().get( "foo" ).getItemName() );
        Assert.assertEquals( Item.SOLD_BY.PER_UNIT, checkoutOrder.getInventory().get( "foo" ).getSoldBy() );
        Assert.assertEquals( new BigDecimal( "12.12" ), checkoutOrder.getInventory().get( "foo" ).getUnitPrice() );

    }

}
