package net.dsa.web3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web3.dto.MemberDTO;
import net.dsa.web3.entity.MemberEntity;
import net.dsa.web3.repository.MemberRepository;

@Service
@Slf4j
@Transactional
/*
 * @Transactional
 * 	-메서드나 클래스에 적용, 해당 메서드가 호출 될 때 트렌젝션 시작,
 * 	-메서드가 완료되면 commit,
 * 	-예외(error)가 발생하면 rollback,
 * 	-해당 어노테이션은 public 메서드 에서만 적용됨
 * 	-class 에 붙이면 class 내의 모든 메서드가 Transaction 적용됨
 * */
// @Transactional -> 그러면 그동안은 서버가 다른 일을 못하는 건가??
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberRepository mr;

	@Override
	public void insertData() {
		// builder 사용
		MemberEntity m = MemberEntity.builder()
				.id("eee")
				.pw("123")
				.name("고길동")
				.phone("010-1234-1234")
				.address("서울특별시 강남구")
				.build();
		mr.save(m);
		
	}

	/**
	 * 회원 정보 조회
	 * @return MemberDTO
	 * */
	@Override
	public MemberDTO selectData() {
		String memberId ="eee";
		MemberEntity member = mr.findById(memberId).orElse(null);
		if(member == null) {
			return null;
		}
		log.debug("[service-info] memberEntity: {}",member);
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.convertEntity_to_DTO(member, memberDTO);
		
		return memberDTO;
	}

	/**
	 * 회원 정보 수정
	 * @param member 수정할 MemberDTO
	 * 
	 * */
	@Override
	public void updateData(MemberDTO member) {
		
		/*
		 * optional<T>
		 * null 로 인한 NullPointException 을 방지하기 위한 자바 클래스
		 */
		MemberEntity m = mr.findById(member.getId()).orElseThrow(()-> new EntityNotFoundException("없는 ID"));
		member.convertDTO_to_Entity(member, m);
		
		mr.save(m);
		log.debug("[service-updateData] member update success");
		
	}

	/**
	 * 회원 정보 삭제
	 * @param id 삭제할 아이디
	 * @return boolean
	 * */
	@Override
	public boolean deleteData(String memberId){
		boolean result = mr.existsById(memberId);
		if(result) {
			mr.deleteById(memberId);
			return true;
		}
		return false;
	}
	
	/**
	 * 모든 회원 정보 조회
	 * @return List<MemberDTO>
	 * */
	@Override
	public List<MemberDTO> selectAllData() {
		
		List<MemberEntity> entities = mr.findAll();
		List<MemberDTO> dtos = new ArrayList<>();
		
		for(MemberEntity entity : entities) {
		 MemberDTO dto = new MemberDTO();
		 dto.convertEntity_to_DTO(entity, dto);
		 dtos.add(dto);
		}
		
		return dtos;
	}
	
	/**
	 * 회원가입
	 * @Param MemberDTO 
	 * 
	*/
	@Override
	public void save(MemberDTO member) {
		MemberEntity m = new MemberEntity(); 
		member.convertDTO_to_Entity(member, m);
		mr.save(m);
		log.debug("[member service] save success");
	}

	/**
	 * login
	 * @param id
	 * @param pw
	 * @return MemberDTO
	 * */
	@Override
	public MemberDTO login(String id, String pw) {
		MemberEntity member = mr.findById(id).orElseThrow(()-> new EntityNotFoundException("ID not exist"));
		boolean isValidPW = member.getPw().equals(pw);
		if(!isValidPW) throw new EntityNotFoundException("invalid password");
		MemberDTO m = new MemberDTO();
		m.convertEntity_to_DTO(member, m);
		return m;
	}
	/**
	 * select by id
	 * @param id
	 * @return MemberDTO
	 * */
	@Override
	public MemberDTO select(String id) {
		try {
			MemberEntity member = mr.findById(id).orElseThrow(()-> new EntityNotFoundException("ID not exist"));
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.convertEntity_to_DTO(member, memberDTO);
			return memberDTO;
		}
		catch(Exception e) {
			throw e;
		}
	
	}


	
}
