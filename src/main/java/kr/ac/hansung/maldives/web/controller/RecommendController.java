package kr.ac.hansung.maldives.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.maldives.web.model.CustomUserDetails;
import kr.ac.hansung.maldives.web.property.WhereyouProperty;
import kr.ac.hansung.maldives.web.service.RecommendService;

@Controller
@RequestMapping("/recommend")
public class RecommendController {

	@Autowired
	private RecommendService recommendService;
	
	@Autowired
	private WhereyouProperty whereyouProperty;


	@RequestMapping
	public String recommendHome(Model model) {

		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		Long user_idx = userDetails.getUserIdx();
		
		//model.addAttribute("UB_stores", recommendService.getRecommendUBStore(user_idx));
		model.addAttribute("googleMapApiKey", whereyouProperty.getGoogleMapApiKey());
		model.addAttribute("stores", recommendService.getRecommendIBStore(user_idx));

		return "recommend/recommend";
	}
}
