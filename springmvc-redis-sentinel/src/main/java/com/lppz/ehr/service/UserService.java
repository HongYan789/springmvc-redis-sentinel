package com.lppz.ehr.service;

import com.lppz.ehr.bean.User;
import com.lppz.ehr.mapper.UserMapper;
import com.lppz.ehr.util.CommonUtil;
import com.lppz.ehr.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(rollbackFor = Exception.class)
@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JedisUtil jedis;
	
	public long insert(User user) {
		CommonUtil.setDefaultValue(user);
		userMapper.insert(user);
		return user.getId();
	}

	public void synData() {
		userMapper.synData();
	}

	
}
