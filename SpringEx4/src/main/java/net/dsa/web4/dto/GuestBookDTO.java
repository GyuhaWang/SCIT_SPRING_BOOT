package net.dsa.web4.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dsa.web4.entity.GuestBookEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestBookDTO {
	
	Integer num;
	String name;
	String password;
	String message;
	LocalDateTime inputDate;
	
	public GuestBookEntity toEntity() {
	    return GuestBookEntity.builder()
	        .name(this.name)
	        .password(this.password)
	        .message(this.message)
	        .build();
	}
	
	public static GuestBookDTO fromEntity(GuestBookEntity entity) {
	    return GuestBookDTO.builder()
	        .num(entity.getNum())
	        .name(entity.getName())
	        .password(entity.getPassword())
	        .message(entity.getMessage())
	        .inputDate(entity.getInputDate())
	        .build();
	}
}
