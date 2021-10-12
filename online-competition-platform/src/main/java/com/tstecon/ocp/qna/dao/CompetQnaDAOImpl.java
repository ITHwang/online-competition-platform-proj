package com.tstecon.ocp.qna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.tstecon.ocp.qna.vo.CompetQnaVO;

@Repository("CompetQnaDAO")
public class CompetQnaDAOImpl implements CompetQnaDAO {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private CompetQnaVO competqnaVO;

//���� �Խ��� ����	
	@Override
	public List selectAllQnaList(int compet_id) throws DataAccessException {
		List<CompetQnaVO> qnaList = sqlSession.selectList("mappers.competQna.selectAllQnaList", compet_id);
		return qnaList;
	}
//�� ���� �߰�
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int articleNO = selectNewArticleNO();
		articleMap.put("articleNO", articleNO);
		System.out.println("articleNO :" + articleNO);
		sqlSession.insert("mappers.competQna.insertNewQna", articleMap);
		return articleNO;
	}
//�� ���� �߰�, �� ��ȣ ��������
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mappers.competQna.selectNewQnaNO");
	}
//�� ������ ����	
	public CompetQnaVO selectQnaArticle(int articleNO) throws DataAccessException {
		return sqlSession.selectOne("mappers.competQna.selectQnaArticle", articleNO);
	}
//���� �� ����		
	public void updateArticle(Map articleMap) throws DataAccessException{
		System.out.println( "���� ������1 :" + articleMap );
		sqlSession.update("mappers.competQna.updateArticle", articleMap);
		
	}
//���� �� ����		
	public void deleteArticle(int articleNO) throws DataAccessException{
		sqlSession.delete("mappers.competQna.deleteArticle", articleNO);
	}
//���� ��� �۾���	
	public int insertReplyNewArticle(Map articleMap) throws DataAccessException{
		int articleNO = selectNewArticleNO();
		articleMap.put("articleNO", articleNO);
		sqlSession.insert("mappers.competQna.insertReplyNewQna", articleMap);
		return articleNO;
	}
	@Override
	public void updateQnaView(int articleNO) throws DataAccessException {
		sqlSession.update("mappers.competQna.updateQnaView", articleNO);
	}
}
