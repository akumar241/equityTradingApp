package com.sapient.sadp.pm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SECURITY_DETAILS")
public class Security {
    @Id
    @Column(name = "SECURITY_SYMBOL", nullable = false, updatable = false)
    private String securitySymbol;

    @Column(name = "SECURITY_NAME", nullable = false, updatable = false)
    private String securityName;

    @Column(name = "SECURITY_LTP")
    private Double lastTradedPrice;

    // Required by the JPA
    public Security() {
        // TODO Auto-generated constructor stub
    }

    public Security(String securitySymbol, String securityName) {
        if (securitySymbol == null)
            throw new NullPointerException("Security Symbol cannot be null");
        if (securityName == null)
            throw new NullPointerException("Security Name cannot be null");
        this.securitySymbol = securitySymbol;
        this.securityName = securityName;
    }

    public Security(String securitySymbol, String securityName, Double lastTradedPrice) {
        if (securitySymbol == null)
            throw new NullPointerException("Security Symbol cannot be null");
        if (securityName == null)
            throw new NullPointerException("Security Name cannot be null");
        if (lastTradedPrice <= 0.0)
            throw new IllegalArgumentException("Last Traded Price should be greater than zero");
        this.securitySymbol = securitySymbol;
        this.securityName = securityName;
        this.lastTradedPrice = lastTradedPrice;
    }

    public String getSecuritySymbol() {
        return securitySymbol;
    }

    public String getSecurityName() {
        return securityName;
    }

    public Double getLastTradedPrice() {
        return lastTradedPrice;
    }

    public void setSecuritySymbol(String securitySymbol) {
        this.securitySymbol = securitySymbol;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    public void setLastTradedPrice(Double lastTradedPrice) {
        if (lastTradedPrice < 0.0)
            throw new IllegalArgumentException("Last Traded Price should be greater than zero");
        this.lastTradedPrice = lastTradedPrice;
    }

    @Override
    public String toString() {
        return "(" + securitySymbol + ", " + securityName + ", "  + lastTradedPrice + ")";
    }

}
