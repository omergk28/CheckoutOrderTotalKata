import java.math.BigDecimal;

public class Item {

    public enum SOLD_BY {PER_UNIT, PER_WEIGHT}

    private String itemName;
    private SOLD_BY soldBy;
    private BigDecimal unitPrice;

    public String getItemName() {
        return itemName;
    }

    public void setItemName( String itemName ) {
        this.itemName = itemName;
    }

    public SOLD_BY getSoldBy() {
        return soldBy;
    }

    public void setSoldBy( SOLD_BY soldBy ) {
        this.soldBy = soldBy;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice( BigDecimal unitPrice ) {
        this.unitPrice = unitPrice;
    }
}
