package com.tstecon.ocp.contents.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface ContentsController {

// ���� ������
	public ModelAndView contentsForm(@RequestParam("compet_id") int compet_id, @RequestParam("mem_id") int mem_id,
			HttpServletRequest request, HttpServletResponse reponse) throws Exception;

// ������ �ۼ� �� ���ԵǴ� �̹������� DB�� ���丮���� ����
	public @ResponseBody String upload(@RequestBody String jsonMap, HttpServletRequest request,
			HttpServletResponse reponse) throws Exception;

	public ModelAndView contentsForm(@RequestParam("compet_id") int compet_id, HttpServletRequest request,
			HttpServletResponse reponse) throws Exception;

	// ������ �Խ���
	public ModelAndView listContents(@RequestParam("compet_id") int compet_id, HttpServletRequest request,
			HttpServletResponse reponse) throws Exception;
}
