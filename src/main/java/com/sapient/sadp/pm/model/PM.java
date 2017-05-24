package com.sapient.sadp.pm.model;

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
@Table(name = "PORTFOLIO_MANAGER")
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "NAME", nullable = false, unique = true) ),
        @AttributeOverride(name = "contactNo", column = @Column(name = "CONTACTNO", nullable = false) ),
        @AttributeOverride(name = "email", column = @Column(name = "EMAIL", nullable = false) ),
        @AttributeOverride(name = "password", column = @Column(name = "PASSWORD", nullable = false) ) })
public class PM extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PORTFOLIO_MANAGER_ID")
    private Long Id;

    public Long getId() {
        return Id;
    }

    public PM() {
    }

    public PM(String name, String email, String password, String username, String contactNo)
            throws InvalidArgumentException {
        super(name, email, password, username, contactNo);
    }

    @Override
    public String toString() {
        return "PM [Id=" + Id + super.toString() + "]";
    }

    public void setId(Long id) {
        Id = id;
    }
    
    
    
}

