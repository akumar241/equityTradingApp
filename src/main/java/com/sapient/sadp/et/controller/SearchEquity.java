package com.sapient.sadp.et.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.sadp.pm.model.Security;
import com.sapient.sadp.pm.service.SecurityManager;

@Controller
public class SearchEquity {
	@Autowired
	private SecurityManager securityManager;

	@RequestMapping(value = "/symbol_lookup2", method = RequestMethod.GET)
	public void searchUser(HttpServletResponse response, @RequestParam Map<String, String> params) {
		String jsonResponse = null;
		List<Security> securities = (List<Security>) securityManager.symbolLookup(params.get("term").toUpperCase());
		List<String> symbols = new ArrayList<>();
		int i = 0;
		jsonResponse = "[";
		for (Security security : securities) {
			System.out.println(security);
			symbols.add(security.getSecuritySymbol());
			// jsonResponse +=
			// "{\"name\":\""+symbols.get(i)+"\",\"value\":\""+symbols.get(i++)+"}";
			jsonResponse += "{\"ltp\":" + security.getLastTradedPrice() + ",\"name\":\"" + security.getSecurityName()
					+ "\",\"value\":\"" + security.getSecuritySymbol() + "\",\"label\":\""
					+ security.getSecuritySymbol() + "\"}";
			i++;
			if (i < securities.size())
				jsonResponse += ",";
		}
		jsonResponse += "]";
		System.out.println(jsonResponse);
		response.setContentType("application/json");
		try {
			response.getOutputStream().print(jsonResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
