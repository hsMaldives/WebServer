package kr.ac.hansung.maldives.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.maldives.web.scheduler.RecommendScheduler;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired(required = false)
	private RecommendScheduler recommendScheduler;
	
	@RequestMapping("/")
	public String index(Model model){
		
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
}
