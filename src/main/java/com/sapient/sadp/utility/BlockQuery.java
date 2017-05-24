package com.sapient.sadp.utility;

// TODO: Auto-generated Javadoc
/**
 * The Class Query.
 */
public class BlockQuery {

    /** The Constant getMatchingBlocksQuery. */
    public static final String getMatchingBlocksQuery = "from Block where EQUITY_SYMBOL= :symbol and SIDE= :side and BLOCK_TYPE= :type and EQUITY_TRADER_ID= :traderid and STATUS='NEW' ";

    /** The Constant getTraderBlocksQuery. */
    public static final String getAvailableTraderBlocksQuery = "from Block where equity_trader_id= :traderid and STATUS = 'NEW' ";
    
    /** The Constant getExecutedTraderBlocksQuery. */
    public static final String getExecutedTraderBlocksQuery = "from Block where equity_trader_id= :traderid and STATUS <> 'NEW'";

    /** The Constant deleteOrderFromBlockQuery. */
    public static final String deleteOrderFromBlockQuery = "update Order set block_id = NULL, ORDER_STATUS = :status where orderId = :orderId";

    /** The Constant updateOrderBlockIdQuery. */
    public static final String updateOrderBlockIdQuery = "update Order set block_id = NULL , order_status='UNASSIGNED' where block_id = :blockId";
    
    /** The Constant deleteBlockQuery. */
    public static final String deleteBlockQuery = "delete from Block where block_id = :blockId";

    /** The Constant filterByTypeAndSymbol. */
    public static final String filterAvailableByTypeSymbol = "from Block where equity_trader_id= :traderid and symbol= :symbol and block_type= :type and STATUS='NEW' ";
    
    /** The Constant filterBySideSymbol. */
    public static final String filterAvailableBySideSymbol = "from Block where equity_trader_id= :traderid and side= :side and symbol= :symbol and STATUS='NEW' ";
    
    /** The Constant filterBySideTypeSymbol. */
    public static final String filterAvailableBySideTypeSymbol = "from Block where equity_trader_id= :traderid and symbol= :symbol and block_type= :type and side= :side and STATUS='NEW' ";
    
    /** The Constant filterBySide. */
    public static final String filterAvailableBySide = "from Block where equity_trader_id= :traderid and side= :side and STATUS='NEW' ";
    
    /** The Constant filterByType. */
    public static final String filterAvailableByType = "from Block where equity_trader_id= :traderid and block_type= :type and STATUS='NEW' ";
    
    /** The Constant filterBySymbol. */
    public static final String filterAvailableBySymbol = "from Block where equity_trader_id= :traderid and symbol= :symbol and STATUS='NEW' ";
    
    /** The Constant filterBySideType. */
    public static final String filterAvailableBySideType = "from Block where equity_trader_id= :traderid and block_type= :type and side= :side and STATUS='NEW' ";
    
    /** The Constant filterExecutedByTypeSymbol. */
    public static final String filterExecutedByTypeSymbol = "from Block where equity_trader_id= :traderid and symbol= :symbol and block_type= :type and STATUS <> 'NEW'";
    
    /** The Constant filterBySideSymbol. */
    public static final String filterExecutedBySideSymbol = "from Block where equity_trader_id= :traderid and side= :side and symbol= :symbol and STATUS <> 'NEW'";
    
    /** The Constant filterBySideTypeSymbol. */
    public static final String filterExecutedBySideTypeSymbol = "from Block where equity_trader_id= :traderid and symbol= :symbol and block_type= :type and side= :side and STATUS <> 'NEW'";
    
    /** The Constant filterBySide. */
    public static final String filterExecutedBySide = "from Block where equity_trader_id= :traderid and side= :side and STATUS <> 'NEW'";
    
    /** The Constant filterByType. */
    public static final String filterExecutedByType = "from Block where equity_trader_id= :traderid and block_type= :type and STATUS <> 'NEW'";
    
    /** The Constant filterBySymbol. */
    public static final String filterExecutedBySymbol = "from Block where equity_trader_id= :traderid and symbol= :symbol and STATUS <> 'NEW'";
    
    /** The Constant filterBySideType. */
    public static final String filterExecutedBySideType = "from Block where equity_trader_id= :traderid and block_type= :type and side= :side and STATUS <> 'NEW'";
    
    /** The Constant type. */
    public static final String type = "type";
    
    /** The Constant side. */
    public static final String side = "side";

    /** The Constant symbol. */
    public static final String symbol = "symbol";

    /** The Constant traderId. */
    public static final String traderId = "traderid";

    /** The Constant orderId. */
    public static final String orderId = "orderId";

    /** The Constant status. */
    public static final String status = "status";

    /** The Constant blockid. */
    public static final String blockId = "blockId";
    
    /** The Constant viewOrdersByStatusQuery. */
    public static final String viewOrdersByStatusQuery = "from Order where status = :status";
    
    /** The Constant cancelOrderQuery. */
    public static final String cancelOrderQuery = "update order set order_status = cancelled where order_id = :orderId";

}
