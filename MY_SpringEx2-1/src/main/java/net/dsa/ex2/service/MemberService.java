package net.dsa.ex2.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.dsa.ex2.controller.MemberController;
import net.dsa.ex2.dto.Member;

@Slf4j
@Service
public class MemberService {
	
	//회원 목록
	private List<Member> memberList = new ArrayList<>();
	
	// 회원가입 처리
	public void signIn(Member member) {
		boolean isAvailable = isAvailableID(member.getId());
		
		if(isAvailable) {
			memberList.add(member);
		}
		else {
			throw new IllegalArgumentException("이미 존재하는 ID입니다.");
		}
	}
	
	private boolean isAvailableID(String ID) {
		var isUsable = true;
		
		for(Member member : memberList) {
			if(member.getId().equals(ID))
				return false;
		}
		
		return isUsable;
	}
	
	// 회원 목록 조회
	public List<Member> getAllUsers(){
		return memberList;
	}
	
	// 로그인
    public Member login(String id, String pw) {
        return memberList.stream()
                .filter(member -> member.getId().equals(id) && member.getPw().equals(pw))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("아이디와 비밀번호를 다시 확인해주세요."));
    }
	
}
