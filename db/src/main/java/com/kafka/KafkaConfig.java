package com.kafka;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.*;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;



@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConfig {

    private final KafkaProperties properties;

    public KafkaConfig(KafkaProperties properties) {
        this.properties = properties;
    }
	
    
    @Value("${kafka.config.topic}")
	private String topic;
    
   
	@Bean
	public Producer<String, String> producer() {
		Properties props = new Properties();
		   props.put("retries",1);
	       props.put("batch.size",16384);
	       props.put("linger.ms",1);
	       props.put("acks", "1");
	       props.put("buffer.memory",33554432);
	       props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
	       props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
	
	       Map<String, String> config = properties.getConfig();
	       Set<String> keySet = config.keySet();
	       for (String key : keySet) {
	       	if(key.equals("group.id") || key.equals("topic")) {
	       		
	       	}else {
	       		props.put(key, config.get(key));
	       	}
	       }
	       
	    return new KafkaProducer<String, String>(props);
	}

    public KafkaConsumer<String, String> consumerCore() throws Exception {
    	String hostName = getMacIds();
    	Properties props = new Properties();
    	props.put("bootstrap.servers", properties.getConfig().get("bootstrap.servers"));
    	props.put("group.id", hostName);
    	props.put("enable.auto.commit", "true");
    	props.put("auto.commit.interval.ms", "1000");
    	props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    	props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    	
    	return new KafkaConsumer<String, String>(props);
	}
	
	@Bean
	public KafkaConsumer<String, String> consumer() throws Exception {
	    return consumerCore();
	}
	
	public static String getMacIds() throws Exception {
		InetAddress ip = null;
		NetworkInterface ni = null;
		String macList = new String();

		Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
		while (netInterfaces.hasMoreElements()) {
			ni = netInterfaces.nextElement();

			Enumeration<InetAddress> ips = ni.getInetAddresses();
			while (ips.hasMoreElements()) {
				ip = ips.nextElement();
				if (!ip.isLoopbackAddress() 
						&& ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
                    if(ni.getHardwareAddress() != null) {
                        macList += getMacFromBytes(ni.getHardwareAddress());
                    }
				}
			}
		}

		return macList;
	}

	private static String getMacFromBytes(byte[] bytes) {
		StringBuffer mac = new StringBuffer();
		byte currentByte;
		boolean first = false;
		for (byte b : bytes) {
			if (first) {
				mac.append("_");
			}
			currentByte = (byte) ((b & 240) >> 4);
			mac.append(Integer.toHexString(currentByte));
			currentByte = (byte) (b & 15);
			mac.append(Integer.toHexString(currentByte));
			first = true;
		}
		return mac.toString().toUpperCase();
	}
    
 

    public KafkaProperties getProperties() {
        return properties;
    }



    public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
    
}
