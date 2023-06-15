import machine
import hcsr04
import time

from machine import Pin

from umqtt.simple import MQTTClient

from time import sleep

#from machine import Timer

from uthingsboard.client import TBDeviceMqttClient
client = TBDeviceMqttClient('194.233.89.191', access_token='b6ugDW2BPyjmJnTGRNl1')
#w9NK1CKg6WaOw3IGa9hQ

#b6ugDW2BPyjmJnTGRNl1
ultrasonic = hcsr04.HCSR04(trigger_pin=5, echo_pin=18, echo_timeout_us=1000000)
#Eled = machine.Pin(2, machine.Pin.OUT)
relay_pin = Pin(2,Pin.OUT)

buzzer = machine.PWM(machine.Pin(32, machine.Pin.OUT))
buzzer.freq(4186)
buzzer.duty(0)


CLIENT_NAME = 'freshconsole'
BROKER_ADDR = '155.133.22.253'
mqttc = MQTTClient(CLIENT_NAME, BROKER_ADDR, keepalive=60)
mqttc.connect()
mqttc.publish(b"iot_topic", b"hello")

client.connect()

while True:
    
    distance = ultrasonic.distance_cm()
    total_height = 100
    
    current_water_level = (total_height - (total_height * 10/100))
    
    treshold= 5
    treshold1 = 20

    mqttc.publish( b"Water_level", str(distance) )
    
    # Sending telemetry
    telemetry = {'Over_head_tank': distance}
    client.send_telemetry(telemetry)
    
    sleep(0.5)
    
    
    if distance <= treshold:   #20
        relay_pin.value(0)
    elif distance >= treshold1:  
        relay_pin.value(1)
    
        
    print('Distance:',distance,"cm")
    
    time.sleep_ms(1000)
    
   

      
      
   
        
         