package com.frechconsole.watermgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.frechconsole.watermgmt.model.WaterflowdataDTO;
import com.frechconsole.watermgmt.subscriber.DataFlowServiceImpl;

@RestController
@RequestMapping(value = "/data")
public class DataFlowController {
	
	@Autowired
	DataFlowServiceImpl dataFlowServiceImpl;

	@GetMapping(value = "/recent")
	public List<WaterflowdataDTO> recentRecrods(@RequestParam int recordcount)
	{
		List<WaterflowdataDTO> list = dataFlowServiceImpl.recentRecrods(recordcount);
		return list;
	}
	
	@GetMapping(value = "/all")
	public List<WaterflowdataDTO> allRecords()
	{
		List<WaterflowdataDTO> list = dataFlowServiceImpl.getAll();
		return list;
	}
	
	@GetMapping(value = "/today")
	public List<WaterflowdataDTO> todayRecords()
	{
		List<WaterflowdataDTO> list = dataFlowServiceImpl.todayRecords();
		return list;
	}
	
	@GetMapping(value = "/todayfromtime")
	public List<WaterflowdataDTO> todayRecordsFromTime(@RequestParam int hour
			, @RequestParam int minutes
			, @RequestParam int seconds)
	{
		List<WaterflowdataDTO> list = dataFlowServiceImpl.todayRecordsFromTime(hour, minutes, seconds);
		return list;
	}
	
	@GetMapping(value = "/todayrecordsbetween")
	public List<WaterflowdataDTO> todayRecordsBetween(@RequestParam int hour
			, @RequestParam int minutes
			, @RequestParam int seconds
			, @RequestParam int tohour
			, @RequestParam int tominutes
			, @RequestParam int toseconds)
	{
		List<WaterflowdataDTO> list = dataFlowServiceImpl.todayRecordsBetween(hour, minutes, seconds
				, tohour, tominutes, toseconds);
		return list;
	}
	
	
}
