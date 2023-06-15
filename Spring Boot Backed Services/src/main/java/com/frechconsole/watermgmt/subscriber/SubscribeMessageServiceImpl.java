package com.frechconsole.watermgmt.subscriber;

import java.sql.Timestamp;
import java.util.Date;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frechconsole.watermgmt.entity.Waterflowdata;
import com.frechconsole.watermgmt.repository.WaterFlowDataRepository;

@Service
public class SubscribeMessageServiceImpl {

	@Autowired
	private WaterFlowDataRepository waterFlowDataRepository;
	
	String broker = "tcp://155.133.22.253:1883";
	String topic = "Water_level";
	String username = "admin";
	String password = "openFreshconsole123";
	String clientid = "emqx_ODkxMT";
	int qos = 0;
	MqttClient client =  null;
	
	public String status()
	{
		String status = "Service stopped or disconnected";
		
		if(client != null && client.isConnected())
		{
			status = "Service connected";
		}

		return status;
	}

	public void start() {

		try 
		{
			client = new MqttClient(broker, clientid, new MemoryPersistence());
			// connect options
			MqttConnectOptions options = new MqttConnectOptions();
			options.setUserName(username);
			options.setPassword(password.toCharArray());
			options.setConnectionTimeout(60);
			options.setKeepAliveInterval(60);
			
			// setup callback
			client.setCallback(new MqttCallback() {

				public void connectionLost(Throwable cause) {
					System.out.println("connectionLost: " + cause.getMessage());
				}

				@SuppressWarnings("removal")
				public void messageArrived(String topic, MqttMessage message) {
					System.out.println("topic: " + topic);
					System.out.println("message content:$" + new String(message.getPayload())+"$");
					System.out.println("Qos: " + message.getQos());

					// Connect database and store value
					try {
						System.out.print("welcome JAP");
						Waterflowdata waterFlowData = new Waterflowdata();
						waterFlowData.setName("UpperTank");
						
						Date date = new Date();  
		                Timestamp ts=new Timestamp(date.getTime());  
		                waterFlowData.setReceivedat(ts);
		                
						waterFlowData.setDistance(new Double(new String(message.getPayload())).doubleValue());

						System.out.println("Data persistance start: " + waterFlowData);
						waterFlowDataRepository.save(waterFlowData);
						System.out.println("Data persistance end ***");
					} catch (Exception ex) {

						ex.printStackTrace();
					}
				}

				public void deliveryComplete(IMqttDeliveryToken token) {
					System.out.println("deliveryComplete---------" + token.isComplete());
				}

			});
			client.connect(options);
			client.subscribe(topic, qos);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
	}
	public void stopService() {


		try 
		{		
			if(client != null && client.isConnected())
			{
				client.disconnect();
				System.out.println("Client Stopped");	
			}
			else
			{
				System.out.println("Queue listening already stoped or not started");
			}
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
	}
}

