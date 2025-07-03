package net.dsa.web3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 해당 클래스를 엔티티로 정의하여 JPA가 해당 클래스를 DB테이블과 매핑
@Entity
// 엔티티가 매핑될 테이블 지정, 생략하면 클래스 이름을 테이블 이름으로 사용
@Table(name="member3")
public class MemberEntity  {
	
	@Id
	@Column(name="id", nullable = false, length=30)
	private String id;
	@Column(name="pw", length=50)
	private String pw;
	@Column(name="name")
	private String name;
	@Column(name="phone")
	private String phone;
	@Column(name="address")
	private String address;
}
