package com.tstecon.ocp.qna.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tstecon.ocp.qna.dao.CompetQnaDAO;
import com.tstecon.ocp.qna.vo.CompetQnaVO;

@Service("CompetQnaService")
public class CompetQnaServiceImpl implements CompetQnaService {
	@Autowired
	private CompetQnaDAO competQnaDAO;
	


//���� �Խ���
	@Override
	public List<CompetQnaVO> qnalistArticles(int compet_id) throws Exception {
		List<CompetQnaVO> qnaList = competQnaDAO.selectAllQnaList(compet_id);
		return qnaList;
	}
//�α���
	@Override
	public CompetQnaVO login(CompetQnaVO competqnaVO) throws Exception {
		return competQnaDAO.quaLogin(competqnaVO);
	}
//�� ���� �߰�
	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		Iterator<String> keys = articleMap.keySet().iterator();

		System.out.println("service�� map");
		while (keys.hasNext()) {
			String key = keys.next();
			System.out.println(String.format("Ű : %s, �� : %s", key, articleMap.get(key)));
		}
		return competQnaDAO.insertNewArticle(articleMap);
	}
//�� ������ ����
	public CompetQnaVO viewArticle(int articleNO) throws Exception {
		return competQnaDAO.selectQnaArticle(articleNO);
	}
//���� �� ����	
		public void modArticle(Map articleMap) throws Exception{
			competQnaDAO.updateArticle(articleMap);
		}
//���� �� ����		
		public void removeArticle(int articleNO) throws Exception{
			competQnaDAO.deleteArticle(articleNO);
		}
//���� ��۾��� �߰�		
		  public int replyAddNewArticle(Map articleMap) throws Exception{
			  return competQnaDAO.insertReplyNewArticle(articleMap);
		  }
}
