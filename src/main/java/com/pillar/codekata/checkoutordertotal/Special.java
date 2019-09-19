package com.pillar.codekata.checkoutordertotal;

import java.math.BigDecimal;

public class Special {
    private int buyN;
    private int getM;
    private int XPercentOff;
    private int limit;
    private BigDecimal forXDollars;

    public Special( int buyN, int getM, int XPercentOff, int limit ) {
        this.buyN = buyN;
        this.getM = getM;
        this.XPercentOff = XPercentOff;
        this.limit = limit;
    }

    public Special( int buyN, int getM, int XPercentOff ) {
        this.buyN = buyN;
        this.getM = getM;
        this.XPercentOff = XPercentOff;
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

    public int getXPercentOff() {
        return XPercentOff;
    }

    public void setXPercentOff( int XPercentOff ) {
        this.XPercentOff = XPercentOff;
    }

    public BigDecimal getForXDollars() {
        return forXDollars;
    }

    public void setForXDollars( BigDecimal forXDollars ) {
        this.forXDollars = forXDollars;
    }
}
