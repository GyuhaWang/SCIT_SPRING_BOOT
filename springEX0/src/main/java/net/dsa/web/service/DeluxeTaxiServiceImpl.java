package net.dsa.web.service;

import org.springframework.stereotype.Service;

@Service("DeluxeTaxiServiceImpl")
public class DeluxeTaxiServiceImpl   implements TransportationService{
	
	@Override
	public void move()
	{
//		System.out.println("[콜 수신]");
		System.out.println("start deluxetaxi");
//		System.out.println("[결제 처리]");
	}
}
