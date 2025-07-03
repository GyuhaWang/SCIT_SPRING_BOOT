package net.dsa.web3dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dsa.web3.entity.MemberEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	
	private String id;
	private String pw;
	private String name;
	private String address;
	private String phone;
	
	public void entitity_to_dto(MemberEntity entity, MemberDTO dto) {
		dto.setId(entity.getId());
		dto.setPw(entity.getId());
		dto.setName(entity.getId());
		dto.setAddress(entity.getId());
		dto.setPhone(entity.getId());
	}
	
	public void dto_to_entity(MemberEntity entity, MemberDTO dto) {
		entity.setId(dto.getId());
		entity.setPw(dto.getId());
		entity.setName(dto.getId());
		entity.setAddress(dto.getId());
		entity.setPhone(dto.getId());
	}
	
}
