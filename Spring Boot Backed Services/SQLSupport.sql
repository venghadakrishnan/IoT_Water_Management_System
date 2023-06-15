drop table water_flow_data;

create table water_flow_data(
	id  int(11) NOT NULL AUTO_INCREMENT
	, tankname varchar(100)
	, receivedat timestamp
	, distance decimal(10,5)
,PRIMARY KEY (`id`)
);

delete  from water_flow_data;

select * from water_flow_data limit 10;
select count(1) from water_flow_data;