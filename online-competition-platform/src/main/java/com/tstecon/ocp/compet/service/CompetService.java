package com.tstecon.ocp.compet.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.tstecon.ocp.compet.qna.vo.CompetQnaVO;
=======
import com.tstecon.ocp.categ.vo.CategVO;
>>>>>>> branch 'master' of https://github.com/ITHwang/online-competition-platform-proj.git
import com.tstecon.ocp.compet.vo.CompetFileVO;
import com.tstecon.ocp.compet.vo.CompetVO;


@Service
public interface CompetService {
//	��ȸ �ȳ�

	public Map<String,List<CompetVO>> CompetInfo(int compet_id) throws DataAccessException;


	public Map<String,List<CompetFileVO>> CompetPoster(int compet_id) throws DataAccessException;
<<<<<<< HEAD
}
=======

//���� 	
	public List<CompetQnaVO>qnalistArticles() throws Exception;
//���� �۾���â
	 public CompetQnaVO login(CompetQnaVO competqnaVO) throws Exception;

	 //��ȸ �̸� ����Ʈ ��������
	 public List<CompetVO> competListById(int i) throws Exception;
}
>>>>>>> branch 'master' of https://github.com/ITHwang/online-competition-platform-proj.git
