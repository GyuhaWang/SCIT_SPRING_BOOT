package net.dsa.web5.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.dsa.web5.entity.ReplyEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
	  	private Integer replyNum;
	  	private Integer boardNum;
	    private String memberId; 
	    private String memberName;
	    private String contents;
	    private LocalDateTime createDate;
	    
	    public static ReplyDTO toDTO(ReplyEntity entity) {
	    	return ReplyDTO.builder()
	    			.replyNum(entity.getReplyNum())
	    			.boardNum(entity.getBoard().getBoardNum())
	    			.memberId(entity.getMember().getMemberId())
	    			.memberName(entity.getMember().getMemberName())
	    			.contents(entity.getContents())
	    			.createDate(entity.getCreateDate())
	    			.build();
	    	
	    }
}

