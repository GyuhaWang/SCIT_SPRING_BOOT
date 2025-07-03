package net.dsa.web2.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class HumanForm {
	@Pattern(regexp ="^[가-힣]+$", message="한글만 입력 가능합니다.")
	@NotBlank
	String name;
	
	@Pattern(regexp="^\\d{6}-\\d{7}$", message="주민등록 번호는 '-' 를 포함하여 14글자 입니다.")
	@NotBlank
	String cid;
}
