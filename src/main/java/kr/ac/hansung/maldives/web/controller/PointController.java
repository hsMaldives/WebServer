package kr.ac.hansung.maldives.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/point")
public class PointController {

	@RequestMapping("/shop")
	public String poingShop(Model model){
		
		return "point/pointShop";
	}
	
}
