package net.dsa.ex4.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChickenDTO {

	private Integer id;
	private String chickenType;
	private int chickenPrice;
	private int quantity;
	private String extraOptions;
	private int extraTotalPrice;
	private String deliveryType;
	private int deliveryPrice;
	private int totalPrice;
	private LocalDateTime orderDate;
	
}
