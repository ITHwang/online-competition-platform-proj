package com.tstecon.ocp.compet.qna.service;

import java.util.List;
import java.util.Map;


import com.tstecon.ocp.compet.qna.vo.CompetQnaVO;





public interface CompetQnaService {

//���� 	
	public List<CompetQnaVO>qnalistArticles() throws Exception;
//���� �α���
	 public CompetQnaVO login(CompetQnaVO competqnaVO) throws Exception;
//���� �� ��ȣ �߰�	 
	 public int addNewArticle(Map articleMap) throws Exception;
//���� �󼼱�	
	 public CompetQnaVO viewArticle(int articleNO) throws Exception;
	 
	 
}
