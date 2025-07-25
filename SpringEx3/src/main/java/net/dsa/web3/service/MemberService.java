package net.dsa.web3.service;

import java.util.List;

import net.dsa.web3.dto.MemberDTO;

public interface MemberService {

	void insertData();

	MemberDTO selectData();

	void updateData(MemberDTO member);

	boolean deleteData(String memberId);

	List<MemberDTO> selectAllData();

	void save(MemberDTO member);

	MemberDTO login(String id, String pw);

	MemberDTO select(String id);

	
	
	/*
	 * BasicController
	 * */
	
}
