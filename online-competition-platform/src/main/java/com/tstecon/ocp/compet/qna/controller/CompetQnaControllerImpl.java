package com.tstecon.ocp.compet.qna.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.tstecon.ocp.compet.qna.dao.CompetQnaDAO;
import com.tstecon.ocp.compet.qna.service.CompetQnaService;
import com.tstecon.ocp.compet.qna.vo.CompetQnaVO;



@Controller("CompetQnaController")
public  class CompetQnaControllerImpl implements CompetQnaController {
	@Autowired
	private CompetQnaService competQnaService;

	@Autowired
	private CompetQnaDAO competQnaDAO;
	
	@Autowired
    private CompetQnaVO competqnaVO;
	

//���� ������	
	@Override
	@RequestMapping(value = "/compet/qna.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView Compet_qna_list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List qnaList = competQnaService.qnalistArticles();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("qnaList", qnaList);
		return mav;

	}
	
//���� ������ �α���
	@Override
	@RequestMapping(value = "/compet/login.do", method = RequestMethod.POST )
	public ModelAndView Compet_qna_login(@ModelAttribute("member")CompetQnaVO member, RedirectAttributes rAttr,
			  HttpServletRequest request, HttpServletResponse response) throws Exception{
			  ModelAndView mav = new ModelAndView();
			  competqnaVO = competQnaService.login(member);
			  if(competqnaVO != null ) {
				  HttpSession session = request.getSession();
				  session.setAttribute("member", competqnaVO);
				  session.setAttribute("isLogOn", true);
			      String action= (String)session.getAttribute("action");    
			      session.removeAttribute("action"); 
			      
			      if(action!=null) {
			    	  mav.setViewName("redirect:" +action); //�� �̸� 
			      }else {
			    	  mav.setViewName("redirect:/member/listMembers.do");
			      }

			  }else {
				  rAttr.addAttribute("result", "loginFailed"); //loginFailed?
				  mav.setViewName("redirect:/member/loginForm.do"); //--------��ũ ����-------
			  }
		
		return mav;  // �׷� return �ϰ� �Ǹ� loginForm ���� �޴� �ǰ�?
	}
	
//���� ������ �α׾ƿ�	
	@Override
	@RequestMapping(value = "/compet/logout.do", method = RequestMethod.GET )
	public ModelAndView  Compet_qna_logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main/main.do"); //?
		return mav;		
	}
	
//���� ������ �� �̵�
	@RequestMapping(value = "/compet/*Form.do", method =  RequestMethod.GET)
	private ModelAndView form(@RequestParam(value= "result", required=false) String result,
			  @RequestParam(value= "action", required=false) String action, 
		       HttpServletRequest request, 
		       HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;
	}
	
//���� �۾��� â
	@Override
	@RequestMapping(value="/compet/addNewArticle.do", method= RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception{
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();  
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			articleMap.put(name, value); 
		}
		
//	     HttpSession session = multipartRequest.getSession();
//	     CompetQnaVO competqnaVO =(CompetQnaVO) session.getAttribute("member");
//		  String mem_id =  competqnaVO.getMem_id();
		
//		 String mem_id ="20210907";
//	     articleMap.put("parentNO", 0);
//	     articleMap.put("mem_id", mem_id);
	     
	     String message;
	     ResponseEntity resEnt=null;
	     HttpHeaders responseHeaders = new HttpHeaders();
	     responseHeaders.add("Content-Type", "text/html; charset=utf-8");	     
	     try {
	        int articleNO = competQnaService.addNewArticle(articleMap);	 
	            message = "<script>";
	            message += " alert('������ �߰��߽��ϴ�.');"; 
	            message += " location.href='"
	    		                          +multipartRequest.getContextPath()+"/compet/qna.do';";
	            message +="</script>";
	            resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	}catch(Exception e) {
		        message = "<script>";
		        message += " alert('������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');');";
		        message += " location.href='"
                 +multipartRequest.getContextPath()+"/compet/*Form.do';";
		        message +="</script>";
		        resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		        e.printStackTrace();
		 }
	
	       return resEnt;
	
	}	

	
//���� ��â ����
	@RequestMapping(value="/compet/viewArticle.do" ,method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO,
	        HttpServletRequest request, HttpServletResponse response) throws Exception{
	        String viewName = (String)request.getAttribute("viewName");
	        competqnaVO = competQnaService.viewArticle(articleNO);
	        ModelAndView mav = new ModelAndView();
	        mav.setViewName(viewName);
	        mav.addObject("article",competqnaVO);		
		    return mav;
	}
	
	
	
	
	
}
