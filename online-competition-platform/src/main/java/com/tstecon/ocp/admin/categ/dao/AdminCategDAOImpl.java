package com.tstecon.ocp.admin.categ.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.tstecon.ocp.categ.vo.CategVO;
import com.tstecon.ocp.compet.vo.CompetVO;

@Repository("adminCategDAO")
public class AdminCategDAOImpl implements AdminCategDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<CategVO> selectCategAllList() {
		List<CategVO> categList = (ArrayList) sqlSession.selectList("mappers.admin_categ.selectAllCateg");
		return categList;
	}

	@Override
	public List<CompetVO> selectCompetList() {
		List<CompetVO> competList = (ArrayList) sqlSession.selectList("mappers.compet.selectAllCompet");
		return competList;
	}

	@Override
	public List<CategVO> selectCategName() throws DataAccessException {
		List<CategVO> categName = (ArrayList) sqlSession.selectList("mappers.admin_categ.selectCategName");
		return categName;
	}
	// ���� ���� ī�װ� ���̵� ��������
	@Override
	public int selectCategIdByName(Map<String, Object> addCompetMap) throws DataAccessException {
		int categ_id = sqlSession.selectOne("mappers.admin_categ.selectCategIdByMap", addCompetMap);
		return categ_id;
	}
	// ī�װ� �̸��� ���� ī�װ� ����
	@Override
	public void deleteCategByName(String categ_name) throws DataAccessException {
		sqlSession.delete("mappers.admin_categ.deleteCategByName",categ_name);
		
	}
	// ī�װ� �̸��� ���� ���̵� ��������
	@Override
	public int selectcategIdByName(String categ_name) throws DataAccessException {
		int categ_id = sqlSession.selectOne("mappers.admin_categ.selectCategIdByName",categ_name);
		return categ_id;
	}
	// ī�װ� ���̵� + 1
	@Override
	public int selectCategPlusId() throws DataAccessException {
		int categ_id = sqlSession.selectOne("mappers.admin_categ.selectCategPlusId");
		return categ_id;
	}
	//ī�װ� �߰�
	@Override
	public void insertCateg(Map map) throws DataAccessException {
		sqlSession.insert("mappers.admin_categ.insertCateg",map);
		return;
	}

}
