package com.tstecon.ocp.compet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

<<<<<<< HEAD

=======
import com.tstecon.ocp.compet.vo.CompetFileVO;
>>>>>>> branch 'master' of https://github.com/ITHwang/online-competition-platform-proj.git
import com.tstecon.ocp.compet.vo.CompetVO;


public interface CompetDAO {
//	��ȸ�ȳ� ������
	public List<CompetVO> selectCompetPoster(String compet_id) throws DataAccessException;
//	��ȸ�ȳ� ����
	public List<CompetVO> selectCompetInfo(String Compet_id) throws DataAccessException;
<<<<<<< HEAD
// ���� �Խ��� ����
	public List selectAllQnaList() throws DataAccessException;
=======
	public CompetFileVO selectCompetSmallPoster(String Compet_id) throws DataAccessException;
>>>>>>> branch 'master' of https://github.com/ITHwang/online-competition-platform-proj.git
}
