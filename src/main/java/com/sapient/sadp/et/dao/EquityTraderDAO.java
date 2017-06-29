package com.sapient.sadp.et.dao;


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
import com.sapient.sadp.utility.EquityTraderQuery;

/**
 * The Class EquityTraderDAO.
 */
@Component
public class EquityTraderDAO {

	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Transactional
	public void persist(EquityTrader entity) {
		throw new UnsupportedOperationException();
	}

	@Transactional
	public void remove(long entityId) {
		throw new UnsupportedOperationException();
	}

	public EquityTrader findByTraderId(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (EquityTrader) session.load(EquityTrader.class, id);
	}

	@Transactional
	public EquityTrader findTraderByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(EquityTrader.class);
		criteria.add(Restrictions.eq("username",username));
		List<EquityTrader> equityTraders = criteria.list();
		if(equityTraders.isEmpty())
		return null;
		else
		return equityTraders.get(0);
	}

	@Transactional
	public Iterable<EquityTrader> findAllTraders() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(EquityTrader.class);
		List<EquityTrader> equityTraders = criteria.list();
		return equityTraders;
	}
}
