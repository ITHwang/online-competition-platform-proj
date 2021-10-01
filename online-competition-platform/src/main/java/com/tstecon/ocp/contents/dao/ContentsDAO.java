package com.tstecon.ocp.contents.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tstecon.ocp.contents.vo.ContentsFileVO;
import com.tstecon.ocp.contents.vo.ContentsVO;
import com.tstecon.ocp.contents.vo.ListContentsVO;

public interface ContentsDAO {
	public int selectNewContentsFileId() throws DataAccessException;

	public void insertNewContentsFile(ContentsFileVO contentsFileVO) throws DataAccessException;

	public List<ListContentsVO> listContents(int compet_id) throws DataAccessException;

	public int insertNewContents(ContentsVO contentsVO) throws DataAccessException;

	public int selectNewContentsId() throws DataAccessException;

	public void updateContentsFiles(List<String> contents_file_list, int contents_id) throws DataAccessException;

}
