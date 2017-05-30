package kr.ac.hansung.maldives.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.maldives.web.scheduler.RecommendScheduler;

@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired(required = false)
	private RecommendScheduler recommendScheduler;

	@RequestMapping("/createIbcf")
	public @ResponseBody Boolean startCreateIbcfModel(){
		if(recommendScheduler == null){
			return false;
		}
		
		recommendScheduler.buildIBCFModel();
		return true;
	}
	
	@RequestMapping("/createUbcf")
	public @ResponseBody Boolean startCreateUbcfModel(){
		if(recommendScheduler == null){
			return false;
		}
		
		recommendScheduler.buildUBCFModel();
		return true;
	}
}
