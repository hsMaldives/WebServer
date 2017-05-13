package kr.ac.hansung.maldives.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.maldives.web.model.Store;
import kr.ac.hansung.maldives.web.service.StoreService;

@Controller
@RequestMapping("/recommend")
public class RecommendController {

	//@Autowired
	//private StoreService storeService;

	@RequestMapping
	public String recommendHome(Model model) {

		// List<Store> stores = storeService.getStores();

		List<Store> stores = new ArrayList<Store>();

		for (int i = 0; i < 5; i++) {
			Store store = new Store();
			store.setStoreIdx((long) i);
			store.setName(i + "번매장");
			store.setAddress(i + "어디있징");
			store.setCategory(null);
			store.setDsiId(i + "dsi");
			store.setLatitude(100.0 + i);
			store.setLongitude(200.0 + i);
			stores.add(store);
		}

		model.addAttribute("stores", stores);

		return "recommend/recommend";
	}
}
