package com.pillar.codekata.checkoutordertotal;

import java.math.BigDecimal;
import java.util.UUID;

public class LineItem {

    private Item item;
    private BigDecimal amount;
    private final UUID lineItemId = UUID.randomUUID();

    public LineItem( Item item, BigDecimal amount ) {
        this.item = item;
        this.amount = amount;
    }

    public UUID getLineItemId() {
        return lineItemId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
