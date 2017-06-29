package com.sapient.sadp.pm.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.sadp.et.model.EquityTrader;
import com.sapient.sadp.exceptions.InvalidArgumentException;
import com.sapient.sadp.pm.dao.OrderDAO;
import com.sapient.sadp.pm.model.Holdings;
import com.sapient.sadp.pm.model.Order;
import com.sapient.sadp.pm.model.Security;
import com.sapient.sadp.utility.Constants.OrderStatus;
import com.sapient.sadp.utility.Constants.Side;

/**
 * The Class OrderManager.
 */
@Component
public class OrderService {

    /** The order dao. */
    @Autowired(required = true)
    private OrderDAO orderDao;

    /** The security manager. */
    @Autowired
    private SecurityManager securityManager;

    /** The holdings manager. */
 /*   @Autowired(required = true)
    private HoldingsManager holdingsManager;*/

    
    /**
     * Creates the order.
     *
     * @param order
     *            the order
     */
    public void createOrder(Order order) {
        orderDao.persist(order);
    }

    /**
     * View orders by status.
     *
     * @param status
     *            the status
     * @param pmId
     *            the pm id
     * @return the iterable
     */
    public List<Order> viewOrdersByStatus(OrderStatus status, Long pmId) {
        return orderDao.viewOrdersOfManager(status, pmId);
    }

    /**
     * Edits the order.
     *
     * @param order
     *            the order
     */
    public void editOrder(Order order) {
        orderDao.merge(order);
    }

    /**
     * Amend order.
     *
     * @param order
     *            the order
     */
    
    public void amendOrder(Order order) {
        orderDao.merge(order);
    }

    /**
     * Update order when fills are received.
     *
     * @param order
     *            the order
     * @throws InvalidArgumentException
     *             the invalid argument exception
     */
    
    public void updateOrder(Order order, long executedQuantity, double price) throws InvalidArgumentException {
        /*order.setExecutedPrice(price);
        order.executeQuantity(executedQuantity);
        Holdings holding = null;
        System.out.println(holdingsManager);
        try{
            holding = holdingsManager.getHoldingsByPortfolioAndSecurity(order.getPortfolio().getPortfolioId(),
                    order.getSecurity().getSecuritySymbol());
            
        }catch(NoResultException nre){
        }
        boolean flag = false;
        if (holding != null) {
            if (Side.BUY == order.getSide()) {
                holding.addExecutedQuantityAtPrice(executedQuantity, order.getExecutedPrice());
            } else {
                if (holding.getQuantity() == executedQuantity) {
                    holdingsManager.removeHoldings(holding.getId());
                    flag = true;
                } else{
                    holding.removeExecutedQuantityAtPrice(executedQuantity, order.getExecutedPrice());
                }
            }
        } 
        else {
            holding = new Holdings(order.getPortfolio(), order.getSecurity(), order.getExecutedPrice(),
                    executedQuantity);
            holdingsManager.addHolding(holding);
        }
        if (!flag)
            holdingsManager.merge(holding);

        Security security = securityManager.getSecurityBySymbol(order.getSecurity().getSecuritySymbol());
        security.setLastTradedPrice(order.getExecutedPrice());
        securityManager.updateSecurityLTP(security);
        orderDao.merge(order);*/
    }

    /**
     * Cancel order.
     *
     * @param orderId
     *            the order id
     */
    public void cancelOrder(Long orderId) {
        orderDao.cancelOrder(orderId);
    }

    /**
     * View orders of trader.
     *
     * @param status
     *            the status
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Order> viewOrdersOfTrader(Long traderId) {
        return orderDao.viewOrdersOfTrader(traderId);

    }

    /**
     * Send order to trader.
     *
     * @param traderId
     *            the trader id
     * @param orderId
     *            the order id
     */
    public void sendToTrader(EquityTrader equityTrader, Long orderId) {
        orderDao.sendToTrader(equityTrader, orderId);
    }

    /**
     * Gets the order by block id.
     *
     * @param blockId
     *            the block id
     * @return the order by block id
     */
    public Iterable<Order> getOrderByBlockId(Long blockId) {
        return orderDao.getOrderByBlockId(blockId);
    }

    /**
     * Gets the order by order id.
     *
     * @param orderId
     *            the order id
     * @return the order by order id
     */
    public Order getOrderByOrderId(Long orderId) {
        return orderDao.findById(orderId);
    }
    
    public Long findOpenQuantityOfOrders(Long portfolioId, String security){
        return orderDao.findOpenQuantityOfOrders(portfolioId, security);
    }
    
    public Iterable<Order> getDayOrderForCancellation()
    {
        return orderDao.getDayOrderForCancellation();
    }
}
