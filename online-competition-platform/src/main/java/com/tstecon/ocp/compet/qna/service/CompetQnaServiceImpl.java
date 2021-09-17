package com.tstecon.ocp.compet.qna.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tstecon.ocp.compet.qna.dao.CompetQnaDAO;
import com.tstecon.ocp.compet.qna.vo.CompetQnaVO;



@Service("CompetQnaService")
public class CompetQnaServiceImpl implements CompetQnaService{
	@Autowired
	private CompetQnaDAO competQnaDAO;



//���� �Խ���
	@Override
	public List<CompetQnaVO> qnalistArticles() throws Exception {
		List<CompetQnaVO> qnaList = competQnaDAO.selectAllQnaList();
		return qnaList;
	}
	@Override
	public CompetQnaVO login(CompetQnaVO competqnaVO) throws Exception{
		 return competQnaDAO.quaLogin(competqnaVO);
	}
	@Override
	 public int addNewArticle(Map articleMap) throws Exception{
        Iterator<String> keys = articleMap.keySet().iterator();
        
        System.out.println("service�� map");
          while( keys.hasNext() ){
            String key = keys.next();
            System.out.println( String.format("Ű : %s, �� : %s", key, articleMap.get(key)) );
        }
		return competQnaDAO.insertNewArticle(articleMap);		
	}
	
	public CompetQnaVO viewArticle(int articleNO) throws Exception{
		CompetQnaVO articleVO = competQnaDAO.selectQnaArticle(articleNO);
		return articleVO;
	}

}
