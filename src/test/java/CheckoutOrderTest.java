import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CheckoutOrderTest {

    @Test
    public void checkoutOrderShouldAddItemToInventory() throws Exception {
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
