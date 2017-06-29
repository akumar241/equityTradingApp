package com.sapient.sadp.pm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.sadp.exceptions.ValueMismatchException;
import com.sapient.sadp.pm.model.Order;
import com.sapient.sadp.pm.service.OrderService;
import com.sapient.sadp.utility.Constants.OrderStatus;

@Controller
public class ViewOrderController {
    
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/getAvailableOrdersById", method = RequestMethod.GET)
    public String getAvailableordersById(HttpSession session, Model model, @RequestParam(value = "orderId") Long orderId ,@RequestParam("page") String page)
            throws ValueMismatchException {

//        EquityTrader equityTrader = equityTraderManager.findTraderByUsername((String) session.getAttribute("username"));
        List<Order> orderList = new ArrayList<Order>();
        @SuppressWarnings("unused")
		List<Order> orderList2 = new ArrayList<Order>();
        Long pmId = (Long) session.getAttribute("pm_id");
        Iterable<Order> neworders = orderService.viewOrdersByStatus(OrderStatus.NEW, pmId);
        for (Order order : neworders)
            System.out.println(order);
        model.addAttribute("neworders", neworders);
        List<Order> openorders = orderService.viewOrdersByStatus(OrderStatus.OPEN, pmId);
        List<Order> assignedorders = orderService.viewOrdersByStatus(OrderStatus.ASSIGNED, pmId);
        List<Order> unassignedorders = orderService.viewOrdersByStatus(OrderStatus.UNASSIGNED, pmId);
        List<Order> allorders = new ArrayList<>();
        allorders.addAll(openorders);
        allorders.addAll(assignedorders);
        allorders.addAll(unassignedorders);
        model.addAttribute("openorders", allorders);
        Iterable<Order> partialorders = orderService.viewOrdersByStatus(OrderStatus.PARTIAL, pmId);
        model.addAttribute("partialorders", partialorders);
        Iterable<Order> completedorders = orderService.viewOrdersByStatus(OrderStatus.COMPLETED, pmId);
        model.addAttribute("completedorders", completedorders);
        Iterable<Order> cancelledorders = orderService.viewOrdersByStatus(OrderStatus.CANCELLED, pmId);
        model.addAttribute("cancelledorders", cancelledorders);
        if(page.equals("page1")) {
            for(Order o: neworders) {
                if(o.getOrderId().equals(orderId)) {             
                    orderList.add(o);
                }
            }
            ((List<Order>) neworders).clear();
            neworders = orderList;
            model.addAttribute("neworders",neworders);
        }
        else if(page.equals("page2")) {
            for(Order o: openorders) {
                if(o.getOrderId().equals(orderId)) {
                    orderList.add(o);
                }
            }
            openorders.clear();
            openorders = orderList;
            model.addAttribute("openorders",openorders);
        }
        else if(page.equals("page3")) {
            for(Order o: partialorders) {
                if(o.getOrderId().equals(orderId)) {
                    orderList.add(o);
                }
            }
            ((List<Order>) partialorders).clear();
            partialorders = orderList;
            model.addAttribute("partialorders",partialorders);
        }
        else if(page.equals("page4")) {
            for(Order o: completedorders) {
                if(o.getOrderId().equals(orderId)) {
                    orderList.add(o);
                }
            }
            ((List<Order>) completedorders).clear();
            completedorders = orderList;
            model.addAttribute("completedorders",completedorders);
        }
        else if(page.equals("page5")) {
            for(Order o: cancelledorders) {
                if(o.getOrderId().equals(orderId)) {
                    orderList.add(o);
                }
            }
            ((List<Order>) cancelledorders).clear();
            cancelledorders = orderList;
            model.addAttribute("cancelledorders",cancelledorders);
        }
        model.addAttribute("pageToActivate", page);
        return "ViewOrders";
    }
}
