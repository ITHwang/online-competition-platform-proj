package com.tstecon.ocp.compet.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tstecon.ocp.compet.vo.CompetFileVO;
import com.tstecon.ocp.compet.vo.CompetVO;

@Service
public interface CompetService {
//	��ȸ �ȳ�

	public Map<String, List<CompetVO>> CompetInfo(int compet_id) throws DataAccessException;

	public Map<String, List<CompetFileVO>> CompetPoster(int compet_id) throws DataAccessException;

	// ī�װ� ���̵� ���� ��ȸ ����Ʈ ��������
	public List<CompetVO> competListById(int i) throws Exception;

	// ī�װ� ���̵� ���� ��ȸ ����Ʈ 3�� ��������
	public List<CompetVO> competListByCategIdToThree(int i) throws Exception;

	// ����� ��ȸ ��������
	public List<CompetVO> finishCompetList(int i) throws Exception;

	// ī�װ����� ���� ��ȸ �̸� ��������
	public List<CompetVO> competListByName(int i) throws Exception;

	List<CompetFileVO> CompetSmallPoster(int compet_id) throws DataAccessException;

	public List<CompetVO> CompetName() throws DataAccessException;

	// admin ��ȸ �߰��ϱ�
	public void addCompet(Map<String, Object> competMap) throws DataAccessException;

	public int addCompetId() throws DataAccessException;

	public void competFinish(String compet_name) throws DataAccessException;

	public void competDelete(String compet_name) throws DataAccessException;

}
