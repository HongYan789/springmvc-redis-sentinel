package com.lppz.ehr.mapper;

import com.lppz.ehr.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Component
public interface UserMapper extends MapperI<User>{
	
	public User selectByOpenid(@Param("wechatOpenid") String openid);

	public User selectByNickName(@Param("nickName") String nickName);

	public void synData();
}
 