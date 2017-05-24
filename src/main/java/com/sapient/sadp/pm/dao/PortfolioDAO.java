package com.sapient.sadp.pm.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.sadp.pm.model.Portfolio;

// TODO: Auto-generated Javadoc
/**
 * The Class PortfolioDAO.
 */
@Component
public class PortfolioDAO  {

    /** The entity manager. */
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

    /**
     * Gets all portfolios by pm id.
     *
     * @param Portfolio Manager_Id
     * @return all portfolios under the PM
     */
    
    public Iterable<Portfolio> getAllPortfoliosByPMId(Long PMId) {        
        Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Portfolio.class);
		criteria.add(Restrictions.eq("pm", PMId));
        List<Portfolio> portfolios = criteria.list();
        return portfolios;
    }

    /**
     * Persist the portfolio details.
     *
     * @param portfolio object
     */
    
    @Transactional
    public void persist(Portfolio portfolio) {
    	Session session = this.sessionFactory.getCurrentSession();
    	session.save(portfolio);
    }	


    /**
     * Find Portfolio by id.
     *
     * @param Portfolio id
     * @return portfolio
     */
    
    public Portfolio findById(long id) {
    	Session session = this.sessionFactory.getCurrentSession();
        Portfolio portfolio = (Portfolio) session.load(Portfolio.class, id);
        return portfolio;
    }



}
