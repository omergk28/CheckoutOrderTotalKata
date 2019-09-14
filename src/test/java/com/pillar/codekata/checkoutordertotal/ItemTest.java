package com.pillar.codekata.checkoutordertotal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ItemTest {

    Item markedDown;
    Item notMarkedDown;

    @Before
    public void setUp() {
        markedDown = new Item();
        markedDown.setItemName( "markedDown" );
        markedDown.setUnitPrice( new BigDecimal( "20.00" ) );
        markedDown.setSoldBy( Item.SOLD_BY.PER_WEIGHT );
        markedDown.setMarkdown( new BigDecimal( "5.00" ) );

        notMarkedDown = new Item();
        notMarkedDown.setItemName( "regularItem" );
        notMarkedDown.setUnitPrice( new BigDecimal( "20.00" ) );
        notMarkedDown.setSoldBy( Item.SOLD_BY.PER_WEIGHT );
    }

    @Test
    public void markedDownItemShouldReturnMarkedDownUnitPrice() {
        Assert.assertEquals( new BigDecimal( "15.00" ), markedDown.getUnitPrice() );
    }

    @Test
    public void isMarkedDownShouldReturnTrueForMarkedDownItem() {
        Assert.assertTrue( markedDown.isMarkedDown() );
        Assert.assertFalse( notMarkedDown.isMarkedDown() );
    }
}
