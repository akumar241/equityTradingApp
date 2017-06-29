package com.sapient.sadp.et.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.sadp.et.dao.BlockDAO;
import com.sapient.sadp.et.model.Block;
import com.sapient.sadp.exceptions.ValueMismatchException;
import com.sapient.sadp.pm.model.Order;
import com.sapient.sadp.pm.service.OrderService;
import com.sapient.sadp.utility.Constants.BlockStatus;
import com.sapient.sadp.utility.Constants.OrderStatus;
import com.sapient.sadp.utility.Constants.Side;
import com.sapient.sadp.utility.Constants.Type;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockManager.
 */
@Component
public class BlockManager {
	
	
    /** The block dao. */
    @Autowired(required = true)
    public BlockDAO blockDAO;

    /** The order manager. */
    @Autowired(required = true)
    private OrderService orderManager;

    /**
     * Assign orders to block.
     *
     * @param orderIds
     *            the order id's
     * @param blockId
     *            the block id
     * @throws ValueMismatchException
     *             the value mismatch exception
     */
    public void assignOrdersToBlock(Iterable<Long> orderIds, Long blockId) throws ValueMismatchException {
        Block block = blockDAO.findById(blockId);
        Order order;
        for (Long orderId : orderIds) {
            order = orderManager.getOrderByOrderId(orderId);
            block.addOrder(order);
            order.setBlock(block);
            order.setOrderStatus(OrderStatus.ASSIGNED);
            orderManager.editOrder(order);
        }
    }

    /**
     * Update block.
     *
     * @param block
     *            the block
     */
    public void updateBlock(Block block) {
        blockDAO.merge(block);
    }

    /**
     * Gets the block details.
     *
     * @param blockId
     *            the block id
     * @return the block details
     */
    public Block getBlockDetails(Long blockId) {
        // Get list of orders from the block...
        return blockDAO.findById(blockId);
    }

    /**
     * Assign orders to new block.
     *
     * @param orderIds
     *            the order id's
     * @return the block
     * @throws ValueMismatchException
     *             the value mismatch exception
     */
    @Transactional
    public Block assignOrdersToNewBlock(Iterable<Long> orderIds) throws ValueMismatchException {
        Block block = new Block();
        Order order = orderManager.getOrderByOrderId(orderIds.iterator().next());
        block.setTrader(order.getEquityTrader());
        ;
        blockDAO.persist(block);
        for (Long orderId : orderIds) {
            order = orderManager.getOrderByOrderId(orderId);
            block.addOrder(order);
            order.setBlock(block);
            order.setOrderStatus(OrderStatus.ASSIGNED);
            orderManager.editOrder(order);
        }
        block.setStatus(BlockStatus.NEW);
        blockDAO.merge(block);
        return block;
    }

    /**
     * Gets the order matching blocks belonging to the trader.
     *
     * @param order
     *            the order
     * @param traderId
     *            the trader id
     * @return the order matching blocks
     */
    public Iterable<Block> getOrderMatchingBlocks(Order order, Long traderId) {
        return blockDAO.getMatchingBlocks(order, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param side
     *            the side
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(Side side, Long traderId) {
        return blockDAO.filterAvailableBlocks(side, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(String symbol, Long traderId) {
        return blockDAO.filterAvailableBlocks(symbol, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param type
     *            the type
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(Type type, Long traderId) {
        return blockDAO.filterAvailableBlocks(type, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param side
     *            the side
     * @param type
     *            the type
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(Side side, Type type, Long traderId) {
        return blockDAO.filterAvailableBlocks(side, type, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param type
     *            the type
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(Type type, String symbol, Long traderId) {
        return blockDAO.filterAvailableBlocks(type, symbol, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param side
     *            the side
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(Side side, String symbol, Long traderId) {
        return blockDAO.filterAvailableBlocks(side, symbol, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param side
     *            the side
     * @param type
     *            the type
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(Side side, Type type, String symbol, Long traderId) {
        return blockDAO.filterAvailableBlocks(side, type, symbol, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param side
     *            the side
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterExecutedBlocks(Side side, Long traderId) {
        return blockDAO.filterExecutedBlocks(side, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterExecutedBlocks(String symbol, Long traderId) {
        return blockDAO.filterExecutedBlocks(symbol, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param type
     *            the type
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterExecutedBlocks(Type type, Long traderId) {
        return blockDAO.filterExecutedBlocks(type, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param side
     *            the side
     * @param type
     *            the type
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterExecutedBlocks(Side side, Type type, Long traderId) {
        return blockDAO.filterExecutedBlocks(side, type, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param type
     *            the type
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterExecutedBlocks(Type type, String symbol, Long traderId) {
        return blockDAO.filterExecutedBlocks(type, symbol, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param side
     *            the side
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterExecutedBlocks(Side side, String symbol, Long traderId) {
        return blockDAO.filterExecutedBlocks(side, symbol, traderId);
    }

    /**
     * Filter blocks.
     *
     * @param side
     *            the side
     * @param type
     *            the type
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterExecutedBlocks(Side side, Type type, String symbol, Long traderId) {
        return blockDAO.filterExecutedBlocks(side, type, symbol, traderId);
    }

    /**
     * Gets the trader blocks.
     *
     * @param traderId
     *            the trader id
     * @return the trader blocks
     */
    public Iterable<Block> getAvailableTraderBlocks(Long traderId) {
        // Get all blocks of a trader.
        return blockDAO.getAvailableTraderBlocks(traderId);
    }

    /**
     * Gets the executed trader blocks.
     *
     * @param traderId
     *            the trader id
     * @return the executed trader blocks
     */
    public Iterable<Block> getExecutedTraderBlocks(Long traderId) {
        // Get all blocks of a trader.
        return blockDAO.getExecutedTraderBlocks(traderId);
    }

    /**
     * Delete order from block.
     *
     * @param orderId
     *            the order id
     */
    @Transactional
    public void deleteOrderFromBlock(Long orderId) {
        Order order = orderManager.getOrderByOrderId(orderId);
        Long blockId = order.getBlock().getId();
        Block block = blockDAO.findById(blockId);
        block.removeOrder(order);
        block = blockDAO.deleteOrderFromBlock(orderId, block);
        Set<Order> orders = (Set<Order>) block.getOrders();
        order.setBlock(null);
        order.setOrderStatus(OrderStatus.UNASSIGNED);
        orderManager.editOrder(order);
        if (orders.size() == 0) {
            deleteBlock(blockId);
        }
    }

    /**
     * Delete block.
     *
     * @param blockId
     *            the block id
     */
    @Transactional
    public void deleteBlock(Long blockId) {
        blockDAO.remove(blockId);
    }

    public Iterable<Order> getTimestampSortedOrders(Block block) {
        ArrayList<Order> orders = new ArrayList<>();
        for (Order order : block.getOrders())
            orders.add(order);
        Collections.sort(orders, Order.ORDER_TIMESTAMP);
        return orders;
    }
}
