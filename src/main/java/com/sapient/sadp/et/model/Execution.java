package com.sapient.sadp.et.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The Class Execution is a "simple plain old java object".
 * 
 * @author Team Broker
 *
 */

@Entity
public class Execution {
    @Id
    private Long executionID;

    private Long blockID;

    private Long quantity;

    private Date timeStamp;

    private String symbol;

    private Double transactionPrice;

    private boolean status;

    public Long getExecutionID() {
        return executionID;
    }

    public Long getBlockID() {
        return blockID;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getTransactionPrice() {
        return transactionPrice;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Execution [executionID=" + executionID + ", blockID=" + blockID + ", quantity=" + quantity + ", symbol="
                + symbol + ", transactionPrice=" + transactionPrice + ", status=" + status + "]";
    }
}
