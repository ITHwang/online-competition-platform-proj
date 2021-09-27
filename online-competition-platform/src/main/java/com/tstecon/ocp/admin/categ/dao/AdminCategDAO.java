package com.tstecon.ocp.admin.categ.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.tstecon.ocp.categ.vo.CategVO;
import com.tstecon.ocp.compet.vo.CompetVO;

public interface AdminCategDAO {

	public List<CategVO> selectCategAllList() throws DataAccessException;
	
	public List<CompetVO> selectCompetList() throws DataAccessException;
	//���Ǿ��� ī�װ� �̸� ��������
	public List<CategVO> selectCategName() throws DataAccessException;
	//���� ���� ī�װ� ���̵� ��������
	public int selectCategIdByName(Map<String, Object> addCompetMap) throws DataAccessException;
	// ī�װ� ����
	public void deleteCategByName(String categ_name) throws DataAccessException;
	//ī�װ� �̸��� ���� ���̵� ��������
	public int selectcategIdByName(String categ_name) throws DataAccessException;
}
