package net.dsa.ex4.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.ex4.dto.ChickenDTO;
import net.dsa.ex4.entity.ChickenEntity;
import net.dsa.ex4.repository.OrderRepository;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository or;

	public void save(ChickenDTO chicken) {
		ChickenEntity chickenEntity = chicken.toEntity();
		or.save(chickenEntity);
		
	}
}
