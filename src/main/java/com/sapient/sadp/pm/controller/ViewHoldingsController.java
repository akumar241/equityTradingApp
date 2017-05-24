package com.sapient.sadp.pm.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.sadp.pm.model.Holdings;
import com.sapient.sadp.pm.model.Order;
import com.sapient.sadp.pm.model.Portfolio;

@Controller
public class ViewHoldingsController {
    /*@Autowired
    public PortfolioManager pm;

    @Autowired
    public HoldingsManager holdingManager;
    @Autowired
    SecurityTimer securityTimer;
*/

    @RequestMapping(value = "/ViewPositionsbyportfolio", method = RequestMethod.GET)
    public String PositionViewbyPortfolio(@RequestParam("id") Long id, Model model, HttpSession session) {
       // securityTimer.cancelGTD();
        Order order = new Order();
        Portfolio portfolio1 = new Portfolio();
        // Database was not generating values so it is just a hack.
        portfolio1.setPortfolioId(0L);
        order.setPortfolio(portfolio1);
        Iterable<Portfolio> portfolioList = Collections.EMPTY_LIST;
        //pm.getAllPortfoliosByPMId((Long) session.getAttribute("pm_id"));
        model.addAttribute("order", order);
        model.addAttribute("portfolio", portfolioList);

        if (id == 0) {
            List<Holdings> holdingsListsforallportfolio = new ArrayList<Holdings>();
            for (Portfolio portfolio : portfolioList) {
                Iterable<Holdings> holdingList = Collections.EMPTY_LIST;
                //holdingManager.getHoldingsByPortfolio(portfolio.getPortfolioId());
                for (Holdings holding : holdingList) {
                    holdingsListsforallportfolio.add(holding);
                }
            }
            model.addAttribute("holdingsbyportfolio", holdingsListsforallportfolio);
            return "ViewPositions";
        }

        else {
            Iterable<Holdings> holding = Collections.EMPTY_LIST;//holdingManager.getHoldingsByPortfolio(id);
            model.addAttribute("holdingsbyportfolio", holding);
            return "ViewPositions";
        }
    }

    @RequestMapping(value = "/ViewPositionsbyportfolio", method = RequestMethod.POST)
    public String getpositions(HttpSession session, Model model) {
       // securityTimer.cancelGTD();
        @SuppressWarnings("unused")
		String username = (String) session.getAttribute("username");
        return PositionViewbyPortfolio(0L, model, session);
    }
}
