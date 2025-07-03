package net.dsa.web2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data //getter, setter, toString, EsqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Human {
	String name;
	String cid;
	int bYear;
	int bMonth;
	int bDay;
	int genderNum;
	int age;
	String genderString;
}
