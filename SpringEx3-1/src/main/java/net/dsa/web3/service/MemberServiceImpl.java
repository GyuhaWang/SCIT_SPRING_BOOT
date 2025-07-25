package net.dsa.web3.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web3.repository.MemberRepository;
import net.dsa.web3dto.MemberDTO;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository mr;
	
	@Override
	public void signIn(MemberDTO member) {
		try {
		boolean isUserExist = mr.existsById(member.getId());
		if(isUserExist) {
			throw new Exception("user already exist");
		}
		else {
//			MemberEntity me = new Memebr
//			mr.save(null)
		
		}
		catch(Exception e){
			throw e;
		}
		
		
	}
	
}
