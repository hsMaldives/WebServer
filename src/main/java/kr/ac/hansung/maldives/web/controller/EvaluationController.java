package kr.ac.hansung.maldives.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.hansung.maldives.web.model.CustomUserDetails;
import kr.ac.hansung.maldives.web.model.Evaluation;
import kr.ac.hansung.maldives.web.model.Position;
import kr.ac.hansung.maldives.web.service.EvaluationService;

@RequestMapping("/evaluation")
@Controller
public class EvaluationController {

	@Autowired
	private EvaluationService evaluationService;
	
	@RequestMapping(value="/historys", method=RequestMethod.GET)
	public String getHistoryList(Model model){
		CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Evaluation> evaluations = evaluationService.findByUser_idxAndNotEmptyEvaluation(userDetails.getUser_idx());
		
		model.addAttribute("evaluations", evaluations);
		
		return "evaluation/historys";
	}
	
	@RequestMapping(value="/miss-evaluations", method=RequestMethod.GET)
	public String getMissEvaluationList(Model model){
		CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Position> positions = evaluationService.findByUser_idxAndEmptyEvaluation(userDetails.getUser_idx());
		
		model.addAttribute("positions", positions);
		
		return "evaluation/missEvaluations";
	}
	
	@RequestMapping(value="/post-evaluation", method=RequestMethod.GET)
	public String postEvaluationForm(@RequestParam(required=false) Evaluation evaluation, Long position_idx, Model model){
		CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Position position = evaluationService.positionFindByOne(position_idx);
		
		if(evaluation == null){
			evaluation = new Evaluation();
		}
		
		if(position == null 
				|| ! position.getUser().getUser_idx().equals(userDetails.getUser_idx())
				|| position.getEvaluation() != null){
			return "redirect:/evaluation/miss-evaluations";
		}
		
		evaluation.setPosition_idx(position.getPosition_idx());
		
		//TODO 카테고리별 평가 항목 설정이 필요
		
		model.addAttribute("position", position);
		model.addAttribute("evaluation", evaluation);
		
		return "evaluation/postEvaluationForm";
	}
	
	@RequestMapping(value="/post-evaluation", method=RequestMethod.POST)
	public String postEvaluationPost(@Valid @ModelAttribute("evaluation") Evaluation evaluation, 
									BindingResult result, Model model){
		CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Position position = evaluationService.positionFindByOne(evaluation.getPosition_idx());
		
		if (result.hasErrors()) {
			return postEvaluationForm(evaluation, evaluation.getPosition_idx(), model);
		}
		
		if(position == null 
				|| ! position.getUser().getUser_idx().equals(userDetails.getUser_idx())
				|| position.getEvaluation() != null){
			return "redirect:/evaluation/miss-evaluations";
		}
		
		evaluationService.savePostEvaluation(evaluation);

		return "redirect:/evaluation/miss-evaluations";
	}
	
}
