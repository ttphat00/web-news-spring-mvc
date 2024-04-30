package com.example.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.dto.NewDTO;
import com.example.service.INewService;

@Controller(value = "newControllerOfWeb")
public class NewController {
	@Autowired
	private INewService newService;

	@RequestMapping(value = "/{category}/{newTitle}", method = RequestMethod.GET)
	public ModelAndView showList(@PathVariable("newTitle") String newTitle) {
		String[] subTitles = newTitle.split("-");
		long id = Long.parseLong(subTitles[subTitles.length - 1]);
		NewDTO dto = newService.findById(id);
		ModelAndView mav = new ModelAndView("web/detail");
		mav.addObject("model", dto);
		return mav;
	}
}
