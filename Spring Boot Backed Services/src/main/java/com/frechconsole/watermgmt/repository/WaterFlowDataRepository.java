package com.frechconsole.watermgmt.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.frechconsole.watermgmt.entity.Waterflowdata;

public interface WaterFlowDataRepository extends JpaRepository<Waterflowdata, Long>{

	@Query(value="select * from water_flow_data ORDER BY id desc",  
			  nativeQuery = true)
	List<Waterflowdata> recentRecords(Pageable pageable);
	
	@Query(value="select * from water_flow_data where receivedat >= ?1"
			, nativeQuery = true)
	List<Waterflowdata> todayRecords(Timestamp timestamp);
	
	@Query(value="select * from water_flow_data where receivedat > ?1 and receivedat < ?2"
			, nativeQuery = true)
	List<Waterflowdata> todayBetweenRecords(Timestamp fromTimestamp, Timestamp toTimestamp);
	
}