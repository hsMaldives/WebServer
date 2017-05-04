package kr.ac.hansung.maldives.web.controller;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.maldives.web.model.Location;
import kr.ac.hansung.maldives.web.model.Store;
import kr.ac.hansung.maldives.web.model.StoreAndRating;
import kr.ac.hansung.maldives.web.service.StoreService;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@RequestMapping("/api/rating")
public class ApiContorller {
	
	@Autowired
	private StoreService storeService;
	
//	@RequestMapping(value="/test", method=RequestMethod.POST)
//	public LocationAndRating test(@RequestBody LocationAndRating locationAndRating, Principal principal){
//		System.out.println(locationAndRating);
//		log.info("[별점 등록] locationAndRating: {}, User: {}", locationAndRating, principal);
//		
//		return locationAndRating;
//	}
	
	
	@RequestMapping(value="/storeAndRatingInfo", method=RequestMethod.POST)
	public StoreAndRating ratingInfo(@RequestBody StoreAndRating storeAndRating, Principal principal){
		System.out.println(storeAndRating);
		log.info("[별점 등록] storeAndRating: {}, User: {}", storeAndRating, principal);
		
		
		//DB에 store별 별점정보를 쌓는다.
		//
		//
		//
		//
		
		return storeAndRating;
	}
	
	
	
	@RequestMapping(value="/onlyLocationInfo", method=RequestMethod.POST)	
	public Location onlyLocationInfo(@RequestBody Location location, Principal principal){
		System.out.println(location);
		log.info("[위치 정보] location: {}, location, User: {}", location, principal);
		
		
		//location정보를  미평가 데이터로 DB에 쌓는다.
		//
		//
		//
		//
		
		return location;
	}
	
	
	
	
	
	@RequestMapping(value="/locationInfo", method=RequestMethod.POST)
	@ResponseBody
	public List<Store> locationInfo(@RequestBody Location location){
		System.out.println(location);
		log.info("[위치 정보] location: {}", location);
		
		
		////////////가장 가까운 매장 정보
		final double LATI = location.getLati();
		final double LONGI = location.getLongi();
		final double FOR_RADIUS=0.0045; //반경 약 500m		//0.009; //반경 약 1000m		//0.0135;//반경 약 1500m
		
		List<Store> stores = storeService.getStoresByBound(location.getLati()-FOR_RADIUS, location.getLati()+FOR_RADIUS, location.getLongi()-FOR_RADIUS, location.getLongi()+FOR_RADIUS);
		
		class MyComparator implements Comparator<Store>{

			@Override
			public int compare(Store o1, Store o2) {								
				return (int) ((Math.pow(o1.getLatitude()-LATI,2)+Math.pow(o1.getLongitude()-LONGI, 2))- (Math.pow(o2.getLatitude()-LATI,2)+Math.pow(o2.getLongitude()-LONGI, 2)));			
			}
			
		}
		
		stores.sort(new MyComparator());
		
		return stores;
	}
	
	
	
	
	
	
	
	
}
