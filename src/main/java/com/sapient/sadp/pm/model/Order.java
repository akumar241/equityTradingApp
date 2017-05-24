package com.sapient.sadp.pm.model;

import java.util.Comparator;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sapient.sadp.et.model.Block;
import com.sapient.sadp.et.model.EquityTrader;
import com.sapient.sadp.exceptions.InvalidArgumentException;
import com.sapient.sadp.utility.Constants.AccountType;
import com.sapient.sadp.utility.Constants.OrderStatus;
import com.sapient.sadp.utility.Constants.Qualifiers;
import com.sapient.sadp.utility.Constants.Side;
import com.sapient.sadp.utility.Constants.Type;


@Entity
@Table(name = "ORDER_DETAILS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "SECURITY_SYMBOL", nullable = false, updatable = false)
    private Security security;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_SIDE", nullable = false)
    private Side side;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_TYPE", nullable = false)
    private Type type;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_QUALIFIER", nullable = false)
    private Qualifiers qualifiers;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_ACCOUNT_TYPE", nullable = false)
    private AccountType accountType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "PORTFOLIO_ID", nullable = false)
    private Portfolio portfolio;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "EQUITY_TRADER_ID")
    private EquityTrader equityTrader;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "PORTFOLIO_MANAGER_ID", nullable = false, updatable = false)
    private PM pmid;

    @Column(name = "ORDER_QUANTITY_TOTAL", nullable = false)
    private Long totalQuantity;

    @Column(name = "ORDER_QUANTITY_EXECUTED")
    private Long executedQuantity;

    @Column(name = "STOP_PRICE")
    private Double stopPrice;

    @Column(name = "LIMIT_PRICE")
    private Double limitPrice;

    @Column(name = "ORDER_EXECUTED_PRICE")
    private Double executedPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BLOCK_ID")
    private Block block;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    private Date created;

    public static final Comparator<Order> ORDER_TIMESTAMP = new TimeOrder();


    public Order() {
        this.totalQuantity = 0L;
        this.executedQuantity = 0L;
        this.limitPrice = 0D;
        this.stopPrice = 0D;
        this.executedPrice = 0D;
        this.orderId = 0L;
    }

    public Order(Security security, Side side, Type type, Qualifiers qualifiers, AccountType accountType,
            Portfolio portfolio, EquityTrader equityTrader, PM pmid, Long totalQuantity, Long executedQuantity,
            Double stopPrice, Double limitPrice, Double executedPrice, OrderStatus orderStatus, Block block) {
        this.security = security;
        this.side = side;
        this.type = type;
        this.qualifiers = qualifiers;
        this.accountType = accountType;
        this.portfolio = portfolio;
        this.equityTrader = equityTrader;
        this.pmid = pmid;
        this.totalQuantity = totalQuantity;
        this.executedQuantity = executedQuantity;
        this.stopPrice = stopPrice;
        this.limitPrice = limitPrice;
        this.executedPrice = executedPrice;
        this.orderStatus = orderStatus;
        this.block = block;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Security getSecurity() {
        return security;
    }

    public Side getSide() {
        return side;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public void setSide(Side side) {
        if (side == null)
            throw new NullPointerException();
        if (!(this.orderStatus == OrderStatus.NEW))
            throw new UnsupportedOperationException("Can't change side once ET is assigned");
        this.side = side;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        if (type == null)
            throw new NullPointerException();
        if (!(this.orderStatus == OrderStatus.NEW))
            throw new UnsupportedOperationException("Can't change type once ET is assigned");
        this.type = type;
    }

    public Qualifiers getQualifiers() {
        return qualifiers;
    }

    public void setQualifiers(Qualifiers qualifiers) {
        if (qualifiers == null)
            throw new NullPointerException();
        if (!(this.orderStatus == OrderStatus.NEW))
            throw new UnsupportedOperationException("Can't change qualifier once ET is assigned");
        this.qualifiers = qualifiers;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        if (accountType == null)
            throw new NullPointerException();
        if (!(this.orderStatus == OrderStatus.NEW))
            throw new UnsupportedOperationException("Can't change qualifier once ET is assigned");
        this.accountType = accountType;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        if (portfolio == null)
            throw new NullPointerException();
        this.portfolio = portfolio;
    }

    public EquityTrader getEquityTrader() {
        return equityTrader;
    }

    public void setEquityTrader(EquityTrader equityTrader)  {
       try{
           if (equityTrader == null)
               throw new InvalidArgumentException("Equity Trader was NULL");
       }
       catch(InvalidArgumentException e)
       {
           System.out.println(e.getMessage());
       }
        created=new Date();
        this.equityTrader = equityTrader;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        if (totalQuantity <= 0.0)
            throw new IllegalArgumentException();
        this.totalQuantity = totalQuantity;
    }

    public Long getOpenQuantity() {
        return this.getTotalQuantity() - this.getExecutedQuantity();
    }

    public Long getExecutedQuantity() {
        return executedQuantity;
    }

    public void setExecutedQuantity(Long executedQuantity) {
        if (executedQuantity < 0.0)
            throw new IllegalArgumentException("Invalid value for the executed quantity");
        if (this.getTotalQuantity() - executedQuantity < 0.0)
            throw new IllegalArgumentException("Invalid value for executed quantity");
        this.executedQuantity = executedQuantity;
    }
    
    public void executeQuantity(long executedQty) {
        if (executedQuantity < 0.0)
            throw new IllegalArgumentException("Invalid value for the executed quantity");
        if (this.getOpenQuantity() < executedQty)
            throw new IllegalArgumentException("Invalid value for executed quantity");
        this.executedQuantity += executedQty;
    }

    public Double getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(Double stopPrice) {
        if (stopPrice < 0.0)
            throw new IllegalArgumentException();

        this.stopPrice = stopPrice;
    }

    public Double getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(Double limitPrice) {
        if (limitPrice < 0.0)
            throw new IllegalArgumentException();

        this.limitPrice = limitPrice;
    }

    public Double getExecutedPrice() {
        return executedPrice;
    }

    public void setExecutedPrice(Double executedPrice) {
        if (executedPrice < 0.0)
            throw new IllegalArgumentException();
        this.executedPrice = executedPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public PM getPmid() { 
        return pmid;
    }

    public void setPmid(PM pmid) {
        this.pmid = pmid;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        
        this.block = block;
    }

    public Date getCreated() {
        return created;
    }

    private static class TimeOrder implements Comparator<Order> {
        @Override
        public int compare(Order arg0, Order arg1) {
            if (arg0 == null || arg1 == null)
                throw new NullPointerException("Why would you compare null orders");
            if (arg0.getBlock() == null || arg1.getBlock() == null)
                throw new NullPointerException("Order is not assigned to a block");
            // if (arg0.getBlock() != arg1.getBlock())
            // throw new InvalidArgumentException("Orders don't belong to same
            // block");

            int comparision = arg0.getCreated().compareTo(arg1.getCreated());
            if (comparision > 0)
                return 1;
            else if (comparision < 0)
                return -1;
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", security=" + security + ", side=" + side + ", type=" + type
                + ", qualifiers=" + qualifiers + ", accountType=" + accountType + ", portfolio=" + portfolio
                + ", equityTrader=" + equityTrader + ", pmid=" + pmid + ", totalQuantity=" + totalQuantity
                + ", executedQuantity=" + executedQuantity + ", stopPrice=" + stopPrice + ", limitPrice=" + limitPrice
                + ", executedPrice=" + executedPrice + ", orderStatus=" + orderStatus + ", block=" + block + "]";
    }

}
