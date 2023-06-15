package com.frechconsole.watermgmt.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "water_flow_data", schema = "water_management")
public class Waterflowdata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tankname")
	private String name;

	private double distance;
	
	private Timestamp receivedat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Timestamp getReceivedat() {
		return receivedat;
	}

	public void setReceivedat(Timestamp receivedat) {
		this.receivedat = receivedat;
	}

	@Override
	public String toString() {
		return "Waterflowdata [id=" + id + ", name=" + name + ", distance=" + distance + ", receivedat=" + receivedat
				+ "]";
	}
	
	

}