package com.frechconsole.watermgmt.model;

import java.sql.Timestamp;

public class WaterflowdataDTO {

	private Long id;
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
		return "WaterflowdataDTO [id=" + id + ", name=" + name + ", distance=" + distance + ", receivedat=" + receivedat
				+ "]";
	}
	
	
}
