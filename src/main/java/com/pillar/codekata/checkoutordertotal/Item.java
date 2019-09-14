package com.pillar.codekata.checkoutordertotal;

import java.math.BigDecimal;

public class Item {

    public enum SOLD_BY {PER_UNIT, PER_WEIGHT;}

    private String itemName;
    private SOLD_BY soldBy;
    private BigDecimal unitPrice;
    private BigDecimal markdown;

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
        BigDecimal price = unitPrice;

        if ( isMarkedDown() )
            price = price.subtract( markdown );

        return price;
    }

    public void setUnitPrice( BigDecimal unitPrice ) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getMarkdown() {
        return markdown;
    }

    public void setMarkdown( BigDecimal markdown ) {
        this.markdown = markdown;
    }

    public boolean isMarkedDown() {
        return markdown != null && markdown.compareTo( new BigDecimal( "0" ) ) > 0;
    }
}
