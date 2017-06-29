package com.sapient.sadp.pm.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.sadp.pm.model.Holdings;
import com.sapient.sadp.utility.OrderQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class HoldingsDAO.
 */
@Component
public class HoldingsDAO  {

	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

    /**
     * Gets the holding.
     *
     * @param portfolio the portfolio
     * @param security the security
     * @return the holding
     */
    
    public Holdings getHoldingsByPortfolioAndSecurity(Long portfolioId, String securitySymbol) {
    	Session session = this.sessionFactory.getCurrentSession();
            String q = OrderQuery.getHoldingByPortfolioAndSecurity;
            Query query = session.createQuery(q);
            query.setParameter(OrderQuery.portfolioId, portfolioId);
            query.setParameter(OrderQuery.security, securitySymbol);
           // Holdings holding = (Holdings) query.getSingleResult();
            return null;
    }
    
    /**
     * Gets the holding.
     *
     * @param portfolio the portfolio
     *
     * @return the iterable
     */
    public Iterable<Holdings> getHoldingsByPortfolio(Long portfolioId) {
    	Session session = this.sessionFactory.getCurrentSession();
            String q = OrderQuery.getHoldingsByPortfolioIdQuery;
            Query query = session.createQuery(q);
            query.setParameter(OrderQuery.portfolioId, portfolioId);
		//	List<Holdings> holdings = query.getResultList();
            return null;
    }

    /**
     * Persist.
     *
     * @param holding the holding
     */
    @Transactional()
    public void persist(Holdings holding) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(holding);
    }

    /**
     * Merge.
     *
     * @param holdings the holdings
     */

    @Transactional
    public void merge(Holdings holdings) {
    	Session session = this.sessionFactory.getCurrentSession();
        if (holdings.getId() != null) {
            try {
                session.merge(holdings);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
  
    /**
     * Removes the.
     *
     * @param HoldingId the holding id
     */
    
    @Transactional
    public void remove(Long id) {
    	Session session = this.sessionFactory.getCurrentSession();
    	Holdings holding = (Holdings) session.load(Holdings.class, id);
        session.delete(holding);   
    }
  
    /**
     * Find by id.
     *
     * @param Holdingid the holdingid
     * @return the holdings
     */

    public Holdings findById(long holdingId) {   
    	Session session = this.sessionFactory.getCurrentSession();
    	return (Holdings) session.load(Holdings.class, holdingId);
    }
}
