package com.tstecon.ocp.admin.categ.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.tstecon.ocp.categ.vo.CategVO;
import com.tstecon.ocp.compet.vo.CompetVO;

public interface AdminCategService {
	//ī�װ� ����Ʈ ��������
	public List<CategVO> CategAllList() throws DataAccessException;
	//���Ǿ��� ī�� �̸��� ��������
	public List<CategVO> categName() throws DataAccessException;
	// ���� ���� ���̵� ��������
	public int addCompet(Map<String, Object> addCompetMap) throws DataAccessException;
	//ī�װ� ����
	public void deleteCateg(String categ_name) throws DataAccessException;
	// ī�װ� �̸��� ���� ���̵� ��������
	public int CategIdByName(String categ_name) throws DataAccessException;
	// ī�װ� ���̵� + 1
	public int categPlusId() throws DataAccessException;
	//ī�װ� �߰�
	public void addCateg(Map map) throws DataAccessException;
	
}
