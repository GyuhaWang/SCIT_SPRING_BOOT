package net.dsa.ex2.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class loginForm {
	String id;
	String pw;
	boolean storeId;
}
