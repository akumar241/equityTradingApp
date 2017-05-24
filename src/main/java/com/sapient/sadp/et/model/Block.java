package com.sapient.sadp.et.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sapient.sadp.exceptions.ValueMismatchException;
import com.sapient.sadp.pm.model.Order;
import com.sapient.sadp.utility.Constants.BlockStatus;
import com.sapient.sadp.utility.Constants.Side;
import com.sapient.sadp.utility.Constants.Type;

// TODO: Auto-generated Javadoc
/**
 * The Class Block is a "simple plain old java object".
 * 
 */
@Entity
@Table(name = "BLOCK")
public class Block {

    /**  The auto-generated id for the given block. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BLOCK_ID")
    private Long id;

    /** The side (i.e. BUY or SELL) of the block */
    @Enumerated(EnumType.STRING)
    @Column(name = "SIDE")
    private Side side;

    /**  The symbol of the equity whose block it is. */
    @Column(name = "EQUITY_SYMBOL")
    private String symbol;

    /**  The status of the block. */
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private BlockStatus status;

    /** The limit price for the block of type Limit else 0.0 */
    @Column(name = "LIMIT_PRICE")
    private Double limitPrice;

    /** The stop price for the block of the type Stop else 0.0 */
    @Column(name = "STOP_PRICE")
    private Double stopPrice;

    /**  The total quantity of the orders that make up the block. */
    @Column(name = "QUANTITY_TOTAL")
    private Long totalQuantity;

    /**  The executed quantity of the block. */
    @Column(name = "QUANTITY_EXECUTED")
    private Long executedQuantity;

    /**  The orders that make up this block. */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "block", cascade = CascadeType.ALL)
    private Set<Order> orders;

    /** The type (i.e. Stop, limit or market */
    @Enumerated(EnumType.STRING)
    @Column(name = "BLOCK_TYPE")
    private Type type;

    /** The trader. */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "EQUITY_TRADER_ID", nullable = false, updatable = false)
    private EquityTrader trader;

    /**
     * Instantiates a new block without any orders in it.
     */
    public Block() {
        orders = new HashSet<Order>();
        this.executedQuantity = 0L;
        this.totalQuantity = 0L;
        this.stopPrice = 0D;
        this.limitPrice = 0D;
    }

    /**
     * Gets the id of this block.
     *
     * @return the id of this block
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Gets the side (i.e. BUY or SELL) of this block
     *
     * @return the side (i.e. BUY or SELL) of this block
     */
    public Side getSide() {
        return side;
    }

    /**
     * Gets the symbol of the security that makes up the orders in the block.
     *
     * @return the symbol symbol of the security that makes up the orders in the
     *         block
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets the status of the block.
     *
     * @return the status of the block
     */
    public BlockStatus getStatus() {
        return status;
    }

    /**
     * Gets the limit price for the block which is 0.0 for the market order
     * Minimum limit price of all the orders for a buy side block maximum limit
     * price of all the orders for a sell side block
     *
     * @return the limit price
     */
    public Double getLimitPrice() {
        return limitPrice;
    }

    /**
     * Gets the stop price for the block which is 0.0 for the market order
     * Minimum stop price of all the orders for a sell side block maximum stop
     * price of all the orders for a buy side block
     * 
     * @return the stop price
     */
    public Double getStopPrice() {
        return stopPrice;
    }

    /**
     * Gets the total quantity of the orders that make up the block.
     *
     * @return the total quantity of the orders that make up the block
     */
    public Long getTotalQuantity() {
        return totalQuantity;
    }

    /**
     * Gets the executed quantity.
     *
     * @return the executed quantity
     */
    public Long getExecutedQuantity() {
        return executedQuantity;
    }

    /**
     * Gets an iterable of the orders that make up the block.
     *
     * @return the Iterable of orders the orders that make up the block
     */
    public Iterable<Order> getOrders() {
        return orders;
    }

    /**
     * Sets the status of the block.
     *
     * @param status            the new status
     */
    public void setStatus(BlockStatus status) {
        this.status = status;
    }

    /**
     * Gets the type of the block.
     *
     * @return the type of the block
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Adds the order into the block.
     *
     * @param order            the order that is to be added to the block
     * @throws ValueMismatchException the value mismatch exception
     */
    public void addOrder(Order order) throws ValueMismatchException {
        // null check
        if (order == null) {
            throw new NullPointerException("Can't add a null order to block");
        }

        // setup all the block parameters if it is the first block

        if (this.orders.isEmpty()) {
            this.side = order.getSide();
            this.symbol = order.getSecurity().getSecuritySymbol().toUpperCase();
            this.type = order.getType();
            this.trader = order.getEquityTrader();

            if (Type.MARKET != this.type) {
                // set the limit price if block is limit
                if (Type.LIMIT == this.getType()) {
                    this.limitPrice = order.getLimitPrice();
                }

                // set the stop price id the block is stop
                if (Type.STOP == this.getType()) {
                    this.stopPrice = order.getStopPrice();
                }
            }
        } else {
            // else verify that they are same
            if (!this.side.equals(order.getSide())) {
                throw new ValueMismatchException("Side Mismatch!");
            }
            if (!this.type.equals(order.getType())) {
                throw new ValueMismatchException("Type Mismatch!");
            }
            if (!this.getSymbol().equals(order.getSecurity().getSecuritySymbol().toUpperCase())) {
                throw new ValueMismatchException("Symbol Mismatch");
            }

            if (!this.getTrader().getName().equals(order.getEquityTrader().getName())) {
                throw new ValueMismatchException("Equity Trader Mismatch");
            }
            // set the limit price if block is limit
            if (Type.LIMIT.equals(this.getType())) {
                this.setLimitPrice(order);
            }
            // set the stop price id the block is stop
            if (Type.STOP.equals(this.getType())) {
                this.setStopPrice(order);
            }
        }

        this.totalQuantity += order.getTotalQuantity();
        this.executedQuantity += order.getExecutedQuantity();
        this.orders.add(order);
    }

    /**
     * Gets the trader.
     *
     * @return the trader
     */
    public EquityTrader getTrader() {
        return trader;
    }

    /**
     * Sets the trader.
     *
     * @param trader the new trader
     */
    public void setTrader(EquityTrader trader) {
		this.trader = trader;
	}

	/**
	 * Sets the limit price.
	 *
	 * @param order the new limit price
	 */
	// private helper to set the limit price when adding orders O(1)
    private void setLimitPrice(Order order) {
        if (Side.BUY == this.side) {
            this.limitPrice = Math.min(this.getLimitPrice(), order.getLimitPrice());
        } else if (Side.SELL == this.side) {
            this.limitPrice = Math.max(this.getLimitPrice(), order.getLimitPrice());
        } else {
            throw new IllegalArgumentException("No such side.");
        }
    }

    /**
     * Sets the stop price.
     *
     * @param order the new stop price
     */
    // private helper to set the stop price when adding orders O(1)
    private void setStopPrice(Order order) {
        // invert comparision
        if (Side.BUY == this.side) {
            this.stopPrice = Math.max(this.getStopPrice(), order.getStopPrice());
        } else if (Side.SELL == this.side) {
            this.stopPrice = Math.min(this.getStopPrice(), order.getStopPrice());
        } else {
            throw new IllegalArgumentException("No such side.");
        }
    }

    /**
     * Removes the order form the block.
     *
     * @param order            the order to be removed from the block
     */
    public void removeOrder(Order order) {
        if (order == null) {
            throw new NullPointerException("Null value passed to remove from the block!");
        }
        // check if the orders set is empty or not
        if (this.orders.isEmpty()) {
            throw new IllegalStateException("Block has no orders!");
        }

        // check if the order is in the set
        if (!this.orders.contains(order)) {
            throw new IllegalArgumentException("Block does not contain this order!");
        }

        // update the limit price for limit order
        if (Type.LIMIT == this.getType()) {
            this.setLimitPrice();
        }

        // update the stop price for stop order
        if (this.getType() == Type.STOP) {
            this.setStopPrice();
        }

        this.totalQuantity -= order.getTotalQuantity();
        this.executedQuantity -= order.getExecutedQuantity();
        this.orders.remove(order);
    }

    /**
     * Sets the stop price.
     */
    // Sets the stop price (for removal) O(n)
    private void setStopPrice() {
        if (Side.BUY == this.side) {
            for (Order order : this.getOrders()) {
                this.limitPrice = Math.min(this.getLimitPrice(), order.getLimitPrice());
            }
        } else if (Side.SELL == this.side) {
            for (Order order : this.getOrders()) {
                this.limitPrice = Math.max(this.getLimitPrice(), order.getLimitPrice());
            }
        } else {
            throw new IllegalArgumentException("No such side.");
        }

    }

    /**
     * Sets the limit price.
     */
    // Sets the limit price (for removal) O(n)
    private void setLimitPrice() {
        if (Side.BUY == this.side) {
            for (Order order : this.getOrders()) {
                this.stopPrice = Math.max(this.getStopPrice(), order.getStopPrice());
            }
        } else if (Side.SELL == this.side) {
            for (Order order : this.getOrders()) {
                this.stopPrice = Math.min(this.getStopPrice(), order.getStopPrice());
            }
        } else {
            throw new IllegalArgumentException("No such side.");
        }
    }

    /**
     * Sets the executed quantity.
     *
     * @param executedQuantity
     *            the new executed quantity
     */
    // subject to change
    public void executeQuantity(long executedQuantity) {
        if (this.getTotalQuantity() - this.getExecutedQuantity() - executedQuantity < 0.0)
            throw new IllegalArgumentException("Entered quantity is not valid");
        this.executedQuantity += executedQuantity;
    }

    /**
     * Gets the open quantity.
     *
     * @return the open quantity
     */
    public double getOpenQuantity() {
        return this.getTotalQuantity() - this.getExecutedQuantity();
    }
}
