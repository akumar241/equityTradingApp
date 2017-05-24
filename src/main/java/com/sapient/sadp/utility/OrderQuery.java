package com.sapient.sadp.utility;

//import com.sapient.java.internal.utility.Constants.OrderStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderQuery.
 */
public class OrderQuery {

    /** The Constant traderId. */
    public static final String traderId = "traderId";

    /** The Constant orderId. */
    public static final String orderId = "orderId";

    /** The Constant status. */
    public static final String status = "status";

    /** The Constant portfolioId. */
    public static final String portfolioId = "portfolioId";

    /** The Constant security. */
    public static final String security = "security";

    /** The Constant blockId. */
    public static final String blockId = "blockId";
    
    /** The Constant managerId. */
    public static final String managerId = "managerId";  
    
    /** The Constant status. */
    public static final String status1 = "OPEN";
    
    /** The Constant status. */
    public static final String status2 = "UNASSIGNED";

    /** The Constant getHoldingByPortfolioAndSecurity. */
    public static final String getHoldingByPortfolioAndSecurity = "from Holdings where PORTFOLIO_ID= :portfolioId and SECURITY_SYMBOL= :security";

    /** The Constant viewOrderQuery. */
    /*public static final String viewOrdersQuery = "from Order where ORDER_STATUS = :"+status+" and PORTFOLIO_MANAGER_ID= :"+managerId;*/

    /** The Constant viewOrderByTraderQuery. */
    public static final String viewOrdersByTrader = "from Order where EQUITY_TRADER_ID = :traderId and ORDER_STATUS= 'OPEN' or ORDER_STATUS= 'UNASSIGNED' ORDER BY ORDER_STATUS ASC";

    /** The Constant viewOrderByManagerQuery. */
    public static final String viewOrdersByManager = "from Order where PORTFOLIO_MANAGER_ID = :managerId and ORDER_STATUS = :status";
    
   /** The Constant getOrdersByBlockId. */
   public static final String getOrdersByBlockId = "from Order where BLOCK_ID = :blockId";
   
    /** The Constant sendToTraderQuery. */
    public static final String sendToTraderQuery = "update Order set TRADER_ID = :traderId , ORDER_STATUS = :status where ORDER_ID = :orderId";

    /** The Constant getPortfoliosByPMIdQuery. */
    public static final String getPortfoliosByPMIdQuery = "from Portfolio where PORTFOLIO_MANAGER_ID= :managerId";
    
    /** The Constant getHoldingsByPortfolioIdQuery. */
    public static final String getHoldingsByPortfolioIdQuery = "from Holdings where PORTFOLIO_ID= :portfolioId";
    
    public static final String getOpenQuantitiesofOrders ="select sum(ORDER_QUANTITY_TOTAL-ORDER_QUANTITY_EXECUTED) from Order where PORTFOLIO_ID= :portfolioId and SECURITY_SYMBOL= :security and ORDER_STATUS != 'NEW' and ORDER_STATUS != 'COMPLETED' and ORDER_STATUS !='CANCELLED' and ORDER_SIDE='SELL'";
    
    public static final String similarSymbolLookupPart1="from Security where security_symbol like '";
    public static final String similarSymbolLookupPart2="%'";
    
    public static final String getDayOrdersForCancellation ="from Order where ORDER_QUALIFIER = 'DAYORDER' and ORDER_STATUS != 'COMPLETED' and ORDER_STATUS !='CANCELLED' and ORDER_STATUS != 'NEW' and ORDER_STATUS !='OPEN'";

}
