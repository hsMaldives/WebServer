package kr.ac.hansung.maldives.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.maldives.web.model.CustomUserDetails;
import kr.ac.hansung.maldives.web.model.Store;
import kr.ac.hansung.maldives.web.service.RecommendService;
import kr.ac.hansung.maldives.web.service.StoreService;

@Controller
@RequestMapping("/recommend")
public class RecommendController {

	@Autowired
	private RecommendService recommendService;
	@Autowired
	private StoreService storeService;

	private List<Store> stores;// 훗날 제거

	@RequestMapping
	public String recommendHome(Model model) {

		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		Long user_idx = userDetails.getUserIdx();
		stores = recommendService.getRecommendStore(user_idx);
		//stores = storeService.getStores();
		model.addAttribute("stores", stores);

		return "recommend/recommend";
	}

	@RequestMapping("/detail/{storeIdx}")
	public String recmmendDetailPage(@PathVariable(value = "storeIdx") Long storeIdx, Model model) {

		Store store = storeService.getStoreById(storeIdx);
		double average = 0.0;

		

		model.addAttribute("store", store);
		model.addAttribute("average", average);

		return "recommend/recommendDetail";
	}
}
