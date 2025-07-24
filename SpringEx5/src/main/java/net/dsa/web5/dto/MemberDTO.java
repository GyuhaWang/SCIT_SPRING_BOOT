package net.dsa.web5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dsa.web5.entity.MemberEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	
	String memberId;
	String memberPassword;
	String memberName;
	String email;
	String phone;
	String address;
	Boolean enabled;
	String rolename;
	
	public MemberEntity toEntity() {
		return MemberEntity.builder()
				.memberId(this.memberId)
				.memberPassword(this.memberPassword)
				.memberName(this.memberName)
				.email(this.email)
				.phone(this.phone)
				.address(this.address)
				.enabled(this.enabled)
				.rolename(this.rolename)
				.build();	
		};
	
	    public static MemberDTO fromEntity(MemberEntity entity) {
	        if (entity == null) {
	            return null;
	        }

	        return MemberDTO.builder()
	                .memberId(entity.getMemberId())
	                .memberPassword(entity.getMemberPassword())
	                .memberName(entity.getMemberName())
	                .email(entity.getEmail())
	                .phone(entity.getPhone())
	                .address(entity.getAddress())
	                .enabled(entity.getEnabled())
	                .rolename(entity.getRolename())
	                .build();
	    }
}
