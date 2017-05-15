package kr.ac.hansung.maldives.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.maldives.web.model.Store;
import kr.ac.hansung.maldives.web.service.StoreService;

@Controller
@RequestMapping("/recommend")
public class RecommendController {

	@Autowired
	private StoreService storeService;

	private List<Store> stores;// 훗날 제거

	@RequestMapping
	public String recommendHome(Model model) {

		// List<Store> stores = storeService.getStores();
		stores = new ArrayList<Store>(); // 훗날 제거

		// 훗날 제거
		for (int i = 0; i < 5; i++) {
			Store store = new Store();
			store.setStoreIdx((long) i);
			store.setName(i + "번매장");
			store.setAddress(i + "어디있징");
			store.setCategory(null);
			store.setDsiId(i + "dsi");
			store.setLatitude(100.0 + i);
			store.setLongitude(200.0 + i);
			store.setPhone(i + "phoneNUM");
			stores.add(store);
		}
		// end 훗날제거

		model.addAttribute("stores", stores);

		return "recommend/recommend";
	}

	@RequestMapping("/detail/{storeIdx}")
	public String recmmendDetailPage(@PathVariable(value = "storeIdx") Long storeIdx, Model model) {

		//Store store = storeService.getStoreById(storeIdx);		
		double average = 0.0;
		
		int i = storeIdx.intValue(); //제거
		Store store = stores.get(i);	//제거

		model.addAttribute("store", store);
		model.addAttribute("average", average);

		return "recommend/recommendDetail";
	}
}
