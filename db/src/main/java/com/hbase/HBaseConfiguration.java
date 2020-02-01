package com.hbase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

@Configuration
public class HBaseConfiguration {

    @Value("${hbase.config.hbase.zookeeper.quorum}")
    private String zookeeperQuorum;

    @Value("${hbase.config.hbase.zookeeper.property.clientPort}")
    private String clientPort;


    @Bean
    public HbaseTemplate hbaseTemplate() {
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        conf.set("hbase.zookeeper.quorum", zookeeperQuorum);
        conf.set("hbase.zookeeper.property.clientPort", clientPort);
        return new HbaseTemplate(conf);
    }
}