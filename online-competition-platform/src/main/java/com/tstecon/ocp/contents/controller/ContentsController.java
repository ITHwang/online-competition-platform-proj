package com.tstecon.ocp.contents.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface ContentsController {

// ���� ������
	public ModelAndView contentsForm(@RequestParam("compet_id") int compet_id, HttpServletRequest request,
			HttpServletResponse reponse) throws Exception;

}
