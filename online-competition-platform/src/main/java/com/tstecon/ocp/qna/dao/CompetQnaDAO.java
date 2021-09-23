package com.tstecon.ocp.qna.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.tstecon.ocp.qna.vo.CompetQnaVO;

public interface CompetQnaDAO {

// ���� �Խ��� ����
	public List selectAllQnaList() throws DataAccessException;

	public int insertNewArticle(Map articleMap) throws DataAccessException;

	public CompetQnaVO quaLogin(CompetQnaVO competqnaVO) throws DataAccessException;

	public CompetQnaVO selectQnaArticle(int articleNO) throws DataAccessException;
}
