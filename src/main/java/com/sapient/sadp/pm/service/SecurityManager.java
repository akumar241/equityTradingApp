package com.sapient.sadp.pm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.sadp.pm.dao.SecurityDAO;
import com.sapient.sadp.pm.model.Security;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityManager.
 */
@Component
public class SecurityManager {
    
    /** The security dao. */
    @Autowired
    private SecurityDAO securityDao;

    /**
     * Gets the security by symbol.
     *
     * @param symbol the symbol
     * @return the security by symbol
     * 
     */
    @Transactional
    public Security getSecurityBySymbol(String symbol)
    {
        return securityDao.getSecurityBySymbol(symbol);
    }

    /**
     * Update security ltp.
     *
     * @param security the security
     */
    public void updateSecurityLTP(Security security)
    {
        securityDao.persist(security);
    }
    
    @Transactional
    public Iterable<Security> symbolLookup(String symbol)
    {
        return securityDao.searchForSimilarSymbols(symbol);
    }

}
