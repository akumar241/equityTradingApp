package com.sapient.sadp.utility;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.sapient.sadp.exceptions.InvalidArgumentException;

@MappedSuperclass
public abstract class User {
    private String name;
    private String email;
    private String password;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    private String contactNo;

    public User() {}

    public User(String name, String email, String password, String username, String contactNo)
            throws InvalidArgumentException {
        if (name == null)
            throw new NullPointerException("Name of the user can't be null");
        if (email == null)
            throw new NullPointerException();
        if (password == null)
            throw new NullPointerException("Password cannot be null");
        if (username == null)
            throw new NullPointerException("Username shoul not be null");

        if (contactNo == null)
            throw new NullPointerException("Contact number is missing");
        if (contactNo.length() != Constants.NUMBER_LENGTH)
            throw new InvalidArgumentException("Number is Invalid");
        if (password.length() < Constants.PASSWORD_LENGTH)
            throw new InvalidArgumentException("Passord should be atleast 8 characters");
        // Checking for invalid patterns
        if (!Pattern.compile(Constants.EMAIL_PATTERN).matcher(email).matches())
            throw new InvalidArgumentException("The given pattern is not correct");

        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.contactNo = contactNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("Name of the user can't be null");
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidArgumentException {
        if (email == null)
            throw new NullPointerException();

        // Checking for invalid patterns
        if (!Pattern.compile(Constants.EMAIL_PATTERN).matcher(email).matches())
            throw new InvalidArgumentException("The given pattern is not correct");

        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws InvalidArgumentException {
        if (password == null)
            throw new NullPointerException("Password cannot be null");

        if (password.length() < Constants.PASSWORD_LENGTH)
            throw new InvalidArgumentException("Passord should be atleast 8 characters");
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null)
            throw new NullPointerException("Username shoul not be null");
        this.username = username;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) throws InvalidArgumentException {
        if (contactNo == null)
            throw new NullPointerException("Contact number is missing");
        if (contactNo.length() != Constants.NUMBER_LENGTH)
            throw new InvalidArgumentException("Number is Invalid");
        Long.parseLong(contactNo);
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "name=" + name + ", email=" + email + ", password=" + password + ", username=" + username
                + ", contactNo=" + contactNo + "]";
    }

}
