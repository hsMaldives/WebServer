package kr.ac.hansung.maldives.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.maldives.web.model.LocationAndRating;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Controller
@RequestMapping("/api/rating")
public class RatingContorller {
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public @ResponseBody LocationAndRating test(@RequestBody LocationAndRating locationAndRating, Principal principal){
		System.out.println(locationAndRating);
		log.info("[별점 등록] locationAndRating: {}, User: {}", locationAndRating, principal);
		
		return locationAndRating;
	}
}
