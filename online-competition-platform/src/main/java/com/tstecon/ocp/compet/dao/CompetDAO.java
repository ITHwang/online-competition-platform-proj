package com.tstecon.ocp.compet.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

<<<<<<< HEAD
import com.tstecon.ocp.compet.qna.vo.CompetQnaVO;
=======
import com.tstecon.ocp.categ.vo.CategVO;
>>>>>>> branch 'master' of https://github.com/ITHwang/online-competition-platform-proj.git
import com.tstecon.ocp.compet.vo.CompetFileVO;
import com.tstecon.ocp.compet.vo.CompetVO;


public interface CompetDAO {
//	��ȸ�ȳ� ������
	// ū ������
	public List<CompetFileVO> selectCompetBigPoster(int compet_id) throws DataAccessException;
	// ���� ������
	public List<CompetFileVO> selectCompetSmallPoster(int compet_id) throws DataAccessException;
	
//	��ȸ�ȳ� ����
	public List<CompetVO> selectCompetInfo(int compet_id) throws DataAccessException;
//	��ȸ ������ Ŭ��
	public List<CompetVO> selectCompet_id(int compet_id) throws DataAccessException;
	public List<CompetFileVO> selectCompet_id_file(int compet_id) throws DataAccessException;

	

<<<<<<< HEAD

=======
// ���� �Խ��� ����
	public List selectAllQnaList() throws DataAccessException;
	
	public int insertNewArticle(Map articleMap) throws DataAccessException;
>>>>>>> branch 'master' of https://github.com/ITHwang/online-competition-platform-proj.git

	public List<CompetVO> selectCompetId(int i) throws DataAccessException;




}
