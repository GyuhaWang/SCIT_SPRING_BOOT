package net.dsa.web5.dto;

import java.time.LocalDateTime;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dsa.web5.entity.BoardEntity;
import net.dsa.web5.entity.MemberEntity;
import net.dsa.web5.entity.ReplyEntity;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	private Integer boardNum;
	private String memberId;
	private String memberName;
	private String title;
	private String contents;
	private Integer viewCount;
	private Integer likeCount;
	private String originalName;
	private String fileName;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	private List<ReplyDTO> replyList;
	 
	

	    /**
	     * Converts a BoardEntity to a BoardDTO.
	     * This static method allows direct conversion from an entity object.
	     *
	     * @param entity The BoardEntity to convert.
	     * @return A new BoardDTO object populated with data from the entity.
	     */
	    public static BoardDTO fromEntity(BoardEntity entity) {
	        if (entity == null) {
	            return null; // Or throw an IllegalArgumentException, depending on desired behavior
	        }

	        String memberId = null;
	        String memberName = null;
	        if (entity.getMember() != null) {
	            memberId = entity.getMember().getMemberId();
	            memberName = entity.getMember().getMemberName();
	        }

//	        List<ReplyDTO> replyDTOs = null;
//	        if (entity.getReplyList() != null) {
//	            replyDTOs = entity.getReplyList().stream()
//	                                .map(ReplyEntity::fromEntity) 
//	                                .collect(Collectors.toList());
//	        }

	        return BoardDTO.builder()
	                .boardNum(entity.getBoardNum())
	                .memberId(memberId)
	                .memberName(memberName)
	                .title(entity.getTitle())
	                .contents(entity.getContents())
	                .viewCount(entity.getViewCount())
	                .likeCount(entity.getLikeCount())
	                .originalName(entity.getOriginamName()) 
	                .fileName(entity.getFileName())
	                .createDate(entity.getCreatedDate())
	                .updateDate(entity.getUpdateDate())
//	                .replyList(replyDTOs)
	                .build();
	    }
}
