package com.sapient.sadp.pm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sapient.sadp.et.model.EquityTrader;
import com.sapient.sadp.et.service.EquityTraderManager;
import com.sapient.sadp.pm.model.Order;
import com.sapient.sadp.pm.model.PM;
import com.sapient.sadp.pm.model.Portfolio;
import com.sapient.sadp.pm.model.Security;
import com.sapient.sadp.pm.service.OrderService;
import com.sapient.sadp.pm.service.PmLoginService;
import com.sapient.sadp.pm.service.PortfolioService;
import com.sapient.sadp.pm.service.SecurityManager;
import com.sapient.sadp.utility.Constants.AccountType;
import com.sapient.sadp.utility.Constants.OrderStatus;
import com.sapient.sadp.utility.Constants.Qualifiers;
import com.sapient.sadp.utility.Constants.Side;
import com.sapient.sadp.utility.Constants.Type;


@Controller
public class CreateOrderController{

    @Autowired
    private OrderService ordermanager;
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    EquityTraderManager equitytradermanager;
    @Autowired
    private SecurityManager securityManager;
    @Autowired
    private PmLoginService pmLoginService;
    

    
    @RequestMapping(value="/CreateOrder", method=RequestMethod.POST)
    public String CreateOrder(@ModelAttribute("security") String Securitysymbol ,@ModelAttribute("equityTrader.id")String traderId, @ModelAttribute("portfolio.portfolioId")String portfolioId,@ModelAttribute("side") Side side,@ModelAttribute("type")Type type,@ModelAttribute("qualifiers")Qualifiers qualifier,@ModelAttribute("accountType")AccountType accounttype,@ModelAttribute("totalQuantity")Long quantity,@ModelAttribute("limitPrice")String limitprice,@ModelAttribute("stopPrice")String stopprice,HttpSession session) {
        
        if(quantity<=0) {
            return "redirect:Createorderquantityerror";
        }
        Security security =securityManager.getSecurityBySymbol(Securitysymbol);
        Portfolio portfolio = portfolioService.getPortfolioById(Long.parseLong(portfolioId));
        Order order = new Order();        
        order.setOrderStatus(OrderStatus.NEW);
        order.setSide(side);
        order.setType(type);
        order.setQualifiers(qualifier);
        order.setAccountType(accounttype);
        order.setTotalQuantity(quantity);
        if(order.getType()==Type.LIMIT)
            order.setLimitPrice(Double.parseDouble(limitprice));
        if(order.getType()==Type.STOP)
            order.setStopPrice(Double.parseDouble(stopprice));
        PM pm = pmLoginService.getpmbyID((Long) session.getAttribute("pm_id"));
        portfolio.setPm(pm);
        order.setPortfolio(portfolio);
        order.setSecurity(security);
        order.setPmid(pm);
        if(!traderId.isEmpty())
        {
            EquityTrader equityTrader = equitytradermanager.findByTraderId(Long.parseLong(traderId));
            order.setEquityTrader(equityTrader);
        }
        System.out.println(order);
        ordermanager.createOrder(order);
        System.out.println(order.toString());
        return "redirect:ViewOrdershome?page=page1";
    }

}
