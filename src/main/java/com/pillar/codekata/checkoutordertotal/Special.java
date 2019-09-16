package com.pillar.codekata.checkoutordertotal;

import java.math.BigDecimal;

public class Special {
    private int buyN;
    private int getM;
    private int percentXOff;
    private int limit;
    private BigDecimal forXDollars;

    public Special( int buyN, int getM, int percentXOff, int limit ) {
        this.buyN = buyN;
        this.getM = getM;
        this.percentXOff = percentXOff;
        this.limit = limit;
    }

    public Special( int buyN, int getM, int percentXOff ) {
        this.buyN = buyN;
        this.getM = getM;
        this.percentXOff = percentXOff;
    }

    public Special( int buyN, BigDecimal forXDollars ) {
        this.buyN = buyN;
        this.forXDollars = forXDollars;
    }

    public int getBuyN() {
        return buyN;
    }

    public void setBuyN( int buyN ) {
        this.buyN = buyN;
    }

    public int getGetM() {
        return getM;
    }

    public void setGetM( int getM ) {
        this.getM = getM;
    }

    public int getPercentXOff() {
        return percentXOff;
    }

    public void setPercentXOff( int percentXOff ) {
        this.percentXOff = percentXOff;
    }

    public BigDecimal getForXDollars() {
        return forXDollars;
    }

    public void setForXDollars( BigDecimal forXDollars ) {
        this.forXDollars = forXDollars;
    }
}
