package net.dsa.web4.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="guestbook")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
/*
 *	@EntityListeners(AuditingEntityListner.class)
 *	Spring Data Jpa 의 Auditing 기능을 사용하기 위해
 *	엔티티 클래스에 추가하는 Annotation
 *	- Entity의 생성 및 수정 시점에서 자동으로 특정 필드를 업데이트
 *	- JPA의 Auditing 기능 :  Entity의 생성 및 수정 시점에서 자동으로 특정 필드를 기록할 수 있도록 도와주는 기능.
 *	- EX.
 *		@CreateDate
 *		@LastModifiedDate
 *		@CreatedBy
 *		@LastModifiedBy
 * */
public class GuestBookEntity {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	@Column(name="num")
	private Integer num;
	
	@Column(name = "name", nullable = false)
	String name;
	
	@Column(name = "password", nullable = false)
	String password;
	
	@Column(name = "message", nullable = false, columnDefinition = "text")
	String message;
	
	@CreatedDate
	@Column(name = "inputdate")
	LocalDateTime inputDate;
}
