package com.tstecon.ocp.admin.contents.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface AdminContentsController {
	public ModelAndView adminContents(HttpServletRequest request, HttpServletResponse reponse) throws Exception;
	
}