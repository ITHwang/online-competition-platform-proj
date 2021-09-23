package com.tstecon.ocp.admin.categ.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tstecon.ocp.admin.categ.dao.AdminCategDAO;
import com.tstecon.ocp.admin.categ.service.AdminCategService;
import com.tstecon.ocp.categ.vo.CategVO;
import com.tstecon.ocp.compet.service.CompetService;
import com.tstecon.ocp.compet.vo.CompetVO;

@Controller("AdminCategController")
public class AdminCategControllerImpl implements AdminCategController{
	@Autowired
	private AdminCategService adminCategService;
	
	@Autowired
	private CompetService competService;
	
	@Autowired
	private AdminCategDAO adminCategDAO;
	
	//������ ī�װ� �⺻ ������ ��Ʈ�ѷ�
	@Override
	@RequestMapping(value = { "/admin/admincateg.do" }, method = { RequestMethod.GET })
	public ModelAndView adminCateg(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		//Map<String, List<CompetVO> aa
		
		//ī�װ��� ��������
		//List<String> categList = adminCategService.Categlist();
		
		// �ݺ��ϱ�
		//�� ī�װ��� �ش��ϴ� competVO�� ��������
		//List<CompetVO> competList = competService.competListByName(ī�װ��̸�);
		//aa.put("ī�װ��̸�", competList);
		
		Map<String, List<CompetVO>> aa = new HashMap<String, List<CompetVO>>();
		Map<String, List<CompetVO>> bb = new HashMap<String, List<CompetVO>>();
		List<CategVO> categList = adminCategService.CategAllList();
		//List<CategVO> categList = ...
		
		// �������� ��ȸ ��������
		for (CategVO i : categList) {
			List<CompetVO> competList = competService.competListById(i.getCateg_id());
			// ... = competService.competListByCategId(i.categ_id);
			aa.put(i.getCateg_name(), competList);
			//aa.put(i.categ_name, competList);
		}
		mav.addObject("competInCateg", aa);
		
		// ����� ��ȸ ��������
		for (CategVO i : categList) {
			List<CompetVO> competList = competService.finishCompetList(i.getCateg_id());
			// ... = competService.competListByCategId(i.categ_id);
			bb.put(i.getCateg_name(), competList);
			//aa.put(i.categ_name, competList);
		}
		mav.addObject("finishCompet", bb);
		return mav;
	}
	
	//������ ī�װ� ���� ������ ��Ʈ�ѷ� -->> ���߿� �п����� ��ġ��
	@Override
	@RequestMapping(value = { "/admin/addCategForm.do" }, method = { RequestMethod.GET })
	public ModelAndView addCategForm(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	//��ȸ �߰�
	@Override
	@RequestMapping(value = { "/admin/addCompet.do" }, method = { RequestMethod.GET })
	public ModelAndView addCompet(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/admin/admincateg.do");
		return mav;
	}
	
	// ��ȸ ����
	@Override
	@RequestMapping(value = { "/admin/competFinish.do" }, method = { RequestMethod.GET })
	public ModelAndView competFinish(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/admin/admincateg.do");
		return mav;
	}
	
	// ����� ��ȸ ����
	@Override
	@RequestMapping(value = { "/admin/competDelete.do" }, method = { RequestMethod.GET })
	public ModelAndView competDelete(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/admin/admincateg.do");
		return mav;
	}
	
	

	
}
