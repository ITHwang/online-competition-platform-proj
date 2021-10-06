package com.tstecon.ocp.contents.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.tstecon.ocp.contents.vo.ContentsFileVO;
import com.tstecon.ocp.contents.vo.ContentsVO;
import com.tstecon.ocp.contents.vo.ListContentsVO;

public interface ContentsService {

	public int newContentsFileId() throws Exception;

	public void addNewContentsFile(ContentsFileVO contentsFileVO) throws Exception;

	public List<ListContentsVO> listContents(int compet_id) throws DataAccessException;

	public int addNewContents(ContentsVO contentsVO) throws DataAccessException;;

	public void addContentsIdToFiles(List<String> contents_file_list, int contents_id) throws DataAccessException;;

	public void updateContentsView(int contents_id) throws DataAccessException;

	public List<ListContentsVO> selectContentsView(int contents_id) throws DataAccessException;

	public List<ListContentsVO> selectContentsCmt(int contents_id) throws DataAccessException;

	public List<ContentsFileVO> selectContentsFile(int contents_id) throws DataAccessException;
	// ������ ��� ī��Ʈ
	public List<ListContentsVO> listContentsCmt(int compet_id) throws DataAccessException;
	// ������ ���ƿ� ī��Ʈ
	public List<ListContentsVO> listContentsLikes(int compet_id) throws DataAccessException;
	// ���ƿ� Ŭ�� ���� �˻�
	public int likeChenk(Map update) throws DataAccessException;
	//���ƿ� Ŭ�� ��
	public void likeUpdate(Map update)throws DataAccessException;

	public void likeDelete(Map update)throws DataAccessException;

	public int likeCount(int contents_id) throws DataAccessException;

	

}
