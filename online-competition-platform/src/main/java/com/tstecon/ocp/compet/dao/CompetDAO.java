package com.tstecon.ocp.compet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;




import com.tstecon.ocp.compet.vo.CompetFileVO;
import com.tstecon.ocp.compet.vo.CompetVO;


public interface CompetDAO {
//	��ȸ�ȳ� ������
	public List<CompetVO> selectCompetPoster(int compet_id) throws DataAccessException;
//	��ȸ�ȳ� ����
	public List<CompetVO> selectCompetInfo(int Compet_id) throws DataAccessException;
//	��ȸ ������ Ŭ��
	public List<CompetVO> selectCompet_id(int Compet_id) throws DataAccessException;
	
	
// ���� �Խ��� ����
	public List selectAllQnaList() throws DataAccessException;

}
