package com.sapient.sadp.et.model;

import java.util.Set;

import com.sapient.sadp.pm.model.Order;
import com.sapient.sadp.utility.Constants.BlockStatus;
import com.sapient.sadp.utility.Constants.Side;
import com.sapient.sadp.utility.Constants.Type;

public class JMSBlock {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BlockStatus getStatus() {
		return status;
	}

	public void setStatus(BlockStatus status) {
		this.status = status;
	}

	public Double getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(Double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public Double getStopPrice() {
		return stopPrice;
	}

	public void setStopPrice(Double stopPrice) {
		this.stopPrice = stopPrice;
	}

	public Long getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Long getExecutedQuantity() {
		return executedQuantity;
	}

	public void setExecutedQuantity(Long executedQuantity) {
		this.executedQuantity = executedQuantity;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	/** The auto-generated id for the given block */

	private Long id;

	/** The side (i.e. BUY or SELL) of the block */
	private Side side;

	/** The symbol of the equity whose block it is */

	private String symbol;

	/** The status of the block */

	private BlockStatus status;

	public JMSBlock() {
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	/** The limit price for the block of type Limit else 0.0 */

	private Double limitPrice;

	/** The stop price for the block of the type Stop else 0.0 */

	private Double stopPrice;

	/** The total quantity of the orders that make up the block */

	private Long totalQuantity;

	/** The executed quantity of the block */

	private Long executedQuantity;

	/*
	 * public JMSBlock(Long id, Side side, String symbol, BlockStatus status,
	 * Double limitPrice, Double stopPrice, Long totalQuantity, Long
	 * executedQuantity, Set<Order> orders, Type type) { super(); this.id = id;
	 * this.side = side; this.symbol = symbol; this.status = status;
	 * this.limitPrice = limitPrice; this.stopPrice = stopPrice;
	 * this.totalQuantity = totalQuantity; this.executedQuantity =
	 * executedQuantity; this.orders = orders; this.type = type; }
	 */

	/** The orders that make up this block */

	private Set<Order> orders;

	/** The type (i.e. Stop, limit or market */

	private Type type;

	@SuppressWarnings("unused")
	private EquityTrader trader;

	public static JMSBlock getJMSBlock(Block block) {
		JMSBlock jblock = new JMSBlock();
		jblock.setId(block.getId());
		jblock.setExecutedQuantity(block.getExecutedQuantity());
		jblock.setLimitPrice(block.getLimitPrice());
		jblock.setSide(block.getSide());
		jblock.setStatus(block.getStatus());
		jblock.setSymbol(block.getSymbol());
		jblock.setType(block.getType());
		jblock.setTotalQuantity(block.getTotalQuantity());
		jblock.setStopPrice(block.getStopPrice());
		return jblock;
	}

}
