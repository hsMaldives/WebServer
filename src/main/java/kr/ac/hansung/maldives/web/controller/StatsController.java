package kr.ac.hansung.maldives.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.maldives.web.model.Category;
import kr.ac.hansung.maldives.web.model.Job;
import kr.ac.hansung.maldives.web.service.CategoryService;
import kr.ac.hansung.maldives.web.service.FusionTableService;
import kr.ac.hansung.maldives.web.service.JobService;

@Controller
@RequestMapping("/stats")
public class StatsController {
	
	@Autowired
	private JobService jobServce;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FusionTableService fusionTableService;
	
	@RequestMapping("/heatmap")
	public String heatMap(Model model){
		List<Category> categorys = categoryService.findByDepthAndCategoryCodeStartingWithOrderByCategoryCode(1, "00");
		List<Job> jobs = jobServce.findAll();
		
		model.addAttribute("fusionTableId", fusionTableService.getFusionTableId());
		model.addAttribute("categorys", categorys);
		model.addAttribute("jobs", jobs);
		
		return "stats/heatmap";
	}
}
