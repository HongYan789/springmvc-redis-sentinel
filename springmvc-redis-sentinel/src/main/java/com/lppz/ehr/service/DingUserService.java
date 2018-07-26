package com.lppz.ehr.service;

import com.lppz.ehr.bean.DingUser;
import com.lppz.ehr.mapper.DingUserMapper;
import com.lppz.ehr.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/** 
 * @Description: TODO
 * @author: DONG
 * @date: 2018年6月20日  
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DingUserService {
	private Logger logger = LoggerFactory.getLogger(DingUserService.class);
	@Autowired
	private  DingUserMapper  dingUserMapper;
	/** 
	* @Title: insert 
	* @Description: TODO 根据jobnumber有则更新无则插入
	* @param dingUser void
	*/ 
	public void insert(DingUser dingUser){
		CommonUtil.setDefaultValue(dingUser);
		DingUser selectDingUser= dingUserMapper.selectAllByDingid(dingUser.getJobnumber());
		if(selectDingUser!=null){
			dingUserMapper.update(dingUser);
			logger.info("更新ding_user表数据成功"+dingUser);
		}else {
			dingUserMapper.insert(dingUser);
			logger.info("插入ding_user表数据成功"+dingUser);
		}
	}
	public DingUser  selectAllByDingid(String jobnumber){
		return dingUserMapper.selectAllByDingid(jobnumber);
	}
	public Map<String, Object> selectSapidByEmployee(Map<String, Object> map){
		return  dingUserMapper.selectSapidByEmployee(map);
	}
	public Map<String, Object>  selectDeptBysapid(String sapid){
		return dingUserMapper.selectDeptBysapid(sapid);
	}
	public Map<String, Object>  selectPDeptBysapid(String parentid){
		return dingUserMapper.selectPDeptBysapid(parentid);
	}
	public List<Map<String, Object>>  selectSonDeptBysapid(String sapid){
		return dingUserMapper.selectSonDeptBysapid(sapid);
	}
	public List<Map<String, Object>>  selectPersonInfoBysapid(String sapid){
		return dingUserMapper.selectPersonInfoBysapid(sapid);
	}
	public List<Map<String, Object>> selectDeptInfoByDescOrDeptName(Map<String, Object> map){
		return dingUserMapper.selectDeptInfoByDescOrDeptName(map);
	}
	public List<Map<String, Object>> selectPersonInfoByNameOrJobNo(Map<String, Object> map){
		return dingUserMapper.selectPersonInfoByNameOrJobNo(map);
	}
	public List<Map<String, Object>> selectPersonInfoByJobNo(Map<String, Object> map){
		return dingUserMapper.selectPersonInfoByJobNo(map);
	}
	public Map<String, Object> selectAvatarByJobNo(String jobNo){
		return dingUserMapper.selectAvatarByJobNo(jobNo);
	}
	public Map<String, Object> selectPostByStationId(String stationId){
		return dingUserMapper.selectPostByStationId(stationId);
	}
}
