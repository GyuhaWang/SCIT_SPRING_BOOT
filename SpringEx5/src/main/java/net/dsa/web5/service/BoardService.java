package net.dsa.web5.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web5.dto.BoardDTO;
import net.dsa.web5.entity.BoardEntity;
import net.dsa.web5.entity.MemberEntity;
import net.dsa.web5.repository.BoardRepository;
import net.dsa.web5.repository.MemberRepository;
import net.dsa.web5.repository.ReplyRepository;
import net.dsa.web5.util.FileManager;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {
	
	private final MemberRepository mr;
	private final BoardRepository br;
	private final ReplyRepository rr;
	private final FileManager fileManager;
	
	/**
	 * 글 저장
	 * @param boardDTO
	 * @param uploadPath
	 * @param upload
	 * */
	public void write(BoardDTO boardDTO, String uploadPath,MultipartFile upload) throws IOException {
		// 작성자 정보 조회
		MemberEntity memberEntity = mr.findById(boardDTO.getMemberId())
				.orElseThrow(()-> new EntityNotFoundException("회원이 없습니다."));
		
		// 게시글 저장
		BoardEntity entity = BoardEntity.builder().build();
		entity.setMember(memberEntity);
		entity.setTitle(boardDTO.getTitle());
		entity.setContents(boardDTO.getContents());
		
		//첨부파일이 있는 경우
		if(upload != null && !upload.isEmpty()) {
			String fileName;
			fileName = fileManager.saveFile(uploadPath, upload);
			entity.setFileName(fileName);
			entity.setOriginamName(upload.getOriginalFilename());
		}
		log.debug("saved entity:{}",entity);
		br.save(entity);
	}

	public List<BoardDTO> getAll() {
		List<BoardEntity> entities=  br.findAll();
		List<BoardDTO> list = new ArrayList();
		
		for(BoardEntity entity : entities) {
			BoardDTO dto = BoardDTO.fromEntity(entity);
			list.add(dto);
		}
		
		return list;
		
	}

	public void updateViewCount(Integer id) {
		BoardEntity entity = br.findById(id).orElseThrow(()-> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));
		entity.setViewCount(entity.getViewCount()+1);

	}

	public BoardDTO getById(Integer id,boolean updateViewCount) {
		
		BoardEntity entity = br.findById(id).orElseThrow(()-> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));
		if(updateViewCount) {
			entity.setViewCount(entity.getViewCount()+1);
		}
		return BoardDTO.fromEntity(entity);
	}

	public void addRecommend(Integer id) {
		BoardEntity entity = br.findById(id).orElseThrow(()-> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));
		entity.setLikeCount(entity.getLikeCount()+1);
		
	}

	public void download(int boardNum, HttpServletResponse response, String uploadPath) {
		BoardEntity entity = br.findById(boardNum).orElseThrow(()-> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));
		
		try {
			/*
			 *Content-Disposition : 브라우저에게 응답에 퍼함된 컨텐츠를 어떻게 처리 할지 지시하는 HTTP 헤더
			 *attachment: 브라우저가 해당 파일을 다운로드 하도록 지시
			 *filename: 다운로드 창에 표시될 파일 이름 지정
			 *URLEncoder.encode : 파일 이름에 한글, 공백, 특수문자가 포함되어 있을 경우 문제가 될 수 이어 UTF-8로 인코딩 
			 * */
			response.setHeader("Content-Disposition","attachment;filename="+
					URLEncoder.encode(entity.getOriginamName(), "UTF-8")
					);
		}
		catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//저장된 파일 경로값
		String fullPath = uploadPath + "/" +entity.getFileName();
		
		//서버의 파일을 읽기 위한 FileInputStream
		FileInputStream filein = null;
		//클라이언트에게 파일을 전송하기 위한 출력 스트림
		ServletOutputStream fileout = null;
		
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			
			FileCopyUtils.copy(filein, fileout);
			
			filein.close();
			fileout.close();
			
		}catch(IOException e) {
			log.debug("[exception occured] can not find the file");
			
		}
		
	}

	public void delete(Integer id) {
		br.deleteById(id);
		
	}

	public void update(BoardDTO board, String uploadPath,MultipartFile upload) throws IOException {
		BoardEntity entity = br.findById(board.getBoardNum()).orElseThrow(()-> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));
		
		entity.setTitle(board.getTitle());
		entity.setContents(board.getContents());
		entity.setUpdateDate(LocalDateTime.now());
		//첨부파일이 있는 경우
		if(upload != null && !upload.isEmpty()) {
					String fileName;
					fileName = fileManager.saveFile(uploadPath, upload);
					entity.setFileName(fileName);
					entity.setOriginamName(upload.getOriginalFilename());
		}
		br.save(entity);
		log.debug("saved entity:{}",entity);
	}
}
