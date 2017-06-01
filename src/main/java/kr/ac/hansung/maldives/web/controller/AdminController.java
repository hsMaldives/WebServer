package kr.ac.hansung.maldives.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.maldives.web.scheduler.RecommendScheduler;
import kr.ac.hansung.maldives.web.service.RecommendService;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired(required = false)
	private RecommendScheduler recommendScheduler;
	
	@Autowired
	private RecommendService recommendService;
	
	@RequestMapping()
	public String index(Model model){
		
		model.addAttribute("criterionRate", recommendService.getCriterionRate());
		
		return "admin/index";
	}

	@RequestMapping("/createIbcf")
	public @ResponseBody Long startCreateIbcfModel(){
		if(recommendScheduler == null){
			return -1L;
		}
		
		return recommendScheduler.buildIBCFModel();
	}
	
	@RequestMapping("/createUbcf")
	public @ResponseBody Long startCreateUbcfModel(){
		if(recommendScheduler == null){
			return -1L;
		}
		
		return recommendScheduler.buildUBCFModel();
	}
	
	@RequestMapping(value="/changeCriterionRate", method = RequestMethod.PATCH)
	public @ResponseBody Float changeCriterionRate(float criterionRate){
		recommendService.setCriterionRate(criterionRate);
		
		return recommendService.getCriterionRate();
	}
}
