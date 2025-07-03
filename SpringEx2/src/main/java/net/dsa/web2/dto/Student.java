package net.dsa.web2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter, toString, EsqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
	private String name;
	private int age;
	private String phone;
	private int grade;
}
