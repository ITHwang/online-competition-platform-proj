package com.tstecon.ocp.qna.service;

import java.util.List;
import java.util.Map;

import com.tstecon.ocp.qna.vo.CompetQnaVO;

public interface CompetQnaService {

//���� 	
	public List<CompetQnaVO> qnalistArticles(int compet_id) throws Exception;
//���� �α���
	public CompetQnaVO login(CompetQnaVO competqnaVO) throws Exception;
//���� �� ���� �߰�	 
	public int addNewArticle(Map articleMap) throws Exception;
//���� �󼼱�	
	public CompetQnaVO viewArticle(int articleNO) throws Exception;
//���� �� ����	
	public void modArticle(Map articleMap) throws Exception;
//���� �� ����		
	public void removeArticle(int articleNO) throws Exception;
}
