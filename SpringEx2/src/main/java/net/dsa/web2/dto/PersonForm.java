package net.dsa.web2.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * -> 서버 측에서 유효성 검증하기 위함
 검증 Annotaion  
 @Size: length of str,
 @NotNull : cannot use null
 @NotEmpty: cannot use null and ""
 @NotBlack: cannot use null ,"", " "
 @Pattern: use REGEX
 @Max: max value
 @Min: min value 
 */

@Getter @Setter @ToString
public class PersonForm {
	@Pattern(regexp ="^[가-힣]+$", message="한글만 입력 가능합니다.")
	@NotBlank
	String name;
	
	@Min(value=1,message="나이는 1 이상이어야 합니다.")
	@Max(value=200, message="나이는 200 이하여야합니다.")
	int age;
	
	@Pattern(regexp="^010\\d{4}\\d{4}$", message="전화번호 형식은 01011112222 형식입니다.")
	String phone;
}
