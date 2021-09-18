package com.tstecon.ocp.compet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tstecon.ocp.compet.qna.vo.CompetQnaVO;

public interface CompetController {
//	��ȸ�ȳ� ������
public ModelAndView CompetInfo(@RequestParam("compet_id") int compet_id,HttpServletRequest request, HttpServletResponse reponse) throws Exception;

//���� ������
	public ModelAndView Compet_qna_list(HttpServletRequest request, HttpServletResponse response) throws Exception;

//���� �۾���â �α���, �α׾ƿ�
	public ModelAndView Compet_qna_login(@ModelAttribute("member") CompetQnaVO competqnaVO, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView Compet_qna_logout(HttpServletRequest request, HttpServletResponse response) throws Exception;

//���� �۾��� �߰�
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception;

	// ��ȸ��� ������
	public ModelAndView competList(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
