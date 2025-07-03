package net.dsa.ex2.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberForm {

	String id;
	String pw;
	String pwCheck;
	String name;
	String phone;
}
