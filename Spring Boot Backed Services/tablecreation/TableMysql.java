package com.frechconsole.watermgmt.tablecreation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "WaterData")
public class TableMysql {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private 
	

}
