package com.tstecon.ocp.compet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;




import com.tstecon.ocp.compet.vo.CompetFileVO;
import com.tstecon.ocp.compet.vo.CompetVO;


public interface CompetDAO {
//	��ȸ�ȳ� ������
	public List<CompetVO> selectCompetPoster(int compet_id) throws DataAccessException;
//	��ȸ�ȳ� ����
<<<<<<< HEAD
	public List<CompetVO> selectCompetInfo(int Compet_id) throws DataAccessException;
	public CompetFileVO selectCompetSmallPoster(int Compet_id) throws DataAccessException;
=======
	public List<CompetVO> selectCompetInfo(String Compet_id) throws DataAccessException;

// ���� �Խ��� ����
	public List selectAllQnaList() throws DataAccessException;

	public CompetFileVO selectCompetSmallPoster(String Compet_id) throws DataAccessException;

>>>>>>> branch 'master' of https://github.com/ITHwang/online-competition-platform-proj.git
}
