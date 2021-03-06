package com.tstecon.ocp.admin.contents.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.tstecon.ocp.contents.vo.ContentsFileVO;
import com.tstecon.ocp.contents.vo.ContentsVO;

@Repository("AdminContentsDAO")
public class AdminContentsDAOImpl implements AdminContentsDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ContentsVO> selectContentsList(int compet_id) throws DataAccessException {
		List<ContentsVO> contentsList = (ArrayList)sqlSession.selectList("mappers.contents.selectContentsList", compet_id);
		return contentsList;
	}

	@Override
	public List<ContentsVO> selectApprContentsList(int compet_id) throws DataAccessException {
		List<ContentsVO> apprContentsList = (ArrayList)sqlSession.selectList("mappers.contents.selectApprContentsList", compet_id);
		return apprContentsList;
	}

	@Override
	public void updateApprContents(int contents_id) throws DataAccessException {
		sqlSession.update("mappers.contents.updateApprContents", contents_id);
		return;
	}

	@Override
	public List<ContentsVO> selectContentsView(int contents_id) throws DataAccessException {
		List<ContentsVO> contentsView = sqlSession.selectList("mappers.contents.selectContentsView",contents_id);
		return contentsView;
	}

	@Override
	public void updateRefusalContents(Map map) throws DataAccessException {
		sqlSession.update("mappers.contents.updateRefusalContents", map);
		return;
		
	}

	@Override
	public List<ContentsFileVO> selectContentsFileView(int contents_id) throws DataAccessException {
		List<ContentsFileVO> contentsFileView = sqlSession.selectList("mappers.contents.selectContentsFileView",contents_id);
		return contentsFileView;
	}

}
