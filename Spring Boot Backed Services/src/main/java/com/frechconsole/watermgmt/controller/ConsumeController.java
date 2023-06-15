package com.frechconsole.watermgmt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frechconsole.watermgmt.subscriber.SubscribeMessageServiceImpl;

@RestController
@RequestMapping(value = "/admin")
public class ConsumeController {

	
	@Autowired
	private SubscribeMessageServiceImpl subscribeMessageServiceImpl;
	
	@GetMapping(value = "/status")
	public Map<String, String> status()
	{
		
		// Get the status
		String status = subscribeMessageServiceImpl.status();
		
		Map<String, String> response = new HashMap<>();
		response.put("message", status);
		
		return response;
	}
	
	@GetMapping(value = "/start")
	public Map<String, String> start()
	{
		
		// Start consume the message
		subscribeMessageServiceImpl.start();
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Consume service started");
		
		return response;
	}
	
	@GetMapping(value = "/stop")
	public Map<String, String> stop()
	{
		
		// Stop consuming the message
		subscribeMessageServiceImpl.stopService();
		
		Map<String, String> response = new HashMap<>();
		response.put("message", "Serivice Stopped");
		
		return response;
	}
}
