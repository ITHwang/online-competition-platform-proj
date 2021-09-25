package com.tstecon.ocp.admin.categ.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.tstecon.ocp.admin.categ.dao.AdminCategDAO;
import com.tstecon.ocp.admin.categ.service.AdminCategService;
import com.tstecon.ocp.categ.vo.CategVO;
import com.tstecon.ocp.compet.service.CompetService;
import com.tstecon.ocp.compet.vo.CompetFileVO;
import com.tstecon.ocp.compet.vo.CompetVO;

@Controller("AdminCategController")
public class AdminCategControllerImpl implements AdminCategController {
	private static final String COMPET_IMAGE_REPO = "C:\\ocp\\competfile";

	@Autowired
	private AdminCategService adminCategService;

	@Autowired
	private CompetService competService;

	@Autowired
	private AdminCategDAO adminCategDAO;

	// ������ ī�װ� �⺻ ������ ��Ʈ�ѷ�
	@Override
	@RequestMapping(value = { "/admin/admincateg.do" }, method = { RequestMethod.GET })
	public ModelAndView adminCateg(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		// Map<String, List<CompetVO> aa

		// ī�װ��� ��������
		// List<String> categList = adminCategService.Categlist();

		// �ݺ��ϱ�
		// �� ī�װ��� �ش��ϴ� competVO�� ��������
		// List<CompetVO> competList = competService.competListByName(ī�װ��̸�);
		// aa.put("ī�װ��̸�", competList);

		Map<String, List<CompetVO>> aa = new HashMap<String, List<CompetVO>>();
		Map<String, List<CompetVO>> bb = new HashMap<String, List<CompetVO>>();
		List<CategVO> categList = adminCategService.CategAllList();
		// List<CategVO> categList = ...

		// �������� ��ȸ ��������
		for (CategVO i : categList) {
			List<CompetVO> competList = competService.competListById(i.getCateg_id());
			// ... = competService.competListByCategId(i.categ_id);
			aa.put(i.getCateg_name(), competList);
			// aa.put(i.categ_name, competList);
		}
		mav.addObject("competInCateg", aa);

		// ����� ��ȸ ��������
		for (CategVO i : categList) {
			List<CompetVO> competList = competService.finishCompetList(i.getCateg_id());
			// ... = competService.competListByCategId(i.categ_id);
			bb.put(i.getCateg_name(), competList);
			// aa.put(i.categ_name, competList);
		}
		mav.addObject("finishCompet", bb);
		return mav;
	}

	// ������ ī�װ� ���� ������ ��Ʈ�ѷ� -->> ���߿� �п����� ��ġ��
	@Override
	@RequestMapping(value = { "/admin/addCategForm.do" }, method = { RequestMethod.GET })
	public ModelAndView addCategForm(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	// ��ȸ �߰�
	@Override
	@RequestMapping(value = { "/admin/addCompet.do" }, method = { RequestMethod.POST })
	public ResponseEntity addCompet(@RequestParam Map<String, Object> competMap,
			MultipartHttpServletRequest multipartRequest, HttpServletResponse reponse) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");

		int compet_id = competService.addCompetId(); // �߰��� ��ȸ�� id ��������
		int categ_id = adminCategService.addCompet(competMap); // �߰��� ��ȸ�� ī�װ��� id ��������
		competMap.put("compet_id", compet_id);
		competMap.put("categ_id", categ_id);

		List<CompetFileVO> imageFileList = new ArrayList<CompetFileVO>(); // competfile table�� insert�� �� �ʿ��� ����Ʈ
		Iterator<String> fileNames = multipartRequest.getFileNames(); // file type�� �ش��ϴ� input �±��� name�� �������� ex)
																		// BigPoster, SmallPoster

		while (fileNames.hasNext()) { // fileName�� ������

			String fileName = fileNames.next(); // fileName ������
			MultipartFile mFile = multipartRequest.getFile(fileName); // fileName���� ������ input �±׿� ����Ǿ��� ���� ��������
			String mFileName = mFile.getOriginalFilename(); // ������ �̸�
			String mFileExtension = mFileName.substring(mFileName.indexOf('.')); // ������ Ȯ���ڸ� ex) .jpg, .png ��

			// ���Ŀ� �´� �̸����� ���� �̸� �ٲٱ�
			String updatedFileName = null;
			if (fileName.equals("BigPoster")) {
				updatedFileName = competMap.get("compet_name") + "-big" + mFileExtension;
			} else {
				updatedFileName = competMap.get("compet_name") + "-small" + mFileExtension;
			}

			// ���� VO ���� ���� �� ����Ʈ�� �߰�
			CompetFileVO competFileVO = new CompetFileVO();
			competFileVO.setCompet_file_name(updatedFileName);
			competFileVO.setCompet_file_type("img");
			competFileVO.setCompet_id((Integer) competMap.get("compet_id"));
			// competDAOImpl.insertCompetFile()���� setCompet_file_id�� ����
			imageFileList.add(competFileVO);

			// �ϵ��ũ�� ���� ����
			File file = new File(COMPET_IMAGE_REPO + "\\" + String.valueOf(competMap.get("compet_id")) + "\\img\\"
					+ updatedFileName);
			if (mFile.getSize() != 0) { // File Null Check
				if (!file.exists()) { // ��λ� ������ �������� ���� ���
					file.getParentFile().mkdirs(); // ��ο� �ش��ϴ� ���丮���� ����
					// �ӽ÷� ����� multipartFile�� ���� ���Ϸ� ����
					mFile.transferTo(file);
				}
			}
		}

		// DB�� ���� ����
		competMap.put("imageFileList", imageFileList);
		competService.addCompet(competMap); // compet table, compet file table�� �߰�

		// ����Ʈ
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		message = "<script>";
		message += " alert('��ȸ�� �߰��߽��ϴ�');";
		message += " location.href='" + multipartRequest.getContextPath() + "/admin/admincateg.do'; ";
		message += " </script>";
		resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		return resEnt;
	}

	// ��ȸ ����
	@Override
	@RequestMapping(value = { "/admin/competFinish.do" }, method = { RequestMethod.GET })
	public ModelAndView competFinish(@RequestParam("compet_name") String compet_name, HttpServletRequest request,
			HttpServletResponse reponse) throws Exception {
		ModelAndView mav = new ModelAndView();
		competService.competFinish(compet_name);
		mav.setViewName("redirect:/admin/admincateg.do");
		return mav;
	}

	// ����� ��ȸ ����
	@Override
	@RequestMapping(value = { "/admin/competDelete.do" }, method = { RequestMethod.GET })
	public ModelAndView competDelete(@RequestParam("compet_name") String compet_name, HttpServletRequest request,
			HttpServletResponse reponse) throws Exception {
		ModelAndView mav = new ModelAndView();
		competService.competDelete(compet_name);
		mav.setViewName("redirect:/admin/admincateg.do");
		return mav;
	}

//	private List<String> upload(MultipartHttpServletRequest multipartRequest) throws Exception {
////		CompetVO competVO = new CompetVO();
////		int compet_id = competVO.getCompet_id();
//		List<String> fileList = new ArrayList<String>();
//		Iterator<String> fileNames = multipartRequest.getFileNames();
//		while (fileNames.hasNext()) {
//			String fileName = fileNames.next();
//			MultipartFile mFile = multipartRequest.getFile(fileName);
//			String originalFileName = mFile.getOriginalFilename();
//			fileList.add(originalFileName);
//			File file = new File(COMPET_IMAGE_REPO + "\\" + compet_id + "\\img\\temp\\" + fileName);
//			if (mFile.getSize() != 0) { // File Null Check
//				if (!file.exists()) { // ��λ� ������ �������� ���� ���
//					file.getParentFile().mkdirs(); // ��ο� �ش��ϴ� ���丮���� ����
//					mFile.transferTo(new File(
//							COMPET_IMAGE_REPO + "\\" + "competfile" + "\\" + /* compet_id + */"\\" + originalFileName)); // �ӽ÷�
//																															// �����
//																															// multipartFile��
//																															// ����
//																															// ���Ϸ�
//																															// ����
//				}
//			}
//		}
//		return fileList;
//	}

}
