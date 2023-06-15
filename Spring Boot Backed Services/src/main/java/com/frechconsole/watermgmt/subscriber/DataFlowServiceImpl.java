package com.frechconsole.watermgmt.subscriber;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.frechconsole.watermgmt.entity.Waterflowdata;
import com.frechconsole.watermgmt.model.WaterflowdataDTO;
import com.frechconsole.watermgmt.repository.WaterFlowDataRepository;

@Service
public class DataFlowServiceImpl {

	@Autowired
	WaterFlowDataRepository dataRepository;

	
	public List<WaterflowdataDTO> recentRecrods( int recordcount)
	{
		List<Waterflowdata> list = dataRepository.recentRecords(PageRequest.of(0,recordcount));
		return convertEntityToDTO(list);
	}
	
	public List<WaterflowdataDTO> getAll()
	{
		List<Waterflowdata> list = (List<Waterflowdata>) dataRepository.findAll();
		return convertEntityToDTO(list);
	}
	
	public List<WaterflowdataDTO> todayRecords()
	{
		Date date = new Date();  
		
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
        Timestamp ts=new Timestamp(date.getTime());  
        
		List<Waterflowdata> list = (List<Waterflowdata>) dataRepository.todayRecords(ts);
		return convertEntityToDTO(list);
	}
	
	public List<WaterflowdataDTO> todayRecordsFromTime(int hour, int minutes, int seconds)
	{
		Date date = new Date();  
		
		date.setHours(hour);
		date.setMinutes(minutes);
		date.setSeconds(seconds);
        Timestamp ts=new Timestamp(date.getTime());  
        
		List<Waterflowdata> list = (List<Waterflowdata>) dataRepository.todayRecords(ts);
		return convertEntityToDTO(list);
	}
	
	public List<WaterflowdataDTO> todayRecordsBetween(int hour, int minutes, int seconds
			, int tohour, int tominutes, int toseconds)
	{
		Date date = new Date();  
		
		date.setHours(hour);
		date.setMinutes(minutes);
		date.setSeconds(seconds-1);
        Timestamp ts=new Timestamp(date.getTime()); 
        
        Date todate = new Date();  
		
        todate.setHours(tohour);
        todate.setMinutes(tominutes);
        todate.setSeconds(toseconds + 1);
        Timestamp tots=new Timestamp(todate.getTime()); 
        
		List<Waterflowdata> list = (List<Waterflowdata>) dataRepository.todayBetweenRecords(ts, tots);
		return convertEntityToDTO(list);
	}
	
	private List<WaterflowdataDTO> convertEntityToDTO(List<Waterflowdata> list)
	{
		List<WaterflowdataDTO> listDTO = new ArrayList<>();
		
		for(Waterflowdata data : list)
		{
			WaterflowdataDTO dto = new WaterflowdataDTO();
			dto.setId(data.getId());
			dto.setName(data.getName());
			dto.setDistance(data.getDistance());
			dto.setReceivedat(data.getReceivedat());
			
			listDTO.add(dto);
		}
		
		return listDTO;
	}
}
