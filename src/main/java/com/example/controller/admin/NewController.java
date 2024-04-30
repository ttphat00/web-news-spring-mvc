package com.example.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.dto.NewDTO;
import com.example.service.ICategoryService;
import com.example.service.INewService;
import com.example.util.MessageUtil;

@Controller(value = "newControllerOfAdmin")
public class NewController {
	@Autowired
	private INewService newService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page, 
								@RequestParam("limit") int limit, 
								HttpServletRequest req) {
		NewDTO newDTO = new NewDTO();
		newDTO.setPage(page);
		newDTO.setLimit(limit);
		newDTO.setListResult(newService.findAll(page, limit));
		newDTO.setTotalItem(newService.totalItem());
		newDTO.setTotalPage((int) Math.ceil((double) (newService.totalItem()) / limit));
		
		ModelAndView mav = new ModelAndView("admin/new/list");
		if(req.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(req.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", newDTO);
		
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, 
								HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("admin/new/edit");
		NewDTO model = new NewDTO();
		if(id != null) {
			model = newService.findById(id);
		}
		if(req.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(req.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("categories", categoryService.findAll());
		mav.addObject("model", model);
		return mav;
	}
}
