package com.semi_pj.web.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.semi_pj.web.vo.MemberVO;

public interface MemberSerivce {

	public void insertMember(MemberVO memberVO) throws DataAccessException;
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;

	public void deleteMember(String id) throws DataAccessException;

	public void updateMember(MemberVO memberVO) throws DataAccessException;
	
	public List<MemberVO> selectAllMemberList() throws DataAccessException;
}
