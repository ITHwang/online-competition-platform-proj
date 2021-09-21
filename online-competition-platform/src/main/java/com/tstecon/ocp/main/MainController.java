package com.tstecon.ocp.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tstecon.ocp.banner.service.BannerService;
import com.tstecon.ocp.common.base.BaseController;

@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController extends BaseController {
	@Autowired
	private BannerService bannerService;

	@RequestMapping(value = { "/main/main.do" }, method = { RequestMethod.GET })
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		// List<BannerFileVO> ��������

		// ��������?? >> jsp���� ajax

		// List<category>
		// ī�װ����� List<competVO> ��������
		// Map<ī�װ� �̸�, List<CompetVO>

		ModelAndView mav = new ModelAndView(viewName);
		// mav.addObject("BannerFileList", )
		// mav.addObject("competList")

		return mav;
	}

	@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
	public String mainOnlySlash(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "redirect:/main/main.do";
	}
}
