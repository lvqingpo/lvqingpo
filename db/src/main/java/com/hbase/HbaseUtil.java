package com.hbase;

import java.util.List;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Component;

@Component
public class HbaseUtil {
//https://my.oschina.net/u/3754001/blog/1803290
	@Autowired
	HbaseTemplate hbaseTemplate;
	
	public List<Result> historyList(String tableName, String startRowkey, String stopRowkey) {
		Scan scan = new Scan();
    	//scan.setStartRow(startRowkey.getBytes());
    	//scan.setStopRow(stopRowkey.getBytes());
        return hbaseTemplate.find(tableName, scan, (rowMapper, rowNum) -> rowMapper);
    	
	}
	
	public void insertData() {
		hbaseTemplate.put("test", "thursday666", "cf", "ok", "yes".getBytes());
	}
	
}
