package com.tstecon.ocp.compet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface CompetController {
//	��ȸ�ȳ� ������
	public ModelAndView CompetInfo(@RequestParam("compet_id") String compet_id,HttpServletRequest request, HttpServletResponse reponse) throws Exception;
//���� ������
public ModelAndView Compet_qna_list(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}


