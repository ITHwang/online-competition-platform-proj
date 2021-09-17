package com.tstecon.ocp.compet.qna.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.tstecon.ocp.compet.qna.vo.CompetQnaVO;

@Repository("CompetQnaDAO")
public class CompetQnaDAOImpl implements CompetQnaDAO {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private CompetQnaVO competqnaVO;

//���� �Խ��� ����	
	@Override
	public List selectAllQnaList() throws DataAccessException {
		List<CompetQnaVO> qnaList = sqlSession.selectList("mappers.competQna.selectAllQnaList");
		return qnaList;
	}

	public CompetQnaVO quaLogin(CompetQnaVO competqnaVO) throws DataAccessException {
		CompetQnaVO vo = sqlSession.selectOne("mappers.competQna.qnaLogin", competqnaVO); // vo�� ���� ������ vo�� ����ִ� login
																							// ���� �������� ������� �ؼ�?
		return vo;
	}

	
	  public int insertNewArticle(Map articleMap) throws DataAccessException{ 
		  int articleNO = selectNewArticleNO(); 
		  articleMap.put("articleNO", articleNO);    
	      sqlSession.insert("mappers.competQna.insertNewQna",articleMap);
	      return articleNO; 
	    }
	 
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mappers.competQna.selectNewQnaNO");
	}
	
	public CompetQnaVO selectQnaArticle(int articleNO) throws DataAccessException{
		return sqlSession.selectOne("mapper.board.selectQnaArticle", articleNO);
	}

}
