package net.dsa.ex4.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dsa.ex4.entity.ChickenEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChickenDTO {
		
		private Integer id;					// PK
	    private String chickenType;			// 치킨 종류
	    private int chickenPrice;			// 치킨 가격
	    private int quantity;				// 수량
	    private String extraOptions;		// 추가 옵션
	    private int extraTotalPrice;		// 추가 옵션 가격
	    private String deliveryType;		// 배달 종류
	    private int deliveryPrice;			// 배달 비용
	    private int totalPrice;				// 총 결제 금액
	    private LocalDateTime orderDate;	// 주문 시간
	    
	    public ChickenEntity toEntity() {
	        return ChickenEntity.builder()
	                .chickenType(this.chickenType)
	                .chickenPrice(this.chickenPrice)
	                .quantity(this.quantity)
	                .extraOptions(this.extraOptions)
	                .extraTotalPrice(this.extraTotalPrice)
	                .deliveryType(this.deliveryType)
	                .deliveryPrice(this.deliveryPrice)
	                .totalPrice(this.totalPrice)
	                .build();
	    }

	    // ChickenEntity -> ChickenDTO 변환 메서드
	    public static ChickenDTO fromEntity(ChickenEntity entity) {
	        return ChickenDTO.builder()
	                .id(entity.getId())
	                .chickenType(entity.getChickenType())
	                .chickenPrice(entity.getChickenPrice())
	                .quantity(entity.getQuantity())
	                .extraOptions(entity.getExtraOptions())
	                .extraTotalPrice(entity.getExtraTotalPrice())
	                .deliveryType(entity.getDeliveryType())
	                .deliveryPrice(entity.getDeliveryPrice())
	                .totalPrice(entity.getTotalPrice())
	                .orderDate(entity.getOrderDate())
	                .build();
	    }
}
