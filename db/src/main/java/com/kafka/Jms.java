package com.kafka;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Jms {
	
	private static final Logger log = LoggerFactory.getLogger(Jms.class);
	
	static private Producer<String, String> producer = null;
	
	static private KafkaConsumer<String, String> consumer=null;
	

	private static ExecutorService producerService= Executors.newCachedThreadPool();
	
	private static ExecutorService consumeService= Executors.newCachedThreadPool();

	static private KafkaConfig kafkaConfig;
	

	
	@Autowired
	Jms(Producer<String, String> producer,KafkaConfig kafkaConfig,KafkaConsumer<String, String> consumer){
		Jms.producer=producer;
		Jms.consumer=consumer;
		Jms.kafkaConfig=kafkaConfig;
	}
	

	
	public static void publishMessage(String key,String value) {
		ProducerRecord<String, String> res= new ProducerRecord<String, String>(Jms.kafkaConfig.getTopic(),key,value);
		org.apache.kafka.clients.producer.Callback cl=  new  org.apache.kafka.clients.producer.Callback() {
			public void onCompletion(RecordMetadata metadata, Exception e) {
				if(e != null) {
					log.info("publish error:"+key+":"+value,e);
				} else {
					log.info("The offset of the record we just sent is: " + metadata.offset());
				}
			}
		};

		producerService.execute(()->{
		 	producer.send(res, cl);
		});
		 log.info("publish:"+Jms.kafkaConfig.getTopic()+","+key+":"+value);
	}
	
	
	public static void consumeMessage() throws Exception {
		consumeService.execute(()->{
			 consumer.subscribe(Arrays.asList(Jms.kafkaConfig.getTopic()));
			 while(true) {
				 ConsumerRecords<String, String> records = consumer.poll(100);
				 for (ConsumerRecord<String, String> record : records) {
					 System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
					 System.out.println("收到了消息");
				 }
			 }
		});
		
		
		
	}
	
	
	
	
	
	

	

	
	
	
	

}
