package com.tstecon.ocp.contents.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tstecon.ocp.contents.dao.ContentsDAO;
import com.tstecon.ocp.contents.vo.ContentsFileVO;
import com.tstecon.ocp.contents.vo.ContentsVO;
import com.tstecon.ocp.contents.vo.ListContentsVO;

@Service("contentsService")
public class ContentsServiceImpl implements ContentsService {

	@Autowired
	private ContentsDAO contentsDAO;

	@Override
	public int newContentsFileId() throws Exception {
		return contentsDAO.selectNewContentsFileId();
	}

	@Override
	public void addNewContentsFile(ContentsFileVO contentsFileVO) throws Exception {
		contentsDAO.insertNewContentsFile(contentsFileVO);
	}
	
	//�Խ��� ����Ʈ ����
	@Override
	public List<ListContentsVO> listContents(int compet_id) throws DataAccessException {
		List<ListContentsVO> listContents = contentsDAO.listContents(compet_id);
		System.out.println("����Ʈ : " + listContents);
		return listContents;
	}
	
	// Ŭ�� �� ��ȸ�� 1����
	@Override
	public void updateContentsView(int contents_id) throws DataAccessException {
		contentsDAO.updateContentsView(contents_id);
		return;
		
	}
	
	// ������ ���� ����
	@Override
	public List<ListContentsVO> selectContentsView(int contents_id) throws DataAccessException {
		List<ListContentsVO> contentsView = contentsDAO.selectContentsView(contents_id);
		return contentsView;
	}
	// ������ ��� ����Ʈ ����
	@Override
	public List<ListContentsVO> selectContentsCmt(int contents_id) throws DataAccessException {
		List<ListContentsVO> contentsCmt = contentsDAO.selectContentsCmt(contents_id);
		return contentsCmt;
	}
	// ������ ���� ����
	@Override
	public List<ContentsFileVO> selectContentsFile(int contents_id) throws DataAccessException {
		List<ContentsFileVO> contentsFile = contentsDAO.selectContentsFile(contents_id);
		return contentsFile;
	}

	@Override
	public int addNewContents(ContentsVO contentsVO) throws DataAccessException {
		return contentsDAO.insertNewContents(contentsVO);
	}

	// contents file�鿡 contents id �߰�
	@Override
	public void addContentsIdToFiles(List<String> contents_file_list, int contents_id) throws DataAccessException {
		contentsDAO.updateContentsFiles(contents_file_list, contents_id);
	}
	// ������ ��� ī��Ʈ
	@Override
	public List<ListContentsVO> listContentsCmt(int compet_id) throws DataAccessException {
		List<ListContentsVO> contentsCmtList = contentsDAO.selectContentsCmtList(compet_id);
		return contentsCmtList;
	}
	// ������ ���ƿ� ī��Ʈ
	@Override
	public List<ListContentsVO> listContentsLikes(int compet_id) throws DataAccessException {
		List<ListContentsVO> contentsLikesList = contentsDAO.selectContentsLikesList(compet_id);
		return contentsLikesList;
	}
	// ���ƿ� Ŭ�� ���� �˻�
	public int likeChenk(Map update) throws DataAccessException{
		System.out.println("----------------------update=============2 : " + update);
		 return contentsDAO.selectlikeChenk(update);
	}
	// ���ƿ� Ŭ�� ��
	@Override
	public void likeUpdate(Map update) throws DataAccessException {
		 contentsDAO.updateLike(update);
		 return ;
		 
	}
	

	@Override
	public void likeDelete(Map update) throws DataAccessException {
		contentsDAO.deleteLike(update);
		return;
	}


	@Override
	public int likeCount(int contents_id) throws DataAccessException {
		return contentsDAO.countLike(contents_id);
		
	}
	// ������ ��� �߰�
	@Override
	public void insertCmtAdd(Map map) throws DataAccessException {
		contentsDAO.insertCmtAdd(map);
		return;
		
	}

	@Override
	public int selectCmtPlusId() throws DataAccessException {
		int cmtId = contentsDAO.selectCmtPlusId();
		return cmtId;
	}

	@Override
	public void deleteCmt(int cmt_id) throws DataAccessException {
		contentsDAO.deleteCmt(cmt_id);
		return;
	}

}
