package com.tstecon.ocp.compet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.tstecon.ocp.compet.qna.vo.CompetQnaVO;
import com.tstecon.ocp.categ.vo.CategVO;
import com.tstecon.ocp.compet.vo.CompetFileVO;
import com.tstecon.ocp.compet.vo.CompetVO;

@Repository("CompetDAO")
public class CompetDAOImpl implements CompetDAO {

	@Autowired
	private SqlSession sqlSession;

//	��ȸ�ȳ� ������
	// ū ������
	@Override
	public List<CompetFileVO> selectCompetBigPoster(int compet_id) throws DataAccessException {
		List<CompetFileVO> competList = (ArrayList) sqlSession.selectList("mappers.compet.selectCompetBigPoster",
				compet_id);
		return competList;
	}

	@Override
	public List<CompetFileVO> selectCompetSmallPoster(int compet_id) throws DataAccessException {
		List<CompetFileVO> competList = (ArrayList) sqlSession.selectList("mappers.compet.selectCompetSmallPoster",
				compet_id);
		return null;
	}

//	��ȸ�ȳ� ����
	@Override
	public List<CompetVO> selectCompetInfo(int compet_id) throws DataAccessException {
		List<CompetVO> competList = (ArrayList) sqlSession.selectList("mappers.compet.selectCompetInfo", compet_id);
		return competList;
	}

//	��ȸ ������ Ŭ��
	@Override
	public List<CompetVO> selectCompet_id(int compet_id) throws DataAccessException {
		List<CompetVO> competList = (ArrayList) sqlSession.selectList("mappers.compet.selectCompet_id", compet_id);
		return competList;
	}

	@Override
	public List<CompetFileVO> selectCompet_id_file(int compet_id) throws DataAccessException {
		List<CompetFileVO> competList = (ArrayList) sqlSession.selectList("mappers.compet.selectCompet_id_file",
				compet_id);
		return competList;
	}

//���� �Խ��� ����	
	@Override
	public List selectAllQnaList() throws DataAccessException {
		List<CompetQnaVO> qnaList = sqlSession.selectList("mappers.compet.selectAllQnaList");
		return qnaList;
	}

	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CompetVO> selectCompetId(int i) throws DataAccessException {
		List<CompetVO> competNameList = (ArrayList) sqlSession.selectList("mappers.compet.selectCompetName", i);
		return competNameList;
	}

}
