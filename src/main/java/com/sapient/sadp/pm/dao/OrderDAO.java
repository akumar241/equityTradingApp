package com.sapient.sadp.pm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.sadp.et.model.EquityTrader;
import com.sapient.sadp.pm.model.Order;
import com.sapient.sadp.utility.Constants.OrderStatus;
import com.sapient.sadp.utility.OrderQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderDAO.
 */
@Component
public class OrderDAO {

	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

    /**
     * Cancel order.
     *
     * @param orderId
     *            the order id
     */

    @Transactional()
    public void cancelOrder(Long orderId) {
    	Session session = this.sessionFactory.getCurrentSession();	
        Order order = (Order) session.load(Order.class, orderId);
        order.setOrderStatus(OrderStatus.CANCELLED);
        session.delete(order);
        session.save(order);
    }

    /**
     * View orders of trader.
     *
     * @param traderId
     *            the trader id
     * @return the iterable
     */

    public Iterable<Order> viewOrdersOfTrader(Long traderId) {
    	Session session = this.sessionFactory.getCurrentSession();	
        String q = OrderQuery.viewOrdersByTrader;
        Criteria criteria = session.createCriteria(Order.class);
        criteria.add(Restrictions.eq("equityTrader", traderId));
        return criteria.list();
    }

    /**
     * View orders of manager.
     *
     * @param status
     *            the status
     * @param managerId
     *            the manager id
     * @return the list
     */

    public List<Order> viewOrdersOfManager(OrderStatus status, Long managerId) {
        String q = OrderQuery.viewOrdersByManager;
        Query query = null;//entityManager.createQuery(q, Order.class);
        query.setParameter(OrderQuery.status, status.toString());
        query.setParameter(OrderQuery.managerId, managerId);
        @SuppressWarnings("unchecked")
        List<Order> orders =null;// query.getResultList();
        System.out.println(orders.size() + "    " + status + "    " + managerId);
        return orders;
    }

    /**
     * Send to trader.
     *
     * @param equityTrader the equity trader
     * @param orderId            the order id
     * @return the iterable
     */

    @Transactional
    public void sendToTrader(EquityTrader equityTrader, Long orderId) {
        Order order = findById(orderId);
        order.setEquityTrader(equityTrader);
        //entityManager.merge(order);
    }

    /**
     * Gets the order by block id.
     *
     * @param id
     *            the id
     * @return the orders by block id
     */

    public Iterable<Order> getOrderByBlockId(Long id) {
        String q = OrderQuery.getOrdersByBlockId;
        Query query = null;//entityManager.createQuery(q, Order.class);
        query.setParameter(OrderQuery.blockId, id);
        @SuppressWarnings("unchecked")
        List<Order> orders = null;//query.getResultList();
        return orders;
    }

    /**
     * Merge.
     *
     * @param order the order
     */

    @Transactional
    public void merge(Order order) {
        if (order.getOrderId() != null) {
            try {
               // entityManager.merge(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Persist.
     *
     * @param order
     *            the order
     */

    @Transactional
    public void persist(Order order) {
     
    }

    /**
     * Find by id.
     *
     * @param orderId
     *            the order id
     * @return the order
     */

    public Order findById(Long orderId) {
    	Session session = this.sessionFactory.getCurrentSession();
        return (Order) session.load(Order.class, orderId);
    }

    public Long findOpenQuantityOfOrders(Long portfolioId, String security){
    	Session session = this.sessionFactory.getCurrentSession();
        String q = OrderQuery.getOpenQuantitiesofOrders;
        org.hibernate.Query query = session.createQuery(q);
        query.setParameter(OrderQuery.portfolioId, portfolioId);
        query.setParameter(OrderQuery.security, security);
        Double qty = null;//(Double) query.getSingleResult();
        if(qty!=null)
            return (new Double(qty)).longValue();
        else 
            return 0L;
    }

    public Iterable<Order> getDayOrderForCancellation()
    {
        String q = OrderQuery.getDayOrdersForCancellation ;
        Query query = null;//entityManager.createQuery(q);
        @SuppressWarnings("unchecked")
        List<Order> orders = null;//query.getResultList();
        return orders;
    }

}
