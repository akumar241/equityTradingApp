package com.sapient.sadp.et.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.sadp.et.model.Block;
import com.sapient.sadp.pm.model.Order;
import com.sapient.sadp.utility.BlockQuery;
import com.sapient.sadp.utility.Constants;
import com.sapient.sadp.utility.Constants.Side;
import com.sapient.sadp.utility.Constants.Type;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockDAO.
 */
@Component
public class BlockDAO {
	
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}



    /**
     * Persist block.
     *
     * @param block
     *            the block
     */
    @Transactional
    public void persist(Block block) {
    	Session session = this.sessionFactory.getCurrentSession();
        session.persist(block);
    }

    /**
     * Gets the block.
     *
     * @param block
     *            the block
     * @return the block
     */
    @Transactional
    public void merge(Block block) {
    	Session session = this.sessionFactory.getCurrentSession();
        session.merge(block);
    }

    /**
     * Find by id.
     *
     * @param blockId
     *            the block id
     * @return the block
     */
    public Block findById(long blockId) {
    	Session session = this.sessionFactory.getCurrentSession();
        return (Block) session.load(Block.class, blockId);
    }

    /**
     * Delete block.
     *
     * @param blockId
     *            the block id
     */
    @Transactional
    public void remove(long blockId) {
        /*String q = BlockQuery.updateOrderBlockIdQuery;
       // Query query = entityManager.createQuery(q);
        query.setParameter(BlockQuery.blockId, blockId);
        query.executeUpdate();

        q = BlockQuery.deleteBlockQuery;
        //query = entityManager.createQuery(q);
        query.setParameter(BlockQuery.blockId, blockId);
        query.executeUpdate();*/
    }

    /**
     * Gets the blocks matching the order.
     *
     * @param order
     *            the order
     * @param traderId
     *            the trader id
     * @return the matching blocks
     */
    public Iterable<Block> getMatchingBlocks(Order order, Long traderId) {
        // Get all the matching blocks for the given order...
       /* try {
            String q = BlockQuery.getMatchingBlocksQuery;
            Query query = entityManager.createQuery(q, Block.class);
            query.setParameter(BlockQuery.symbol, order.getSecurity().getSecuritySymbol());
            query.setParameter(BlockQuery.side, order.getSide().toString());
            query.setParameter(BlockQuery.type, order.getType().toString());
            query.setParameter(BlockQuery.traderId, traderId);

            @SuppressWarnings("unchecked")
            List<Block> blocks = query.getResultList();
            return blocks;
        } catch (Exception e) {

        }*/
        return null;
    }

    /**
     * Gets the trader blocks.
     *
     * @param traderId
     *            the trader id
     * @return the trader blocks
     */

    @Transactional
    public Iterable<Block> getAvailableTraderBlocks(Long traderId) {
        String q = BlockQuery.getAvailableTraderBlocksQuery;
       // Query query = entityManager.createQuery(q, Block.class);
       /* query.setParameter(BlockQuery.traderId, traderId);
        @SuppressWarnings("unchecked")
		List<Block> blocks = query.getResultList();*/
        return null;
    }

    /**
     * Gets the executed trader blocks.
     *
     * @param traderId
     *            the trader id
     * @return the executed trader blocks
     */
    @Transactional
    public Iterable<Block> getExecutedTraderBlocks(Long traderId) {
        String q = BlockQuery.getExecutedTraderBlocksQuery;
       /* Query query = entityManager.createQuery(q, Block.class);
        query.setParameter(BlockQuery.traderId, traderId);
        @SuppressWarnings("unchecked")
		List<Block> blocks = query.getResultList();*/
        return null;
    }

    /**
     * Delete order from block.
     *
     * @param orderId
     *            the order id
     * @param block
     *            the block
     * @return the block
     */
    @Transactional
    public Block deleteOrderFromBlock(Long orderId, Block block) {
        String q = BlockQuery.deleteOrderFromBlockQuery;
        /*Query query = entityManager.createQuery(q);
        query.setParameter(BlockQuery.status, Constants.OrderStatus.UNASSIGNED);
        query.setParameter(BlockQuery.orderId, orderId);
        query.executeUpdate();
        entityManager.persist(block);*/
        return block;
    }

    /**
     * Filter available blocks.
     *
     * @param type
     *            the type
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(Type type, String symbol, Long traderId) {
        /*try {
            String q = BlockQuery.filterAvailableByTypeSymbol;
            Query query = null;//entityManager.createQuery(q);
            query.setParameter(BlockQuery.symbol, symbol);
            query.setParameter(BlockQuery.type, type.toString());
            query.setParameter(BlockQuery.traderId, traderId);
            @SuppressWarnings("unchecked")
			List<Block> blocks = query.getResultList();
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }

    /**
     * Filter available blocks.
     *
     * @param side
     *            the side
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(Side side, String symbol, Long traderId) {
        /*try {
            String q = BlockQuery.filterAvailableBySideSymbol;
            Query query = entityManager.createQuery(q);
            query.setParameter(BlockQuery.symbol, symbol);
            query.setParameter(BlockQuery.side, side);
            query.setParameter(BlockQuery.traderId, traderId);
            @SuppressWarnings("unchecked")
			List<Block> blocks = query.getResultList();
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }

    /**
     * Filter available blocks.
     *
     * @param side
     *            the side
     * @param type
     *            the type
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(Side side, Type type, String symbol, Long traderId) {
        /*try {
            String q = BlockQuery.filterAvailableBySideTypeSymbol;
            Query query = entityManager.createQuery(q);
            query.setParameter("side", side);
            query.setParameter(BlockQuery.symbol, symbol);
            query.setParameter(BlockQuery.type, type.toString());
            query.setParameter(BlockQuery.traderId, traderId);
            @SuppressWarnings("unchecked")
			List<Block> blocks = query.getResultList();
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }

    /**
     * Filter available blocks.
     *
     * @param side
     *            the side
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(Side side, Long traderId) {
        /*try {
            String q = BlockQuery.filterAvailableBySide;
            Query query = entityManager.createQuery(q);
            query.setParameter(BlockQuery.side, side);
            query.setParameter(BlockQuery.traderId, traderId);
            @SuppressWarnings("unchecked")
			List<Block> blocks = query.getResultList();
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }

    /**
     * Filter available blocks.
     *
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(String symbol, Long traderId) {
        /*try {
            String q = BlockQuery.filterAvailableBySymbol;
            Query query = entityManager.createQuery(q);
            query.setParameter(BlockQuery.symbol, symbol);
            query.setParameter(BlockQuery.traderId, traderId);
            @SuppressWarnings("unchecked")
			List<Block> blocks = query.getResultList();
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }

    /**
     * Filter available blocks.
     *
     * @param type
     *            the type
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(Type type, Long traderId) {
        /*try {
            String q = BlockQuery.filterAvailableByType;
            Query query = entityManager.createQuery(q);
            query.setParameter(BlockQuery.type, type.toString());
            query.setParameter(BlockQuery.traderId, traderId);
            System.out.println(query.toString());
            @SuppressWarnings("unchecked")
			List<Block> blocks = query.getResultList();
            System.out.println(type + " " + traderId);
            System.out.println(blocks);
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }
        */return null;
    }

    /**
     * Filter available blocks.
     *
     * @param side
     *            the side
     * @param type
     *            the type
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterAvailableBlocks(Side side, Type type, Long traderId) {
       /* try {
            String q = BlockQuery.filterAvailableBySideType;
            Query query = entityManager.createQuery(q);
            query.setParameter(BlockQuery.side, side);
            query.setParameter(BlockQuery.type, type.toString());
            query.setParameter(BlockQuery.traderId, traderId);
            @SuppressWarnings("unchecked")
			List<Block> blocks = query.getResultList();
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }

    public Iterable<Block> filterExecutedBlocks(Type type, String symbol, Long traderId) {
       /* try {
            String q = BlockQuery.filterExecutedByTypeSymbol;
            Query query = entityManager.createQuery(q);
            query.setParameter(BlockQuery.symbol, symbol);
            query.setParameter(BlockQuery.type, type.toString());
            query.setParameter(BlockQuery.traderId, traderId);
            @SuppressWarnings("unchecked")
            List<Block> blocks = query.getResultList();
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }

    /**
     * Filter available blocks.
     *
     * @param side
     *            the side
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterExecutedBlocks(Side side, String symbol, Long traderId) {
       /* try {
            String q = BlockQuery.filterExecutedBySideSymbol;
            Query query = entityManager.createQuery(q);
            query.setParameter(BlockQuery.symbol, symbol);
            query.setParameter(BlockQuery.side, side);
            query.setParameter(BlockQuery.traderId, traderId);
            @SuppressWarnings("unchecked")
            List<Block> blocks = query.getResultList();
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }

    /**
     * Filter available blocks.
     *
     * @param side
     *            the side
     * @param type
     *            the type
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterExecutedBlocks(Side side, Type type, String symbol, Long traderId) {
       /* try {
            String q = BlockQuery.filterExecutedBySideTypeSymbol;
            Query query = entityManager.createQuery(q);
            query.setParameter("side", side);
            query.setParameter(BlockQuery.symbol, symbol);
            query.setParameter(BlockQuery.type, type.toString());
            query.setParameter(BlockQuery.traderId, traderId);
            @SuppressWarnings("unchecked")
            List<Block> blocks = query.getResultList();
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }

    /**
     * Filter available blocks.
     *
     * @param side
     *            the side
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterExecutedBlocks(Side side, Long traderId) {
        /*try {
            String q = BlockQuery.filterExecutedBySide;
            Query query = entityManager.createQuery(q);
            query.setParameter(BlockQuery.side, side);
            query.setParameter(BlockQuery.traderId, traderId);
            @SuppressWarnings("unchecked")
            List<Block> blocks = query.getResultList();
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }

    /**
     * Filter available blocks.
     *
     * @param symbol
     *            the symbol
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterExecutedBlocks(String symbol, Long traderId) {
        /*try {
            String q = BlockQuery.filterExecutedBySymbol;
            Query query = entityManager.createQuery(q);
            query.setParameter(BlockQuery.symbol, symbol);
            query.setParameter(BlockQuery.traderId, traderId);
            @SuppressWarnings("unchecked")
            List<Block> blocks = query.getResultList();
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }

    /**
     * Filter available blocks.
     *
     * @param type
     *            the type
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterExecutedBlocks(Type type, Long traderId) {
       /* try {
            String q = BlockQuery.filterExecutedByType;
            Query query = entityManager.createQuery(q);
            query.setParameter(BlockQuery.type, type.toString());
            query.setParameter(BlockQuery.traderId, traderId);
            System.out.println(query.toString());
            @SuppressWarnings("unchecked")
            List<Block> blocks = query.getResultList();
            System.out.println(type + " " + traderId);
            System.out.println(blocks);
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }

    /**
     * Filter available blocks.
     *
     * @param side
     *            the side
     * @param type
     *            the type
     * @param traderId
     *            the trader id
     * @return the iterable
     */
    public Iterable<Block> filterExecutedBlocks(Side side, Type type, Long traderId) {
        /*try {
            String q = BlockQuery.filterExecutedBySideType;
            Query query = entityManager.createQuery(q);
            query.setParameter(BlockQuery.side, side);
            query.setParameter(BlockQuery.type, type.toString());
            query.setParameter(BlockQuery.traderId, traderId);
            @SuppressWarnings("unchecked")
            List<Block> blocks = query.getResultList();
            return blocks;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }
}
