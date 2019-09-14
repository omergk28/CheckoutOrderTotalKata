package com.pillar.codekata.checkoutordertotal;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ItemTest {

    @Test
    public void markedDownItemShouldReturnMarkedDownUnitPrice() {
        Item markedDown = new Item();
        markedDown.setItemName( "markedDown" );
        markedDown.setUnitPrice( new BigDecimal( "20.00" ) );
        markedDown.setSoldBy( Item.SOLD_BY.PER_WEIGHT );
        markedDown.setMarkdown( new BigDecimal( "5.00" ) );

        Assert.assertEquals( new BigDecimal( "15.00" ), markedDown.getUnitPrice() );
    }
}
