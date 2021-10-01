package com.tstecon.ocp.contents.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tstecon.ocp.contents.service.ContentsService;
import com.tstecon.ocp.contents.vo.ContentsFileVO;
import com.tstecon.ocp.contents.service.ContentsService;
import com.tstecon.ocp.contents.vo.ContentsVO;
import com.tstecon.ocp.contents.vo.ListContentsVO;

@Controller("contentsController")
public class ContentsControllerImpl implements ContentsController {
	private static final String CONTENTS_IMAGE_IN_TEXT = "C:\\ocp\\contentsfile\\img_in_text";

	@Autowired
	private ContentsService contentsService;

	@Autowired
	private ContentsFileVO contentsFileVO;

	@Override
	@RequestMapping(value = { "/contents/contentsForm.do" }, method = { RequestMethod.GET })
	public ModelAndView contentsForm(@RequestParam("compet_id") int compet_id, @RequestParam("mem_id") int mem_id,
			HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("compet_id", compet_id);
		return mav;
	}

	// ������ �ۼ� �� ���ԵǴ� �̹������� DB�� ���丮���� ����
	@Override
	@RequestMapping(value = { "/contents/uploadImgInText.do" }, method = { RequestMethod.POST })
	public @ResponseBody String upload(@RequestBody String jsonMap, HttpServletRequest request,
			HttpServletResponse reponse) throws Exception {
		request.setCharacterEncoding("utf-8");

		Map<String, String> imageMap = new Gson().fromJson(jsonMap, Map.class);
		String base64 = imageMap.get("base64");
		String fileName = imageMap.get("fileName");
		String ext = (imageMap.get("ext") == "jpeg") ? "jpg" : imageMap.get("ext");
		byte[] binary = Base64.getDecoder().decode(base64);

		// �߰��� compet ������ id ��������
		int contents_file_id = contentsService.newContentsFileId();

		contentsFileVO.setContents_file_id(contents_file_id);
		contentsFileVO.setContents_file_name(fileName);
		contentsFileVO.setContents_file_type("img");
		// contents_id�� ���ε� �ɶ�

		File outFile = new File(CONTENTS_IMAGE_IN_TEXT + "\\" + String.valueOf(contents_file_id) + "\\" + fileName);
		if (!outFile.exists()) {
			// �ϵ��ũ�� ���� ����
			try {
				if (!outFile.getParentFile().exists()) {
					outFile.getParentFile().mkdirs();
				}
				FileOutputStream fileOutputStream = new FileOutputStream(outFile);
				fileOutputStream.write(binary);
				fileOutputStream.close();

			} catch (Throwable e) {
				e.printStackTrace(System.out);
			}
		}

		// DB�� ���� ����(contents_id Į���� null)
		contentsService.addNewContentsFile(contentsFileVO);

		return String.valueOf(contents_file_id);
	}

	// ������ ��ȸ �� �ؽ�Ʈ �� ���ԵǾ� �ִ� �̹��� �ҷ�����
//	@Override
	@RequestMapping(value = { "/contents/downloadImgInText.do" }, method = { RequestMethod.GET })
	public void download(@RequestParam("contents_file_id") int contents_file_id,
			@RequestParam("fileName") String fileName, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");

		OutputStream out = response.getOutputStream();
		String filePath = CONTENTS_IMAGE_IN_TEXT + "\\" + String.valueOf(contents_file_id) + "\\" + fileName;
		File image = new File(filePath);

		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment;");
		FileInputStream in = new FileInputStream(image);
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer); // ���ۿ� �о���� ���ڰ���
			if (count == -1) // ������ �������� �����ߴ��� üũ
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}
	
	// ������ �Խ���
	@Override
	@RequestMapping(value = { "/contents/listContents.do" }, method = { RequestMethod.GET })
	public ModelAndView listContents(@RequestParam("compet_id") int compet_id, HttpServletRequest request,
			HttpServletResponse reponse) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		List<ListContentsVO> contentsList = contentsService.listContents(compet_id);
		mav.addObject("compet_id", compet_id);
		mav.addObject("listContents", contentsList);
		return mav;
	}

	// ������ �ڼ��� ����
	@Override
	@RequestMapping(value = { "/contents/contentsView.do" }, method = { RequestMethod.GET })
	public ModelAndView contentsView(@RequestParam("contents_id") int contents_id, HttpServletRequest request, HttpServletResponse reponse)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		// Ŭ�� �� ��ȸ �� ����
		contentsService.updateContentsView(contents_id);
		
		// ��� ���� ����Ʈ ����
		List<ListContentsVO> contentsView = contentsService.selectContentsView(contents_id);
		
		// ��� ����Ʈ ����
		List<ListContentsVO> contentsCmt = contentsService.selectContentsCmt(contents_id);
		
		// ������ ���� ����Ʈ ����
		List<ContentsFileVO> contentsFileView = contentsService.selectContentsFile(contents_id);
		
		mav.addObject("contentsView", contentsView);
		mav.addObject("contentsCmt", contentsCmt);
		mav.addObject("contentsFileView", contentsFileView);
		return mav;
	}
	
	

}
