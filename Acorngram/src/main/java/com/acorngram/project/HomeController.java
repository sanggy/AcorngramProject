package com.acorngram.project;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping("/home.do")
	public String homepage() {
		System.out.println("여기는 들어 오나?");
		return "home";
	}
	
	
	
}
