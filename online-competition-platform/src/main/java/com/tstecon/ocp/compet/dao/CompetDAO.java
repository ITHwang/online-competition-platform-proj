package com.tstecon.ocp.compet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tstecon.ocp.compet.vo.CompetVO;


public interface CompetDAO {
//	��ȸ�ȳ� ������
	public List<CompetVO> selectCompetPoster(String compet_id) throws DataAccessException;
//	��ȸ�ȳ� ����
	public List<CompetVO> selectCompetInfo(String Compet_id) throws DataAccessException;
}
