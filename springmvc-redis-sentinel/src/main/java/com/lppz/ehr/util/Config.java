package com.lppz.ehr.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@Configuration
@PropertySource(value={"classpath:ehr-jdbc.properties"})
public class Config{

    @Value("${redis.ipport}")
    public String redisIpPort;

    @Value("${dingding.api.url}")
    public String dingdingApi;

    @Value("${dingding.api.code}")
    public String dingdingCode;
    
    @Value("${oms.services.to.pi.url}")
    public String omsUrl;
    
    @Value("${oms.services.to.pi.username}")
    public String omsUserName;
    
    @Value("${oms.services.to.pi.password}")
    public String omsPassword;
    
    @Value("${job.sleep.times}")
    public Long sleepTimes;
    
    @Value("${job.sleep.jobTimes}")
    public Long jobTimes;

    @Value("${dingding.client.url}")
    public String clientUrl;

    @Value("${dingding.client.agentId}")
    public Long agentId;

    @Value("${dingding.client.msgtype}")
    public String msgtype;

    @Value("${dingding.client.toAllUser}")
    public Boolean toAllUser;

    @Value("${paramConfigs}")
    public String paramConfigs;

    @Value("${checkConfigs}")
    public String checkConfigs;

    @Value("${dingding.push.message.url}")
    public String pushMessageUrl;

    @Value("${dingding.push.message.agentIdUrl}")
    public String agentIdUrl;


    
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
	
}
