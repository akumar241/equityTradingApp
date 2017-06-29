package com.sapient.sadp.pm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.sadp.pm.dao.PMDAO;
import com.sapient.sadp.pm.model.PM;
import com.sapient.sadp.utility.User;


/**
 * The Class PmLoginManager.
 */
@Component
public class PmLoginService {
    
    /** The pm dao. */
    @Autowired
    private PMDAO pmDao; 
    
    /** The pm. */
    private User pm;

    /**
     * Validate pm.
     *
     * @param username the username
     * @return true, if successful
     */
    @Transactional
    public boolean validatePm(String username) {
        this.pm = pmDao.findPm(username);
        if (this.pm != null)
            return true;
        return false;
    }

    /**
     * Validate password.
     *
     * @param password the password
     * @return true, if successful
     */
    @Transactional
    public boolean validatePassword(String password) {
        if (this.pm.getPassword().equals(password))
            return true;
        return false;
    }

    /**
     * Gets the pmid.
     *
     * @param username the username
     * @return the pmid
     */
    @Transactional
    public Object getpmid(String username) {
        // TODO Auto-generated method stub
        return pmDao.findPm(username);
    }
    @Transactional
    public PM getpmbyusername(String username) {
        // TODO Auto-generated method stub
        return pmDao.findByUsername(username);
    }

    @Transactional
	public PM getpmbyID(Long pmId) {
		// TODO Auto-generated method stub
		return pmDao.findById(pmId);
	}
    
}
