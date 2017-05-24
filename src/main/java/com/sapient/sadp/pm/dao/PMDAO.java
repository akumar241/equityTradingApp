package com.sapient.sadp.pm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sapient.sadp.pm.model.PM;
import com.sapient.sadp.utility.PmQuery;
import com.sapient.sadp.utility.User;

/**
 * The Class PMDAO.
 */
@Component
public class PMDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PMDAO.class);
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}


    /**
     * Find PM by PMid
     *
     * @param PMid the pmid
     * @return the pm
     */
    
    public PM findById(long PMid) {
    	Session session = this.sessionFactory.getCurrentSession();		
		PM p = (PM) session.load(PM.class, PMid);
		logger.info("PM loaded successfully, Person details="+p);
		return p;
    }

    /**
     * Find pm by username
     *
     * @param username the username
     * @return the user
     */
    
    public User findPm(String userName)
	{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PM.class);
		criteria.add(Restrictions.eq("username", userName));
		List<PM> pmList = criteria.list();
		if (!CollectionUtils.isEmpty(pmList)) {
			PM pm = pmList.get(0);
			if (pm.getUsername().equals(userName))
				return pm;
		}

		return null;
    }

    /**
     * Find PM by pmusername.
     *
     * @param pmusername the pmusername
     * @return the pm
     */
    
    public PM findByUsername(String pmusername) {
    	Session session = this.sessionFactory.getCurrentSession();
        String query = PmQuery.GET_PM;
        Query q = session.createQuery(query);
        q.setParameter("username", pmusername);
        PM pm = (PM) q.list().get(0);
        return pm;
    }

}
