package com.tstecon.ocp.compet.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tstecon.ocp.compet.dao.CompetDAO;
import com.tstecon.ocp.compet.qna.vo.CompetQnaVO;
import com.tstecon.ocp.compet.service.CompetService;
<<<<<<< HEAD
import com.tstecon.ocp.compet.vo.CompetVO;
=======
import com.tstecon.ocp.qna.vo.CompetQnaVO;
>>>>>>> branch 'master' of https://github.com/ITHwang/online-competition-platform-proj.git

@Controller("CompetController")
public class CompetControllerImpl implements CompetController {
	@Autowired
	private CompetService competService;

	@Autowired
	private CompetDAO competDAO;

	@Autowired
	private CompetQnaVO competqnaVO;

//	��ȸ�ȳ�������
	@Override
	@RequestMapping(value = { "/compet/competInfo.do" }, method = { RequestMethod.GET })
	public ModelAndView CompetInfo(@RequestParam("compet_id") int compet_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		// ��ȸ �ȳ� Ű ��� �߰�
		Map competInfo = competService.CompetInfo(compet_id);
		mav.addObject("competInfo", competInfo);
		// ��ȸ ������ Ű ��� �߰�
		competInfo = competService.CompetPoster(compet_id);
		mav.addObject("competPoster", competInfo);
		return mav;
	}


<<<<<<< HEAD
	

	
=======
	}

//���� ������ �α���
	@Override
	@RequestMapping(value = "/compet/login.do", method = RequestMethod.POST)
	public ModelAndView Compet_qna_login(@ModelAttribute("member") CompetQnaVO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		competqnaVO = competService.login(member);
		if (competqnaVO != null) {

		}

		return null;
	}

	@Override
	@RequestMapping(value = "/compet/competList.do", method = RequestMethod.GET)
	public ModelAndView competList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		// service: ������ ��������
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	@Override
	public ModelAndView Compet_qna_logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		return null;
	}
>>>>>>> branch 'master' of https://github.com/ITHwang/online-competition-platform-proj.git

}
