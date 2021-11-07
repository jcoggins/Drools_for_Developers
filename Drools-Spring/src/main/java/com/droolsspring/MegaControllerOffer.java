package com.droolsspring;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MegaControllerOffer {
	
		@Autowired
		private KieSession session;
		
		@PostMapping("/order")
		public Product orderNow(@RequestBody Product order) {
			session.insert(order);
			session.fireAllRules();
			return order;
			
		}
}
