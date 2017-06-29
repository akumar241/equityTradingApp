package com.sapient.sadp.pm.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class Portfolio.
 */
@Entity
@Table(name = "PORTFOLIO_DETAILS")
public class Portfolio {

    /** The portfolio id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PORTFOLIO_ID")
    private Long portfolioId;

    /** The portfolio name. */
    @Column(name = "PORTFOLIO_NAME", unique = true)
    private String portfolioName;

    /** The pm. */
    @ManyToOne
    @JoinColumn(name = "PORTFOLIO_MANAGER_ID", nullable = false, updatable = false)
    private PM pm;

    /** The holdings. *//*
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "portfolio")
    private Set<Holdings> holdings;

    *//** The orders. *//*
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "portfolio")
    private Set<Order> orders;
*/
    /**
     * Instantiates a new portfolio.
     */
    public Portfolio() {
       /* orders = new HashSet<Order>();
        holdings = new HashSet<Holdings>();*/
    }


    public Portfolio(String portfolioName, PM pm) {
		super();
		this.portfolioName = portfolioName;
		this.pm = pm;
	}


	/**
     * Gets the portfolio id.
     *
     * @return the portfolio id
     */
    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }


    /**
     * Gets the portfolio name.
     *
     * @return the portfolio name
     */
    public String getPortfolioName() {
    	return portfolioName;
    	
    }
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }


    
    /**
     * Gets the pm.
     *
     * @return the pm
     */
    public PM getPm() {
    	return pm;
    }
    
    public void setPm(PM pm) {
        this.pm = pm;
    }



    /**
     * Gets the holdings.
     *
     * @return the holdings
     */
    /*public Iterable<Holdings> getHoldings() {
        return holdings;
    }

    *//**
     * Gets the orders.
     *
     * @return the orders
     *//*
    public Iterable<Order> getOrders() {
        return orders;
    }

    *//**
     * Adds the orders.
     *
     * @param orders
     *            the orders
     *//*
    public void addOrders(Iterable<Order> orders) {
        if (orders == null)
            throw new NullPointerException("List of orders is null");
        for (Order order : orders) {
            this.addOrder(order);
        }
    }

    *//**
     * Adds the order.
     *
     * @param order
     *            the order
     *//*
    public void addOrder(Order order) {
        if (order == null) {
            throw new NullPointerException("Can't add a null order to portfolio");
        }
        orders.add(order);
    }

    *//**
     * Adds the holdings.
     *
     * @param holdings
     *            the holdings
     *//*
    public void addHoldings(Iterable<Holdings> holdings) {
        for (Holdings holding : holdings) {
            this.addHolding(holding);
        }
    }

    *//**
     * Adds the holding.
     *
     * @param holding
     *            the holding
     *//*
    public void addHolding(Holdings holding) {
        if (holding == null) {
            throw new NullPointerException("Can't add a null holding to portfolio");
        } else
            holdings.add(holding);
    }

    *//**
     * Removes the holdings.
     *
     * @param holdings
     *            the holdings
     *//*
    public void removeHoldings(Iterable<Holdings> holdings) {
        if (holdings == null) {
            throw new NullPointerException("List of holdings is null");
        }
        for (Holdings holding : holdings) {
            this.removeHolding(holding);
        }
    }

    *//**
     * Removes the holding.
     *
     * @param holding
     *            the holding
     *//*
    public void removeHolding(Holdings holding) {
        if (holding == null) {
            throw new NullPointerException("Null value passed to remove from the portfolio!");
        }
        
        if (this.holdings.isEmpty()) {
            throw new IllegalStateException("Portfolio has no holdings!");
        }

        if (!this.holdings.contains(holding)) {
            throw new IllegalArgumentException("Portfolio does not contain this holding!");
        }
        this.holdings.remove(holding);
    }
*/
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Portfolio [portfolioId=" + portfolioId + ", portfolioName=" + portfolioName +  "]";
    }
}