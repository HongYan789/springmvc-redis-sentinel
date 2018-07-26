package com.lppz.ehr.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lppz.ehr.exception.InternalException;
import com.lppz.ehr.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 主动推送消息给钉钉以及sap接口回调
 * @author DearZhang
 *
 */
@SuppressWarnings({"unused","rawtypes","unchecked"})
@Transactional(rollbackFor = Exception.class)
//@EnableAsync
@Component
public class SapTaskService {

	@Autowired
	private Config config;
    //修改为可配置
	//private static final long sleepTimes = 1000L * 60 * 1;

	private Logger logger = LoggerFactory.getLogger(SapTaskService.class);
	
	//没任务则sleep，有任务则执行
//	@Async
	@Scheduled(cron="0/1 * * * * * ")
    public void taskCycle() throws Exception{

		int scoreBatchCount = 0;
		int orgCount = 0;
		do {
			logger.info("execute sapTaskCycle");
			
			if(orgCount == 0 && scoreBatchCount == 0) {
				Thread.sleep(config.sleepTimes);
			}
			
		}while(true);
	}
	
	

}
