package com.pillar.codekata.checkoutordertotal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CheckoutOrderTest {

    Item item1;
    Item item2;
    Item item3;


    @Before
    public void setup() {
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
    }

    @Test
    public void addItemToOrderShouldCreateALineItemAndAddItToOrderItemsAndUpdateTheItemOrderTotalAmount() {
        CheckoutOrder checkoutOrder = new CheckoutOrder();

        checkoutOrder.addItemToInventory( item1 );

        LineItem addedLineItem = checkoutOrder.addItemToOrder( item1, new BigDecimal( "1" ) );

        Assert.assertTrue( checkoutOrder.getOrderItems().containsKey( addedLineItem.getLineItemId() ) );
        Assert.assertEquals( new BigDecimal( "1" ), checkoutOrder.getOrderItemTotalAmount()
                .get( item1.getItemName() ) );
    }

    @Test
    public void addItemToInventoryShouldUpdatePreviouslyDefinedItem() {
        CheckoutOrder checkoutOrder = new CheckoutOrder();

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
        CheckoutOrder checkoutOrder = new CheckoutOrder();

        checkoutOrder.addItemToInventory( item1 );

        Assert.assertTrue( checkoutOrder.getInventory().containsKey( "foo" ) );
        Assert.assertEquals( "foo", checkoutOrder.getInventory().get( "foo" ).getItemName() );
        Assert.assertEquals( Item.SOLD_BY.PER_UNIT, checkoutOrder.getInventory().get( "foo" ).getSoldBy() );
        Assert.assertEquals( new BigDecimal( "12.12" ), checkoutOrder.getInventory().get( "foo" ).getUnitPrice() );

    }

}
