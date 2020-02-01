package com;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.domain.DeptDO;
import com.hbase.HbaseUtil;
import com.kafka.Jms;
import com.redis.RedisService;
import com.service.DeptService;


@SpringBootApplication
public class DbApplication {
	
	private static  Logger logger=LoggerFactory.getLogger(DbApplication.class);
	
	@Autowired
	DeptService deptService;
	
	@Autowired
    private RedisService redisService;
	
	

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context=SpringApplication.run(DbApplication.class, args);
		//context.getBean(DbApplication.class).run();
		//context.getBean(DbApplication.class).startRedis();
		
		/* List<Result> list=context.getBean(HbaseUtil.class).historyList("test", null, null);
		 for(Result result:list) {
			 for (KeyValue value : result.raw()) {
					String rowkey = new String(value.getRow());//rowkey
					String family = new String(value.getFamily());//列族
					String qualifier = new String(value.getQualifier());//列名
					String dataValue = new String(value.getValue());//列值
					System.out.println("rowkey:"+rowkey);
					System.out.println("family:"+family);
					System.out.println("qualifier:"+qualifier);
					System.out.println("dataValue:"+dataValue);
		
				
				}
		 }*/
		
		//context.getBean(HbaseUtil.class).insertData();
		
		//Jms.publishMessage("qaz", "www");
		
		//Jms.consumeMessage();
		 
		logger.info("start perfect!");
		logger.info("start perfect!");
	}
	
	private void run() {
		Map<String,Object> map=new HashMap<String,Object>();
		List<DeptDO> list=deptService.list(map);
		System.out.println(list.get(0).getName());
	}
	
	private void startRedis() {
		//redisService.del("azz");
		redisService.setStr("jjj", "zzzz");
	}

}
