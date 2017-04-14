
package kr.ac.hansung.maldives.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.maldives.web.model.Store;
import kr.ac.hansung.maldives.web.service.StoreService;

@Controller
public class LocationController {

	@Autowired
	private StoreService storeService;
	
	@RequestMapping("/location")
	public String location(Model model){
		storeService.getStores();
		return "location/location";
	}
	
	@RequestMapping("/locationsss")
	public @ResponseBody List<Store> findByLL(int lim){
		List<Store> stores = storeService.getStoresBylim(lim);
		return stores;
	}
}
