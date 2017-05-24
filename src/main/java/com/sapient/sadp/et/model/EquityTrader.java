package com.sapient.sadp.et.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sapient.sadp.exceptions.InvalidArgumentException;
import com.sapient.sadp.utility.User;

@Entity
@Table(name = "EQUITY_TRADER")
@AttributeOverrides({ @AttributeOverride(name = "name", column = @Column(name = "TRADER_NAME", nullable = false) ),
        @AttributeOverride(name = "contactNo", column = @Column(name = "TRADER_CONTACT_NUMBER", nullable = false) ),
        @AttributeOverride(name = "email", column = @Column(name = "TRADER_EMAIL", nullable = false) ),
        @AttributeOverride(name = "password", column = @Column(name = "PASSWORD", nullable = false) ) })
public class EquityTrader extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EQUITY_TRADER_ID")
    private Long id;

    public EquityTrader() {
    }

    public EquityTrader(String name, String email, String password, String username, String contactNo)
            throws InvalidArgumentException {
        super(name, email, password, username, contactNo);
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "EquityTrader [id=" + id + ", " + super.toString();
    }
    
    

}
