package net.dsa.web.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("TaxiServiceImpl")
public class TaxiServiceImpl implements TransportationService {
		
	@Override
		public void move()
		{
//			System.out.println("[콜 수신]");
			System.out.println("start taxi");
//			System.out.println("[결제 처리]");
		}
}
