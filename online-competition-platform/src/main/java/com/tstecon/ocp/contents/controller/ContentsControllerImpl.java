package com.tstecon.ocp.contents.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.tstecon.ocp.contents.vo.ContentsVO;
import com.tstecon.ocp.contents.vo.ListContentsVO;
import com.tstecon.ocp.member.vo.MemberVO;

import net.sf.json.JSONObject;

@Controller("contentsController")
public class ContentsControllerImpl implements ContentsController {
	private static final String CONTENTS_IMAGE_IN_TEXT = "C:\\ocp\\contentsfile\\img_in_text";

	@Autowired
	private ContentsService contentsService;

	@Autowired
	private ContentsFileVO contentsFileVO;

	@Autowired
	private ContentsVO contentsVO;

	@Override
	@RequestMapping(value = { "/contents/contentsForm.do" }, method = { RequestMethod.GET })
	public ModelAndView contentsForm(@RequestParam("compet_id") int compet_id, HttpServletRequest request,
			HttpServletResponse reponse) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		HttpSession session = request.getSession();

		mav.addObject("mem_id", ((MemberVO) session.getAttribute("loginInfo")).getMem_id());
		mav.addObject("compet_id", compet_id);
		return mav;
	}

	// ?????? ???? ?? ???????? ?????????? DB?? ?????????? ????
	@Override
	@RequestMapping(value = { "/contents/uploadImgInText.do" }, method = { RequestMethod.POST })
	public @ResponseBody String upload(@RequestBody String jsonMap, HttpServletRequest request,
			HttpServletResponse reponse) throws Exception {
		request.setCharacterEncoding("utf-8");

		Map<String, String> imageMap = new Gson().fromJson(jsonMap, Map.class);
		String base64 = imageMap.get("base64");

		String fileName = imageMap.get("fileName");
//		if (fileName == null) { // fileName?? ???? ???? ???????? ?????? fileName???? ????
//			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//			fileName = sdf.format(timestamp);
//		}

		String ext = (imageMap.get("ext") == "jpeg") ? "jpg" : imageMap.get("ext");
		byte[] binary = Base64.getDecoder().decode(base64);

		// ?????? compet ?????? id ????????
		int contents_file_id = contentsService.newContentsFileId();

		contentsFileVO.setContents_file_id(contents_file_id);
		contentsFileVO.setContents_file_name(fileName);
		contentsFileVO.setContents_file_type("img");

		File outFile = new File(CONTENTS_IMAGE_IN_TEXT + "\\" + String.valueOf(contents_file_id) + "\\" + fileName);
		if (!outFile.exists()) {
			// ???????????? ???? ????
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

		// DB?? ???? ????(contents_id ?????? null)
		contentsService.addNewContentsFile(contentsFileVO);

		return String.valueOf(contents_file_id);
	}

	// ?????? ???? ?? ?????? ?? ???????? ???? ?????? ????????
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
			int count = in.read(buffer); // ?????? ???????? ????????
			if (count == -1) // ?????? ???????? ?????????? ????
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}

	// ?????? ??????
	@Override
	@RequestMapping(value = { "/contents/listContents.do" }, method = { RequestMethod.GET })
	public ModelAndView listContents(@RequestParam("compet_id") int compet_id, HttpServletRequest request,
			HttpServletResponse reponse) throws Exception {

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		// ????, ?????? ?? ?????? ???? ????????
		List<ListContentsVO> contentsList = contentsService.listContents(compet_id);

		// ???? ?????? ????
		mav.addObject("compet_id", compet_id);
		// ????, ?????? ?? ?????? ???? ????
		mav.addObject("listContents", contentsList);

		return mav;
	}

//	@Override
	@RequestMapping(value = { "/contents/addContents.do" }, method = { RequestMethod.POST })
	public ResponseEntity listContents(@RequestParam HashMap<String, String> contentsMap, HttpServletRequest request,
			HttpServletResponse reponse) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		contentsVO.setCompet_id(Integer.parseInt(contentsMap.get("compet_id")));
		contentsVO.setContents_name(contentsMap.get("contents_name"));
		contentsVO.setContents_text(contentsMap.get("contents_text"));
		contentsVO.setContents_state("????????");
		contentsVO.setMem_id(contentsMap.get("mem_id"));

		int contents_id = contentsService.addNewContents(contentsVO); // ?????? ?????? ?? contents_id ????

		// ???? ???????? ???? ???????? contents_id?? ????????
		String contents_file_list_string = contentsMap.get("constents_file_list");
		contents_file_list_string = contents_file_list_string.substring(0, contents_file_list_string.length() - 1);
		List<String> contents_file_list = Arrays.asList(contents_file_list_string.split(","));
		contentsService.addContentsIdToFiles(contents_file_list, contents_id);

		String message = "<script>";
		message += "alert('???????? ??????????????');";
		message += "location.href='" + request.getContextPath() + "/compet/competInfo.do?compet_id="
				+ contentsMap.get("compet_id") + "';";
		message += ("</script>");

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		ResponseEntity resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	// ?????? ?????? ????
	@Override
	@RequestMapping(value = { "/contents/contentsView.do" }, method = { RequestMethod.GET })
	public ModelAndView contentsView(@RequestParam("contents_id") int contents_id, HttpServletRequest request,
			HttpServletResponse reponse) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		// ???? ?? ???? ?? ????
		contentsService.updateContentsView(contents_id);

		// ???? ???? ?????? ????
		List<ListContentsVO> contentsView = contentsService.selectContentsView(contents_id);

		// ???? ?????? ????
		List<ListContentsVO> contentsCmt = contentsService.selectContentsCmt(contents_id);

		// ?????? ???? ?????? ????
		List<ContentsFileVO> contentsFileView = contentsService.selectContentsFile(contents_id);

		mav.addObject("contentsView", contentsView);
		mav.addObject("contentsCmt", contentsCmt);
		mav.addObject("contentsFileView", contentsFileView);
		return mav;
	}

	// ?????? ?????? ????,????
	@RequestMapping(value = { "/contents/like_Update.do" }, method = { RequestMethod.POST })
	public void like_Update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> update = new HashMap<String, Object>();
		int contents_id = Integer.parseInt(request.getParameter("contents_id"));
		String mem_id = request.getParameter("mem_id");
		update.put("contents_id", contents_id);
		update.put("mem_id", mem_id);
		int like_check = contentsService.likeChenk(update);
		System.out.println("----------------------update============= 1 : " + update);
		if (like_check == 0) {
			contentsService.likeUpdate(update);
		} else {
			contentsService.likeDelete(update);
		}

	}

	// ?????? ?????? Count
	@RequestMapping(value = { "/contents/likeCount.do" }, method = { RequestMethod.POST })
	public void likeCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		int contents_id = Integer.parseInt(request.getParameter("contents_id"));
		int count = contentsService.likeCount(contents_id);
		System.out.println("---------contents_id --------------------------------------- : " + contents_id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("count", count);
		String jsoncount = jsonObject.toString();
		writer.print(jsoncount);

	}

	@Override
	@RequestMapping(value = { "/contents/addCmt.do" }, method = { RequestMethod.GET })
	public ModelAndView addCmt(@RequestParam("cmt_text") String cmt_text, @RequestParam("contents_id") int contents_id,
			HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session = request.getSession();

		MemberVO memberVO = (MemberVO) session.getAttribute("loginInfo");
		String memId = memberVO.getMem_id();
		int cmtId = contentsService.selectCmtPlusId();

		Map map = new HashMap();
		map.put("mem_id", memId);
		map.put("cmt_text", cmt_text);
		map.put("contents_id", contents_id);
		map.put("cmt_id", cmtId);

		contentsService.insertCmtAdd(map);

		mav.setViewName("redirect:/contents/contentsView.do?contents_id=" + contents_id);
		return mav;
	}

	// ?????? ???? ????
	@Override
	@RequestMapping(value = { "/contents/deleteCmt.do" }, method = { RequestMethod.GET })
	public ModelAndView deleteCmt(@RequestParam("cmt_id") int cmt_id, @RequestParam("contents_id") int contents_id,
			HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		contentsService.deleteCmt(cmt_id);

		mav.setViewName("redirect:/contents/contentsView.do?contents_id=" + contents_id);
		return mav;
	}

	// ?????? ?????? ????

}
