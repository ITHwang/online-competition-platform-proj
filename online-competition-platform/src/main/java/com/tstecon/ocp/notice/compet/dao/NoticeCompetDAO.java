package com.tstecon.ocp.notice.compet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tstecon.ocp.compet.vo.CompetVO;
import com.tstecon.ocp.notice.compet.vo.NoticeCompetVO;

public interface NoticeCompetDAO {
	public List<NoticeCompetVO> selectNoticesCompetList(String compet_id) throws DataAccessException;
}
