package com.sapient.sadp.pm.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.sadp.pm.dao.HoldingsDAO;
import com.sapient.sadp.pm.model.Holdings;
import com.sapient.sadp.pm.model.Order;

// TODO: Auto-generated Javadoc

/**
 * The Class HoldingsManager.
 */
@Component
public class HoldingsManager {

    /** The holdings dao. */
    @Autowired
    private HoldingsDAO holdingsDao;

    /**
     * Adds the holding.
     *
     * @param holding
     *            the holding
     */
    public void addHolding(Holdings holding) {
        holdingsDao.persist(holding);
    }

    /**
     * Merges the holding.
     *
     * @param holding
     *            the holding
     */
    public void merge(Holdings holding) {
        holdingsDao.merge(holding);
    }

    /**
     * Removes the holdings.
     *
     * @param id
     *            the id
     */
    public void removeHoldings(Long id) {
        holdingsDao.remove(id);
    }

    /**
     * Gets the holdings by portfolio.
     *
     * @param id
     *            the id
     * @return the holdings by portfolio
     */

    public Iterable<Holdings> getHoldingsByPortfolio(Long portfolioId) {
        return holdingsDao.getHoldingsByPortfolio(portfolioId);
    }

    /**
     * Gets the holding.
     *
     * @param portfolio
     *            the portfolio
     * @param security
     *            the security
     * @return the holding
     */

    public Holdings getHoldingsByPortfolioAndSecurity(Long portfolioId, String securitySymbol) {
        return holdingsDao.getHoldingsByPortfolioAndSecurity(portfolioId, securitySymbol);

    }

    /**
     * Calculate average price for buy.
     *
     * @param order
     *            the order
     * @param holding
     *            the holding
     * @return the double
     */

    public double calculateAveragePriceForBuy(Order order, Holdings holding) {
        double avgPrice = (holding.getQuantity() * holding.getAveragePrice()
                + order.getExecutedQuantity() * order.getExecutedPrice())
                / (holding.getQuantity() + order.getExecutedQuantity());
        return avgPrice;

    }

    /**
     * Calculate average price for sell.
     *
     * @param order
     *            the order
     * @param holding
     *            the holding
     * @return the double
     */
    @Transactional
    public double calculateAveragePriceForSell(Order order, Holdings holding) {
        double avgPrice = (holding.getQuantity() * holding.getAveragePrice()
                - order.getExecutedQuantity() * order.getExecutedPrice())
                / (holding.getQuantity() - order.getExecutedQuantity());
        ;
        return avgPrice;

    }

}
