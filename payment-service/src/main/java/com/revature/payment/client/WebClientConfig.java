package com.revature.payment.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class WebClientConfig {
	@Autowired
	private ReactorLoadBalancerExchangeFilterFunction lbFunction;
	
	@Bean
	 public WebClient webClient() {
		 return WebClient.builder().filter(lbFunction).build();
	     //   return WebClient.builder().build();
		//  var reactorHttpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
	        
	      // return WebClient.builder().clientConnector(new ReactorClientHttpConnector(reactorHttpClient)).build();
	    }
}

