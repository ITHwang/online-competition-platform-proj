package com.tstecon.ocp.qna.service;

import java.util.List;
import java.util.Map;

import com.tstecon.ocp.qna.vo.CompetQnaVO;

public interface CompetQnaService {

//���� 	
	public List<CompetQnaVO> qnalistArticles() throws Exception;
//���� �α���
	public CompetQnaVO login(CompetQnaVO competqnaVO) throws Exception;
//���� �� ��ȣ �߰�	 
	public int addNewArticle(Map articleMap) throws Exception;
//���� �󼼱�	
	public CompetQnaVO viewArticle(int articleNO) throws Exception;
//���� �� ����	
	public void modArticle(Map articleMap) throws Exception;
//���� �� ����		
	public void removeArticle(int articleNO) throws Exception;
}
