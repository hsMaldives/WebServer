
package kr.ac.hansung.maldives.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.maldives.web.model.Store;
import kr.ac.hansung.maldives.web.service.StoreService;

@Controller
@RequestMapping("/location")
public class LocationController {

	@Autowired
	private StoreService storeService;

	@RequestMapping("/")
	public String location(Model model) {
		storeService.getStores();
		return "location/location";
	}

	@RequestMapping("/getStores")
	public @ResponseBody List<Store> getStoresByBound(double startX, double endX, double startY, double endY) {
		List<Store> stores = storeService.getStoresByBound(startX, endX, startY, endY);
		return stores;
	}

	@RequestMapping("/detail")
	public String locationDetail(@RequestParam("store_idx") Long store_idx, Model model) {
		Store store = storeService.getStoreById(store_idx);
		model.addAttribute("store", store);
		return "location/detail";
	}
}
