package com.example.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.dto.NewDTO;
import com.example.service.INewService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
	@Autowired
	private INewService newService;

	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		NewDTO newDTO = new NewDTO();
		newDTO.setPage(page);
		newDTO.setLimit(limit);
		newDTO.setListResult(newService.findAll(page, limit));
		newDTO.setTotalItem(newService.totalItem());
		newDTO.setTotalPage((int) Math.ceil((double) (newDTO.getTotalItem()) / limit));
		
		ModelAndView mav = new ModelAndView("web/home");
		mav.addObject("model", newDTO);
		return mav;
	}
	
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		
		/* If authenticated and access to page /dang-nhap, then redirect back to page /trang-chu */
//		try {
//			SecurityUtils.getPrincipal();
//			return new ModelAndView("redirect:/trang-chu");
//		}catch (Exception e) {
//			ModelAndView mav = new ModelAndView("login");
//			return mav;
//		}
		
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
}
