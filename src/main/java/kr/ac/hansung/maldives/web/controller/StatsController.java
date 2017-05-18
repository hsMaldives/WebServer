package kr.ac.hansung.maldives.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.maldives.web.service.FusionTableService;

@Controller
@RequestMapping("/stats")
public class StatsController {

	@Autowired
	private FusionTableService fusionTableService;
	
	@RequestMapping("/heatmap")
	public String heatMap(Model model){
		
		model.addAttribute("fusionTableId", fusionTableService.getFusionTableId());
		
		return "stats/heatmap";
	}
}
