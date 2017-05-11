package kr.ac.hansung.maldives.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.maldives.web.model.User;
import kr.ac.hansung.maldives.web.service.AccountService;

@Controller
@RequestMapping("/point")
public class PointController {

	@Autowired
	AccountService accountService;
	
	@RequestMapping("/shop")
	public String poingShop(Model model){
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = accountService.findByEmail(username);
		int point = user.getPoint();
		
		model.addAttribute("point", point);
		
		return "point/pointShop";
	}
	
}
