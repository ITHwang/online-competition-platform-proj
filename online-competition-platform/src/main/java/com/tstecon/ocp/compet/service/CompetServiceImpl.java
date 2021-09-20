package com.tstecon.ocp.compet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tstecon.ocp.compet.dao.CompetDAO;
import com.tstecon.ocp.compet.vo.CompetFileVO;
import com.tstecon.ocp.compet.vo.CompetVO;
import com.tstecon.ocp.qna.vo.CompetQnaVO;

@Service("CompetService")
public class CompetServiceImpl implements CompetService {
	@Autowired
	private CompetDAO competDAO;

//	��ȸ�ȳ�
	@Override
	public Map<String, List<CompetVO>> CompetInfo(int compet_id) throws DataAccessException {
		Map<String, List<CompetVO>> competInfo = new HashMap<String, List<CompetVO>>();
		List<CompetVO> competList = competDAO.selectCompet_id(compet_id);
		competInfo.put("compet_id", competList);
		competList = competDAO.selectCompetInfo(compet_id);
		competInfo.put("info", competList);

		return competInfo;
	}

//	��ȸ ������
	@Override
	public Map<String, List<CompetFileVO>> CompetPoster(int compet_id) throws DataAccessException {
		Map<String, List<CompetFileVO>> competInfo = new HashMap<String, List<CompetFileVO>>();
		// ū ������ ��������
		List<CompetFileVO> competList = competDAO.selectCompetBigPoster(compet_id);
		competInfo.put("competBigPoster", competList);
		// ���� ������ ��������
		competList = competDAO.selectCompetSmallPoster(compet_id);
		competInfo.put("competSmallPoster", competList);
		// ��ȸ ���̵� ��������
		competList = competDAO.selectCompet_id_file(compet_id);
		competInfo.put("compet_id", competList);
		return competInfo;
	}

//��ȸ ��������: ���� ������ ��������
	@Override
	public List<CompetFileVO> CompetSmallPoster(int compet_id) throws DataAccessException {
		List<CompetFileVO> CompetSmallPoster = competDAO.selectCompetSmallPoster(compet_id);
		return CompetSmallPoster;
	}

//���� �Խ���
	@Override
	public List<CompetQnaVO> qnalistArticles() throws Exception {
		List<CompetQnaVO> qnaList = competDAO.selectAllQnaList();
		return qnaList;
	}

	@Override
	public List<CompetVO> competListById(int i) throws Exception {
		List<CompetVO> competListByName = competDAO.selectCompetId(i);
		return competListByName;
	}

	@Override
	public CompetQnaVO login(CompetQnaVO competqnaVO) throws Exception {
		return null;
	}
	// ��ȸ �̸� ��������
	@Override
	public List<CompetVO> CompetName() throws DataAccessException {
		List<CompetVO> competName = competDAO.selectCompetName();
		return competName;
	}

}
