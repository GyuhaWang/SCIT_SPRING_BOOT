package net.dsa.ex4.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.ex4.dto.ChickenDTO;
import net.dsa.ex4.entity.ChickenEntity;
import net.dsa.ex4.repository.ChickenRepository;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ChickenService {

	private final ChickenRepository cr;

	public void order(ChickenDTO dto) {
		ChickenEntity entity = ChickenEntity.builder().build();
		
		entity.setChickenType(dto.getChickenType());
		entity.setChickenPrice(dto.getChickenPrice());
		entity.setQuantity(dto.getQuantity());
		String extraOptions = dto.getExtraOptions();
		if (dto.getExtraOptions() == null 
				|| dto.getExtraOptions() == "") {
			extraOptions = "없음";
		}
		entity.setExtraOptions(extraOptions);
		entity.setExtraTotalPrice(dto.getExtraTotalPrice());
		entity.setDeliveryType(dto.getDeliveryType());
		entity.setDeliveryPrice(dto.getDeliveryPrice());
		entity.setTotalPrice(dto.getTotalPrice());
		
		cr.save(entity);
	}
	
}
