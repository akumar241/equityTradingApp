package com.sapient.sadp.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sapient.sadp.et.model.Block;
import com.sapient.sadp.exceptions.ValueMismatchException;
import com.sapient.sadp.pm.model.Order;
import com.sapient.sadp.utility.Constants.Side;
import com.sapient.sadp.utility.Constants.Type;

/**
 * The Class BlocksProposer takes a list of orders and returns a list of blocks
 * that can be created from the list of orders
 */
@Service
public class BlocksProposer {

    /**
     * Propose blocks from a list of orders
     *
     * @param orders
     *            the orders form which proposal will be created
     * @return the iterable of blocks
     * @throws ValueMismatchException
     */
    public Iterable<Block> proposeBlocks(Iterable<Order> orders) throws ValueMismatchException {
        // map from a 3-tuple to list of orders
        Map<BlockFilter<Side, String, Type>, List<Order>> mapper = new HashMap<>();

        for (Order order : orders) {
            // 3-tuple of side, symbol and type
        	BlockFilter<Side, String, Type> tuple = new BlockFilter<Side, String, Type>(order.getSide(),
                    order.getSecurity().getSecuritySymbol(), order.getType());

            // create list for new tuple
            if (!mapper.containsKey(tuple)) {
                mapper.put(tuple, new ArrayList<Order>());
            }

            // get list and add new order to it and then put it back
            List<Order> moreOrders = mapper.get(tuple);
            moreOrders.add(order);
            mapper.put(tuple, moreOrders);
        }
        return proposedBlocks(mapper);
    }

    // helper just to avoid cluttering the proposeBlocks method
    private Iterable<Block> proposedBlocks(Map<BlockFilter<Side, String, Type>, List<Order>> map)
            throws ValueMismatchException {
        List<Block> proposal = new ArrayList<>();
        for (List<Order> list : map.values()) {
            if (list.size() > Constants.PROPOSAL_LIMIT) {
                Block block = new Block();
                for (Order order : list){
                	block.addOrder(order);
                }
                proposal.add(block);
            }
        }
        return proposal;
    }
}
