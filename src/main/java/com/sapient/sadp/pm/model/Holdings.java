package com.sapient.sadp.pm.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.sapient.sadp.exceptions.InvalidArgumentException;


/**
 * The Class Holdings.
 */
@Entity
@Table(name = "HOLDINGS_DETAILS", uniqueConstraints = { @UniqueConstraint( columnNames = { "PORTFOLIO_ID", "SECURITY_SYMBOL"})})
public class Holdings {

    /** The Id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "HOLDING_ID")
    private Long Id;

    /** The quantity. */
    @Column(name = "HOLDING_QUANTITY")
    private Long quantity;

    /** The average price. */
    @Column(name = "HOLDING_AVERAGE_PRICE")
    private Double averagePrice;

    /** The portfolio. */
    @ManyToOne
    @JoinColumn(name = "PORTFOLIO_ID", nullable = false, updatable = false)
    private Portfolio portfolio;

    /** The security. */
    @OneToOne
    @JoinColumn(name = "SECURITY_SYMBOL", nullable = false, updatable = false)
    private Security security;

    /**
     * Instantiates a new holdings.
     */
    public Holdings() {
    }

    /**
     * Instantiates a new holdings.
     *
     * @param portfolio
     *            the portfolio
     * @param security
     *            the security
     */
    public Holdings(Portfolio portfolio, Security security, Double averagePrice, Long quantity)throws InvalidArgumentException {
        if (portfolio == null)
            throw new NullPointerException("Portfolio of a holding can't be null");
        if (security == null)
            throw new NullPointerException("Security of a holding can't be null");
        if (quantity <= 0)
            throw new InvalidArgumentException("Quantity is not positive");
        if (averagePrice <= 0)
            throw new InvalidArgumentException("Average Price is not positive");
        this.portfolio = portfolio;
        this.security = security;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
    }

    /**
     * Instantiates a new holdings.
     *
     * @param quantity
     *            the quantity
     * @param averagePrice
     *            the average price
     * @param portfolio
     *            the portfolio
     * @param security
     *            the security
     * @throws InvalidArgumentException
     *             the invalid argument exception
     */
   

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return Id;
    }

    /**
     * Gets the quantity.
     *
     * @return the quantity
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * Adds the quantity at price.
     *
     * @param quantity
     *            the quantity
     * @param price
     *            the price
     * @throws InvalidArgumentException
     *             the invalid argument exception
     */
    public void addExecutedQuantityAtPrice(long executedQuantity, double executedPrice) throws InvalidArgumentException {
        if (executedQuantity <= 0)
            throw new InvalidArgumentException("Quantity is not positive");
        if (averagePrice <= 0)
            throw new InvalidArgumentException("Average Price is not positive");
        this.averagePrice = (this.getQuantity() * this.getAveragePrice() + executedQuantity * executedPrice)
                / (this.getQuantity() + executedQuantity);
        this.averagePrice=Double.parseDouble(String.format("%.2lf",this.averagePrice));
        this.quantity += executedQuantity;
    }

    /**
     * Removes the quantity at price.
     *
     * @param quantity
     *            the quantity
     * @param price
     *            the price
     * @throws InvalidArgumentException
     *             the invalid argument exception
     */
    public void removeExecutedQuantityAtPrice(long executedQuantity, double executedPrice) throws InvalidArgumentException {
        System.out.println(this.getQuantity());
        System.out.println(executedQuantity);
        if (executedQuantity <= 0)
            throw new InvalidArgumentException("Quantity is not positive");
        if (averagePrice <= 0)
            throw new InvalidArgumentException("Average Price is not positive");

        if (executedQuantity - this.getQuantity() > 0)
            throw new InvalidArgumentException("Quantity entered is more than the present quantity");
        this.averagePrice = (this.getQuantity() * this.getAveragePrice() - executedQuantity * executedPrice)
                / (this.getQuantity() - executedQuantity);
        this.averagePrice=Double.parseDouble(String.format("%.2lf",this.averagePrice));
        this.quantity -= executedQuantity;
    }
    
    

    /**
     * Gets the average price.
     *
     * @return the average price
     */
    public Double getAveragePrice() {
        return averagePrice;
    }

    /**
     * Gets the portfolio.
     *
     * @return the portfolio
     */
    public Portfolio getPortfolio() {
        return portfolio;
    }

    /**
     * Gets the security.
     *
     * @return the security
     */
    public Security getSecurity() {
        return security;
    }
    
    public void setQuantity(Long quantity) throws InvalidArgumentException{
        if(quantity>=0)
        this.quantity = quantity;
        else
            throw new InvalidArgumentException("Quantity is not positive");
            
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "(" + quantity + "," + averagePrice + "," + portfolio.getPortfolioName() + "," + security.getSecurityName() + ")";
    }
}
