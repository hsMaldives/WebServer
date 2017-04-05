package kr.ac.hansung.maldives.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@Autowired
	private Facebook facebook;

	@Autowired
	private ConnectionRepository connectionRepository;

//	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
//	public String login() {
//		return "user/login";
//	}

//	@RequestMapping(value = "/")
//	public String helloFacebook(Model model) {
//		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
//			return "redirect:/connect/facebook";
//		}
//
//		model.addAttribute("facebookProfile", facebook.fetchObject("me", User.class, new String[] { "id", "email",  "first_name", "last_name" }));
//		PagedList<Post> feed = facebook.feedOperations().getFeed();
//		model.addAttribute("feed", feed);
//		
//		return "hello";
//	}
}
