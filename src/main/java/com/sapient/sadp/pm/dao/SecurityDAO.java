package com.sapient.sadp.pm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.sadp.pm.model.Security;
import com.sapient.sadp.utility.OrderQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityDAO.
 */
@Component
public class SecurityDAO  {
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}


    /**
     *
     * @param security symbol
     * @return the security by symbol
     */
    
    public Security getSecurityBySymbol(String symbol) {
    	Session session = this.sessionFactory.getCurrentSession();	
    	return (Security) session.load(Security.class, symbol);
    }

    /**
     * Persist security ltp.
     *
     * @param  security
     */
    
    @Transactional
    public void persist(Security security) {
    	Session session = this.sessionFactory.getCurrentSession();	
        session.merge(security);

    }
    @SuppressWarnings("unchecked")
	@Transactional
    public Iterable<Security> searchForSimilarSymbols(String symbol)
    {
        List<Security> similarSymbols;
        Session session = this.sessionFactory.getCurrentSession();	
        Query query=session.createQuery(OrderQuery.similarSymbolLookupPart1+symbol+OrderQuery.similarSymbolLookupPart2);
        similarSymbols=query.list();
        return similarSymbols;
        
    }
}
