package com.tstecon.ocp.qna.service;

import java.util.List;
import java.util.Map;

import com.tstecon.ocp.qna.vo.CompetQnaVO;

public interface CompetQnaService {

//문의 	
	public List<CompetQnaVO> qnalistArticles(int compet_id) throws Exception;
//문의 글 쓰기 추가	 
	public int addNewArticle(Map articleMap) throws Exception;
//문의 상세글	
	public CompetQnaVO viewArticle(int articleNO) throws Exception;
//문의 글 수정	
	public void modArticle(Map articleMap) throws Exception;
//문의 글 삭제		
	public void removeArticle(int articleNO) throws Exception;
//문의 댓글 쓰기 추가
	   public int replyAddNewArticle(Map articleMap) throws Exception;
// 조회수 1 증가	   	   
	public void updateQnaView(int articleNO)throws Exception;	
}
