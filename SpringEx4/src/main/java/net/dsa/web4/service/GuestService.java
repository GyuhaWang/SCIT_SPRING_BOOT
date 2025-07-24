package net.dsa.web4.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web4.dto.GuestBookDTO;
import net.dsa.web4.entity.GuestBookEntity;
import net.dsa.web4.repository.GuestRepository;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class GuestService {
	
	private final GuestRepository gr;

	public void createGuestBook(GuestBookEntity entity) throws Exception {
		try {	
			gr.save(entity);
		}
		catch(Exception e) {
			log.debug("GuestService - createGuestBook] : error :{}",e);
			throw e;
		}
		
	}

	public List<GuestBookDTO> getAll() throws Exception {
		try {
			List<GuestBookEntity> entities = gr.findAll();
			List<GuestBookDTO> dtos = new ArrayList();

			
			for(GuestBookEntity entity : entities ) {
				GuestBookDTO dto =new GuestBookDTO();
				dtos.add(dto.fromEntity(entity));
			}
			
			return dtos;
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	public void deleteById(int id) {
			gr.deleteById(id);
	}
}
