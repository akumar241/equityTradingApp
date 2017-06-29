package com.sapient.sadp.et.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.sadp.et.dao.EquityTraderDAO;
import com.sapient.sadp.et.model.Block;
import com.sapient.sadp.et.model.EquityTrader;

// TODO: Auto-generated Javadoc
/**
 * The Class EquityTraderManager.
 */
@Component
public class EquityTraderManager {

    /** The equity trader dao. */
	@Autowired
    private EquityTraderDAO equityTraderDAO;

    /** The block manager. */
	@Autowired
    private BlockManager blockManager;

    /**
     * Send for execution.
     *
     * @param blockIds
     *            the block ids of blocks which are to be executed
     */
    public void sendForExecution(Iterable<Long> blockIds) {
        List<Block> blocks = new ArrayList<>();
        for (Long id : blockIds) {
            Block newBlock = blockManager.getBlockDetails(id);
            try {

                // blockExecutor.sendForExecution(newBlock);
                blocks.add(newBlock);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Gets the trader.
     *
     * @param traderId
     *            the trader id
     * @return the trader details i.e. <code>EquityTrader</code> POJO
     */
    @Transactional
    public EquityTrader findByTraderId(long traderId) {
        return equityTraderDAO.findByTraderId(traderId);
    }

    /**
     * Find traders.
     *
     * @return the iterable
     */
    public Iterable<EquityTrader> findAllTraders() {
        return equityTraderDAO.findAllTraders();
    }

    /**
     * Find trader by username.
     *
     * @param username
     *            the username
     * @return the equity trader
     */
    public EquityTrader findTraderByUsername(String username) {
        return equityTraderDAO.findTraderByUsername(username);
    }
}
