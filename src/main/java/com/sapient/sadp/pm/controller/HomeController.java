package com.sapient.sadp.pm.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.sadp.et.model.EquityTrader;
import com.sapient.sadp.et.service.BlockManager;
import com.sapient.sadp.et.service.EquityTraderManager;
import com.sapient.sadp.exceptions.InvalidArgumentException;
import com.sapient.sadp.pm.model.Holdings;
import com.sapient.sadp.pm.model.Order;
import com.sapient.sadp.pm.model.PM;
import com.sapient.sadp.pm.model.Portfolio;
import com.sapient.sadp.pm.service.HoldingsManager;
import com.sapient.sadp.pm.service.OrderService;
import com.sapient.sadp.pm.service.PmLoginService;
import com.sapient.sadp.pm.service.PortfolioService;
import com.sapient.sadp.pm.service.SecurityManager;
import com.sapient.sadp.utility.Constants.OrderStatus;
import com.sapient.sadp.utility.Constants.Side;

@Controller
public class HomeController {



    @Autowired
    SecurityManager securityManager;
    @Autowired
    OrderService orderManager;
    @Autowired
    PortfolioService portfolioManager;
    @Autowired
    EquityTraderManager equityTraderManager;
    @Autowired
    public HoldingsManager holdingManager;
    @Autowired
    private PmLoginService pMManager;
    @Autowired
    private BlockManager blockManager;

    private boolean flag = true;

    @RequestMapping("/Home")
    public String Home(Model model, HttpSession session) {
        return "InternalSystem/ViewPositionsbyportfolio?id=0";
    }

    @RequestMapping(value = "/CreateOrdershome", method = RequestMethod.GET)
    public String OrderCreator(Model model, HttpSession session) {
        Order order = new Order();
        Iterable<Portfolio> portfolio = portfolioManager.getAllPortfoliosByPMId((Long) session.getAttribute("pm_id"));
        Iterable<EquityTrader> trader = equityTraderManager.findAllTraders();
        EquityTrader equityTrader = new EquityTrader();
        equityTrader.setName("NONE");
        order.setEquityTrader(equityTrader);
        model.addAttribute("createorder", order);
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("trader", trader);
        return "CreateOrders";
    }

    @RequestMapping(value = "/newPortfolio", method = RequestMethod.GET)
    public String newPortfolio(Model model, HttpSession session, @RequestParam("id") String portfolioName) {
        Order order = new Order();
        List<Portfolio> allportfolios = new ArrayList<>();
        if (!portfolioName.isEmpty()) {
            Portfolio portfolio = new Portfolio();
            portfolio.setPortfolioName(portfolioName);
            PM pm = pMManager.getpmbyID((Long) session.getAttribute("pm_id"));
            portfolio.setPm(pm);
            portfolioManager.addPortfolio(portfolio);
            allportfolios.add(portfolio);
        }
        EquityTrader equitytrader = new EquityTrader();
        equitytrader.setName("NONE");
        order.setEquityTrader(equitytrader);
        model.addAttribute("createorder", order);
        Iterable<Portfolio> portfolios = portfolioManager.getAllPortfoliosByPMId((Long) session.getAttribute("pm_id"));
        allportfolios.addAll((Collection<? extends Portfolio>) portfolios);
        model.addAttribute("portfolio", allportfolios);
        Iterable<EquityTrader> trader = equityTraderManager.findAllTraders();
        model.addAttribute("trader", trader);
        return "CreateOrders";
    }

    @RequestMapping(value = "/ViewOrdershome", method = RequestMethod.GET)
    public String View_Orders(Model model, @RequestParam("page") String page, HttpSession session) {
        Long pmId = (Long) session.getAttribute("pm_id");
        Iterable<Order> neworders = orderManager.viewOrdersByStatus(OrderStatus.NEW, pmId);
        for (Order order : neworders)
            System.out.println(order);
        model.addAttribute("neworders", neworders);
        List<Order> openorders = orderManager.viewOrdersByStatus(OrderStatus.OPEN, pmId);
        List<Order> assignedorders = orderManager.viewOrdersByStatus(OrderStatus.ASSIGNED, pmId);
        List<Order> unassignedorders = orderManager.viewOrdersByStatus(OrderStatus.UNASSIGNED, pmId);
        List<Order> allorders = new ArrayList<>();
        allorders.addAll(openorders);
        allorders.addAll(assignedorders);
        allorders.addAll(unassignedorders);
        model.addAttribute("openorders", allorders);
        Iterable<Order> partialorders = orderManager.viewOrdersByStatus(OrderStatus.PARTIAL, pmId);
        model.addAttribute("partialorders", partialorders);
        Iterable<Order> completedorders = orderManager.viewOrdersByStatus(OrderStatus.COMPLETED, pmId);
        model.addAttribute("completedorders", completedorders);
        Iterable<Order> cancelledorders = orderManager.viewOrdersByStatus(OrderStatus.CANCELLED, pmId);
        model.addAttribute("cancelledorders", cancelledorders);
        model.addAttribute("pageToActivate", page);

        Order order = new Order();
        model.addAttribute("order", order);

        if (!flag) {
            flag = true;
            String message = "Insufficient holdings available";
            model.addAttribute("message", message);
        }
        return "ViewOrders";
    }

    @RequestMapping(value = "/ViewOrdersAfterSendingToTrader", method = RequestMethod.GET)
    public String viewAndSendToTrader(Model model, @RequestParam("id") Long orderid, HttpSession session)
            throws InvalidArgumentException {
        Order order1 = orderManager.getOrderByOrderId(orderid);
        Long openQty;
        try {
            openQty = orderManager.findOpenQuantityOfOrders(order1.getPortfolio().getPortfolioId(),
                    order1.getSecurity().getSecuritySymbol());
        } catch (NoResultException e) {
            openQty = 0L;
        }
        Holdings holdings;
        try {
            holdings = holdingManager.getHoldingsByPortfolioAndSecurity(order1.getPortfolio().getPortfolioId(),
                    order1.getSecurity().getSecuritySymbol());
        } catch (NoResultException e) {
            holdings = new Holdings();
            holdings.setQuantity(0L);
        }

        if (((order1.getSide() == Side.SELL) && (holdings.getQuantity() >= (order1.getTotalQuantity() + openQty)))
                || (order1.getSide() == Side.BUY)) {
            flag = true;
            order1.setOrderStatus(OrderStatus.OPEN);
            orderManager.editOrder(order1);
            return "redirect:ViewOrdershome?page=page1";
        } else {
            flag = false;
            return "redirect:ViewOrdershome?page=page1";
        }
    }

    @RequestMapping(value = "/ViewOrdersAfterCancel", method = RequestMethod.GET)
    public String cancelOrder(Model model, @RequestParam("id") Long orderid, HttpSession session) {
        Long pmId = (Long) session.getAttribute("pm_id");
        Order order = orderManager.getOrderByOrderId(orderid);
        if (order.getOrderStatus() == OrderStatus.ASSIGNED)
            blockManager.deleteOrderFromBlock(orderid);
        orderManager.cancelOrder(orderid);
        Iterable<Order> neworders = orderManager.viewOrdersByStatus(OrderStatus.NEW, pmId);
        model.addAttribute("neworders", neworders);
        Iterable<Order> openorders = orderManager.viewOrdersByStatus(OrderStatus.OPEN, pmId);
        model.addAttribute("openorders", openorders);
        Iterable<Order> completedorders = orderManager.viewOrdersByStatus(OrderStatus.COMPLETED, pmId);
        model.addAttribute("completedorders", completedorders);
        Iterable<Order> cancelledorders = orderManager.viewOrdersByStatus(OrderStatus.CANCELLED, pmId);
        model.addAttribute("cancelledorders", cancelledorders);
        return "redirect:ViewOrdershome?page=page1";
    }

    @RequestMapping("/logout")
    public String Logout(Model model, HttpSession session) {
        return "logout";
    }
    
    @RequestMapping("/Createorderquantityerror")
    public String errorpage(Model model) {
        return "Createorderquantityerror";
    }

}
