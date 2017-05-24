package com.sapient.sadp.utility;

// TODO: Auto-generated Javadoc
/**
 * The Class Constants.
 */
public class Constants {

    /**
     * The Enum Side.
     */
    public enum Side {
        BUY, SELL
    };

    /**
     * The Enum BlockStatus.
     */
    public enum BlockStatus {
        /** The new. */
        NEW, /** The sent for execution. */
        SENT_FOR_EXECUTION, /** The partial. */
        PARTIAL, /** The expired. */
        EXPIRED, /** The completed. */
        COMPLETED
    };

    /**
     * The Enum Type.
     */
    public enum Type {
        LIMIT, MARKET, STOP
    };

    /**
     * The Enum OrderStatus.
     */
    public enum OrderStatus {
        NEW, OPEN, PARTIAL, CANCELLED, COMPLETED, UNASSIGNED, ASSIGNED
    };

    /**
     * The Enum Qualifiers.
     */
    public enum Qualifiers {
        DAYORDER, GTC
    };

    /**
     * The Enum AccountType.
     */
    public enum AccountType {
        CASH, MARGIN
    };

    /** The Constant EMAIL_PATTERN. */
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * The Constant PROPOSAL_LIMIT specifies the limit of the number of orders
     * that should be shown as a block while clubbing.
     */
    public static final int PROPOSAL_LIMIT = 1;

    /**
     * The Constant NUMBER_LENGTH specifies number of digits in a phone number
     */
    public static final int NUMBER_LENGTH = 10;

    /**
     * The Constant PASSWORD_LENGTH specifies the minimum length of the password
     */
    public static final int PASSWORD_LENGTH = 8;
}
