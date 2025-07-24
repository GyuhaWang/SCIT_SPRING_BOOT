package net.dsa.web5.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="web5_board")
public class BoardEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="board_num")
	private Integer boardNum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id",referencedColumnName="member_id")
	private MemberEntity member;
	
	@Column(name="title", nullable =  false, length = 1000)
	private String title;
	
	@Column(name ="contents", nullable = false, columnDefinition = "text")
	private String contents;
	
	@Column(name="view_count", columnDefinition="integer default 0")
	private Integer viewCount;
	
	@Column(name="like_count", columnDefinition="integer default 0")
	private Integer likeCount;
	
	@Column(name="original_name")
	private String originamName;
	
	@Column(name="file_name")
	private String fileName;
	
	@CreatedDate
	@Column(name="create_date",columnDefinition="timestamp default"+"current_timestamp")
	private LocalDateTime createdDate;
	
	@Column(name="update_date",columnDefinition="timestamp default"+"current_timestamp")
	private LocalDateTime updateDate;
	
	//orpanRemoval : 게시글에 댓글이 삭제되면 db에도 반영.
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval =true)
	private List<ReplyEntity> replyList;
	
	@PrePersist
	public void prePersist() {
		if(viewCount == null) this.viewCount = 0;
		if(likeCount == null) this.likeCount =0;
	}
}
