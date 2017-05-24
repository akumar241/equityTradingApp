package com.sapient.sadp.pm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.sadp.pm.dao.PortfolioDAO;
import com.sapient.sadp.pm.model.Portfolio;

// TODO: Auto-generated Javadoc
/**
 * The Class PortfolioManager.
 */
@Component
public class PortfolioService {

    /** The portfolio dao. */
    @Autowired
	private PortfolioDAO portfolioDao;

    /**
     * Adds the portfolio.
     *
     * @param  portfolio object
     */
    public void addPortfolio(Portfolio portfolio)
	{
		portfolioDao.persist(portfolio);
	}

	/**
	 * Gets the all portfolios under PM.
	 *
	 * @param id
	 * @return all portfolios by pm id
	 */
    @Transactional
	public Iterable<Portfolio> getAllPortfoliosByPMId(Long id)
	{
		return portfolioDao.getAllPortfoliosByPMId(id);
	}


	/**
	 * Gets the portfolio by id.
	 *
	 * @param  id
	 * @return portfolio
	 */
	public Portfolio getPortfolioById(Long id)
	{
	    return portfolioDao.findById(id);
	}
}
