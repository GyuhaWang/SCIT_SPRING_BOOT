package net.dsa.web5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web5.dto.MemberDTO;
import net.dsa.web5.entity.MemberEntity;
import net.dsa.web5.repository.MemberRepository;
import net.dsa.web5.security.AuthenticatedUser;

/**
 * 회원 관련 서비스
 * */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository mr; 
	
	private final BCryptPasswordEncoder passwordEncoder;

	/**
	 * 아이디 중복 확인
	 * @param searchId
	 * @return boolean
	 * */
	public boolean idCheck(String searchId) {
		return !mr.existsById(searchId);
	}

	/**
	 * 맴버 가입처리
	 * @param memberDTO
	 * */
	public void join(MemberDTO member) {
		MemberEntity memberentity =MemberEntity.builder()
				.memberId(member.getMemberId())
				.memberPassword(passwordEncoder.encode(member.getMemberPassword()) )
				.memberName(member.getMemberName())
				.email(member.getEmail())
				.phone(member.getPhone())
				.address(member.getAddress())
				.enabled(true)
				.rolename("ROLE_USER")
				.build();	
		log.debug("[service join] memberEntity:{}",memberentity);
		mr.save(memberentity);
	}

	/**
	 * 맴버 정보 조회
	 * @param memberName
	 * @return MemberDTO
	 * */
	public MemberDTO getMember(String username) {
		MemberEntity memberEntity = mr.findById(username).orElseThrow(()-> new EntityNotFoundException("user not found"));
		MemberDTO member = MemberDTO.fromEntity(memberEntity);
		return member;
	}
	
	/**
	 * 맴버 업데이트처리
	 * @param memberDTO
	 * */
	public void update(MemberDTO member) {
		// 비밀번호가 다르다면 변경, 아니면 그대로 
		MemberEntity memberEntity = mr.findById(member.getMemberId())
		            .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));	
		String password = passwordEncoder.encode(member.getMemberPassword());
		if(password != memberEntity.getMemberPassword()) {
			memberEntity.setMemberPassword(password);
		}
		memberEntity.setMemberName(member.getMemberName());
		memberEntity.setEmail(member.getEmail());
		memberEntity.setPhone(member.getPhone());
		memberEntity.setAddress(member.getAddress());
		
		log.debug("[service update] memberEntity:{}",memberEntity);
		//@Transactional 안에서 필드를 변경하면 JPA가 없데이트를 실행함
		mr.save(memberEntity);
		
	}

	public List<MemberDTO> getMembers(AuthenticatedUser user) {
		
		List<MemberEntity> memberEntites = mr.findAll();
		
		List<MemberDTO> members = new ArrayList();
		memberEntites.forEach(entity -> {
			if(!user.getId().equals( entity.getMemberId())) {		
				
			MemberDTO member = new MemberDTO().fromEntity(entity);
			members.add(member);
			}
		});
		return members;
	}

	public void updateAutorize(String id) {
		
		MemberEntity memberEntity = mr.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));	
		
		if(memberEntity.getRolename().equals("ROLE_ADMIN")) {
			memberEntity.setRolename("ROLE_USER");
		}
		else {
			memberEntity.setRolename("ROLE_ADMIN");
		}
	}

	public void updateStatus(String id) {
		MemberEntity memberEntity = mr.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
		memberEntity.setEnabled(!memberEntity.getEnabled());
		
	}
	
}
